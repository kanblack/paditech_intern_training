package com.pesteam.watchimage.getData;

/**
 * Created by bangindong on 12/14/2017.
 */

public class APIUtils {

    private APIUtils(){}

    static final String BASE_URL = "https://demo0858711.mockable.io/";

    public static APIService getAPIService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
