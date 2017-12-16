package com.toring.paditechproject.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class P1Section {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("p1Images")
    @Expose
    private List<P1Image> p1Images = null;

    /**
     * No args constructor for use in serialization
     */
    public P1Section() {
    }

    /**
     * @param title
     * @param p1Images
     */
    public P1Section(String title, List<P1Image> p1Images) {
        super();
        this.title = title;
        this.p1Images = p1Images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<P1Image> getP1Images() {
        return p1Images;
    }

    public void setP1Images(List<P1Image> p1Images) {
        this.p1Images = p1Images;
    }

}