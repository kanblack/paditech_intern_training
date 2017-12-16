package com.toring.paditechproject.network.model;

/**
 * Created by ToRing on 12/15/2017.
 */

public class P2Data {
    private int user_id;
    private String access_token;

    public P2Data(int user_id, String access_token) {
        this.user_id = user_id;
        this.access_token = access_token;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
