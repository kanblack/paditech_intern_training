package com.toring.myapplication.network.image_object;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by ToRing on 1/10/2018.
 */

public class ImageObject extends RealmObject implements Serializable{
    private String name;
    private String url;
    private int width;
    private int height;

    public ImageObject() {
        width = -1;
        height = -1;
    }

    public ImageObject(String url, int width, int height) {
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public ImageObject(String url) {
        this.url = url;
    }

    public ImageObject(String name, String url, int width, int height) {
        this.name = name;
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
