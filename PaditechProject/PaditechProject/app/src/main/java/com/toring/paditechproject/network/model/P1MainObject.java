package com.toring.paditechproject.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class P1MainObject {

    @SerializedName("data")
    @Expose
    private P1Data p1Data;

    /**
     * No args constructor for use in serialization
     */
    public P1MainObject() {
    }

    /**
     * @param p1Data
     */
    public P1MainObject(P1Data p1Data) {
        super();
        this.p1Data = p1Data;
    }

    public P1Data getP1Data() {
        return p1Data;
    }

    public void setP1Data(P1Data p1Data) {
        this.p1Data = p1Data;
    }
}

