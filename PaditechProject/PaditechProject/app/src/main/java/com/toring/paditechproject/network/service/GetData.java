package com.toring.paditechproject.network.service;

import com.toring.paditechproject.network.model.P1MainObject;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ToRing on 12/15/2017.
 */

public interface GetData {
    @GET("https://demo0858711.mockable.io/p1/getP1Data")
    Call<P1MainObject> getData();
}
