package com.dtoprak.beko1000tr.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dtoprak.beko1000tr.models.QrData;
import com.dtoprak.beko1000tr.models.QrResponse;
import com.dtoprak.beko1000tr.R;
import com.dtoprak.beko1000tr.util.UnsafeOkHttpClient;
import com.dtoprak.beko1000tr.util.Util;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
	private QrData qrData;
	private Button paymentButton;
	private ViewGroup containerLayout;
	LayoutInflater inflater;
	private boolean requestOngoing = false;

	public HomeFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		this.inflater = inflater;
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		containerLayout = view.findViewById(R.id.container);
		paymentButton = view.findViewById(R.id.payment_button);

		paymentButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (qrData.getTransactionAmount() > 0 && !requestOngoing) {
					payment();
				}
			}
		});
		return view;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestOngoing = false;
		getQRSale();
	}

	private void getQRSale() {
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"totalReceiptAmount\":100}");
		Request request = new Request.Builder()
				.url("https://sandbox-api.payosy.com/api/get_qr_sale")
				.post(body)
				.addHeader("x-ibm-client-id", UnsafeOkHttpClient.CLIENT_ID)
				.addHeader("x-ibm-client-secret", UnsafeOkHttpClient.SECRET_KEY)
				.addHeader("content-type", "application/json")
				.addHeader("accept", "application/json")
				.build();
		Call call = UnsafeOkHttpClient.getUnsafeOkHttpClient().newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				Log.d("getQRSale", "FAIL: " + e);
				if (isAdded()) {
					getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(getContext(), "GET QR SALE FAILED: " + e, Toast.LENGTH_LONG).show();
						}
					});
				}
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if (response.isSuccessful()) {
					String responseStr = response.body().string();
					Gson gson = new Gson();
					QrResponse qrResponse = gson.fromJson(responseStr, QrResponse.class);
					qrData = Util.qrParser(qrResponse.QRdata);
					qrData.setAsSting(responseStr);
					Log.d("getQRSale", "SUCCESS: " + qrData.toString());
					if (isAdded()) {
						getActivity().runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(getContext(), "GET QR SALE SUCCESS", Toast.LENGTH_LONG).show();
							}
						});
					}

					if (isAdded()) {
						getActivity().runOnUiThread(new Runnable() {
							@Override
							public void run() {
								containerLayout.addView(generateView("Receipt ID:", qrData.getReceiptId()));
								containerLayout.addView(generateView("Pos ID:", qrData.getPosId()));
								containerLayout.addView(generateView("Receipt Date:", qrData.getReceiptDateTime()));
								containerLayout.addView(generateView("Transaction Amount:", "" + qrData.getTransactionAmount()));
							}
						});
					}
				} else {
					Log.d("getQRSale", "NOT SUCCESSFULL");
					if (isAdded()) {
						getActivity().runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(getContext(), "GET QR SALE Failed", Toast.LENGTH_LONG).show();
							}
						});
					}
				}
			}
		});
	}

	private void payment() {
		requestOngoing = true;
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"returnCode\":1000,\"returnDesc\"" +
				":\"success\",\"receiptMsgCustomer\":\"beko Campaign\",\"receiptMsgMerchant\"" +
				":\"beko Campaign Merchant\",\"paymentInfoList\":[{\"paymentProcessorID\"" +
				":67,\"paymentActionList\":[{\"paymentType\":3,\"amount\":" + qrData.getTransactionAmount() + ",\"currencyID\"" +
				":" + qrData.getTransactionCurrency() + ",\"vatRate\":800}]}],\"QRdata\"" +
				":\"" + qrData.getAsSting() + "\"}");
		Request request = new Request.Builder()
				.url("https://sandbox-api.payosy.com/api/payment")
				.post(body)
				.addHeader("x-ibm-client-id", UnsafeOkHttpClient.CLIENT_ID)
				.addHeader("x-ibm-client-secret", UnsafeOkHttpClient.SECRET_KEY)
				.addHeader("content-type", "application/json")
				.addHeader("accept", "application/json")
				.build();

		Call call = UnsafeOkHttpClient.getUnsafeOkHttpClient().newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				requestOngoing = false;
				Log.d("payment", "FAIL");
				if (isAdded()) {
					getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(getContext(), "Payment Failed", Toast.LENGTH_LONG).show();
						}
					});
				}
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				requestOngoing = false;
				if (response.isSuccessful()) {
					String responseStr = response.body().string();
					if (isAdded()) {
						getActivity().runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(getContext(), "Payment Success", Toast.LENGTH_LONG).show();
							}
						});
					}
					Log.d("DDDTTT", responseStr);
				} else {
					Log.d("payment", "NOT SUCCESSFULL");
					if (isAdded()) {
						getActivity().runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(getContext(), "Payment Failed", Toast.LENGTH_LONG).show();
							}
						});
					}
				}
			}
		});
	}

	private View generateView(String title, String data) {
		View view = inflater.inflate(R.layout.text_field, null);
		TextView titleText = view.findViewById(R.id.data_title_text_view);
		TextView dataText = view.findViewById(R.id.data_text_view);
		titleText.setText(title);
		dataText.setText(data);
		return view;
	}

}
