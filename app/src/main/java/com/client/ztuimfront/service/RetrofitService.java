package com.client.ztuimfront.service;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private Retrofit retrofit;

    public RetrofitService() {
        initializeRetrofit();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    private void initializeRetrofit() {

        retrofit = new Retrofit.Builder().baseUrl("http://172.28.122.58:8080")
                .addConverterFactory(GsonConverterFactory.create(new Gson())).build();
    }
}
