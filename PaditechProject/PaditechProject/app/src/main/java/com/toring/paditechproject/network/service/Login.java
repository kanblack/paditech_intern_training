package com.toring.paditechproject.network.service;

import com.toring.paditechproject.network.model.P2MainObject;

import retrofit2.Callback;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ToRing on 12/15/2017.
 */

public interface Login {
    @POST("https://demo0858711.mockable.io/login")
    Callback<P2MainObject> login(@Header("username") String username, @Header("password") String password);
}
