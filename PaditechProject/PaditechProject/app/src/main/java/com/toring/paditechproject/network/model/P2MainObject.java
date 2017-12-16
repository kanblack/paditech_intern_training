package com.toring.paditechproject.network.model;

/**
 * Created by ToRing on 12/15/2017.
 */

public class P2MainObject {
    private P2Data data;

    public P2MainObject(P2Data data) {
        this.data = data;
    }

    public P2Data getData() {
        return data;
    }

    public void setData(P2Data data) {
        this.data = data;
    }
}
