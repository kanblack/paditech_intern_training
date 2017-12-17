package com.toring.paditechproject.network.service;

import com.toring.paditechproject.network.model.P2MainObject;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by ToRing on 12/15/2017.
 */

public interface ServiceLogin {
    @POST("https://demo0858711.mockable.io/login")
    Call<P2MainObject> login();
}
