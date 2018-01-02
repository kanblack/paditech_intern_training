package com.pesteam.watchimage.facebook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bangindong on 1/1/2018.
 */

public class Albums {
    private String id;
    private String name;
    private String urlCoverPhoto;
    private int count;

    public Albums(String id, String name, String urlCoverPhoto, int count) {
        this.id = id;
        this.name = name;
        this.urlCoverPhoto = urlCoverPhoto;
        this.count = count;
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

    public String getUrlCoverPhoto() {
        return urlCoverPhoto;
    }

    public void setUrlCoverPhoto(String urlCoverPhoto) {
        this.urlCoverPhoto = urlCoverPhoto;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
