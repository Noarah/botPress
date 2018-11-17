package com.example.noarah.botPress.Service;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String BASE_URL = "http://a8b8ad23.ngrok.io/conversations/default/";


    public static Retrofit retrofit;

    public static Retrofit getRetrofitClient() {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return retrofit;
    }
}