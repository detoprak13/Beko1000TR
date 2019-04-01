package com.dtoprak.beko1000tr;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
	private ImageView homeButton;
	private ImageView settingsButton;
	private Fragment currentFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		homeButton = findViewById(R.id.bottom_navigation_home);
		settingsButton = findViewById(R.id.bottom_navigation_settings);

		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		HomeFragment homeFragment = new HomeFragment();
		currentFragment = homeFragment;
		homeButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));
		fragmentTransaction.replace(R.id.main_content_container, homeFragment);
		fragmentTransaction.commit();

		homeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!(currentFragment instanceof HomeFragment)) {
					HomeFragment homeFragment = new HomeFragment();
					currentFragment = homeFragment;
					settingsButton.setBackgroundColor(getResources().getColor(R.color.background_color, getTheme()));
					homeButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));
					FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
					fragmentTransaction.replace(R.id.main_content_container, homeFragment);
					fragmentTransaction.commit();
				}
			}
		});

		settingsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!(currentFragment instanceof SettingsFragment)) {
					SettingsFragment settingsFragment = new SettingsFragment();
					currentFragment = settingsFragment;
					homeButton.setBackgroundColor(getResources().getColor(R.color.background_color, getTheme()));
					settingsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));
					FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
					fragmentTransaction.replace(R.id.main_content_container, settingsFragment);
					fragmentTransaction.commit();
				}
			}
		});

		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"totalReceiptAmount\":100}");
		Request request = new Request.Builder()
				.url("https://sandbox-api.payosy.com/api/get_qr_sale")
				.post(body)
				.addHeader("x-ibm-client-id", RetrofitClient.CLIENT_ID)
				.addHeader("x-ibm-client-secret", RetrofitClient.SECRET_KEY)
				.addHeader("content-type", "application/json")
				.addHeader("accept", "application/json")
				.build();
		Call call = client.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				// Something went wrong
				Log.d("DDDTTT", e.toString());
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if (response.isSuccessful()) {
					String responseStr = response.body().string();
					// Do what you want to do with the response.
					Log.d("DDDTTT", responseStr);
				} else {
					Log.d("DDDTTT", "asd");
					// Request not successful
				}
			}
		});

	}

}
