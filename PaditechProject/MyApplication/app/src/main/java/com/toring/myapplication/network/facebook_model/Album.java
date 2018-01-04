package com.toring.myapplication.network.facebook_model;

/**
 * Created by ToRing on 12/30/2017.
 */

public class Album {
    String id;
    String name;
    String url;
    int photoCount;

    public Album() {
    }

    public Album(String id, String name, String url, int photoCount) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.photoCount = photoCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(int photoCount) {
        this.photoCount = photoCount;
    }
}
