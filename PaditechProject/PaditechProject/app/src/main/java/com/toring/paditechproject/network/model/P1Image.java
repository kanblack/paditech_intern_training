package com.toring.paditechproject.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class P1Image {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * No args constructor for use in serialization
     */
    public P1Image() {
    }

    /**
     * @param title
     * @param shortDescription
     * @param url
     */
    public P1Image(String title, String shortDescription, String url) {
        super();
        this.title = title;
        this.shortDescription = shortDescription;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}