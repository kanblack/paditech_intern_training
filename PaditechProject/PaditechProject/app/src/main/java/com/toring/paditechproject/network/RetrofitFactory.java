package com.toring.paditechproject.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ToRing on 12/15/2017.
 */

public class RetrofitFactory {
    private static RetrofitFactory instance;
    private static Retrofit retrofit;

    public RetrofitFactory() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitFactory getInstance() {
        if (instance == null) {
            instance = new RetrofitFactory();
        }
        return instance;
    }

    public static <ServiceClass> ServiceClass createServiceClass(Class<ServiceClass> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
