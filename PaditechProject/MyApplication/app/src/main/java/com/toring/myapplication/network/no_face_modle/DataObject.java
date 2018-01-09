package com.toring.myapplication.network.no_face_modle;

import io.realm.RealmObject;

/**
 * Created by ToRing on 12/28/2017.
 */

public class DataObject extends RealmObject {
    String url;

    public DataObject() {
    }

    public DataObject(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
