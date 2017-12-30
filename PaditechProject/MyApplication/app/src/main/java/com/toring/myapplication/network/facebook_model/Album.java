package com.toring.myapplication.network.facebook_model;

/**
 * Created by ToRing on 12/30/2017.
 */

public class Album {
    String id;
    String name;

    public Album(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Album() {
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
}
