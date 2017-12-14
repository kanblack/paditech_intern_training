package com.pesteam.watchimage.getData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by bangindong on 12/14/2017.
 */

public interface APIService {

    @GET("images")
    Call<ChildScreen1Class> loadData();
}
