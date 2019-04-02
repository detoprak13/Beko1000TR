package com.dtoprak.beko1000tr;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class RetrofitClient {

	private static Retrofit retrofit;
	private static final String BASE_URL = "https://sandbox-api.payosy.com";
	public static final String CLIENT_ID = "90045867-c788-4a78-82c6-ded0aa6ae9b6";
	public static final String SECRET_KEY = "lE3pA4tA3tM3yN5aN3sM0vU2mV0uS4rK6rC2nC3jV3gV1rR4bN";

	public static Retrofit getRetrofitInstance() {
		if (retrofit == null) {
			retrofit = new retrofit2.Retrofit.Builder()
					.baseUrl(BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.build();
		}
		return retrofit;
	}

	public interface GetDataService {

		@Headers({"x-ibm-client-id: " + CLIENT_ID, "x-ibm-client-secret: " + SECRET_KEY, "content-type", "application/json", "accept", "application/json"})
		@POST("/get_qr_sale")
		Call<String> getQrData(@Field("totalReceiptAmount") double amount);
	}
}
