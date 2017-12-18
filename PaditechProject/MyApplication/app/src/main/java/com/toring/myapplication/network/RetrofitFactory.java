package com.toring.myapplication.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tr on 12/18/17.
 */

public class RetrofitFactory {
    private static RetrofitFactory instance;
    private static Retrofit retrofit;

    public RetrofitFactory() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://demo0858711.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitFactory getInstance() {
        if (instance == null){
            instance = new RetrofitFactory();
        }
        return instance;
    }

    public static <ServiceClass> ServiceClass createService(Class<ServiceClass> serviceClass){
        return retrofit.create(serviceClass);
    }
}
