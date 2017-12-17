package com.toring.paditechproject.network.model;

import java.util.List;

/**
 * Created by ToRing on 12/17/2017.
 */

public class ComboImage {
    private List<P1Image> p1Images;



    public ComboImage(List<P1Image> p1Images) {
        this.p1Images = p1Images;
    }

    public List<P1Image> getP1Images() {
        return p1Images;
    }

    public void setP1Images(List<P1Image> p1Images) {
        this.p1Images = p1Images;
    }
}
