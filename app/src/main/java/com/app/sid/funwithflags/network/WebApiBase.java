package com.app.sid.funwithflags.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sidhanshu Udawat on 19-Jan-18.
 */

public abstract class WebApiBase {
    private static final String BASE_URL = "https://restcountries.eu/rest/v2/";
    protected Retrofit mRetrofit;

    public WebApiBase() {
        initRequestService();
    }

    private void initRequestService() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    protected abstract void getRequestsService();
}