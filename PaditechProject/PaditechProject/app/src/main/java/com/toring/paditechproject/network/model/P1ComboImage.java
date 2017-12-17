package com.toring.paditechproject.network.model;

import java.util.List;

/**
 * Created by ToRing on 12/17/2017.
 */

public class P1ComboImage {
    private List<P1Image> p1ImageList;

    public P1ComboImage(List<P1Image> p1ImageList) {
        this.p1ImageList = p1ImageList;
    }

    public List<P1Image> getP1ImageList() {
        return p1ImageList;
    }

    public void setP1ImageList(List<P1Image> p1ImageList) {
        this.p1ImageList = p1ImageList;
    }
}
