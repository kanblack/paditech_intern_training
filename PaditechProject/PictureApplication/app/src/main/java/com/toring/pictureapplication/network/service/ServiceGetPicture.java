package com.toring.pictureapplication.network.service;

import com.toring.pictureapplication.network.modle.MainObject;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tr on 12/18/17.
 */

public interface ServiceGetPicture {
    @GET("https://demo0858711.mockable.io/images")
    Call<MainObject> getPicture();
}
