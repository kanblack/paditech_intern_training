package com.toring.paditechproject.model;

/**
 * Created by ToRing on 12/13/2017.
 */

public class P5Person {
    private String name;
    private boolean on;
    private boolean new_;
    private int profile;
    private int acvatar;

    public P5Person(String name, boolean on, boolean new_, int profile, int acvatar) {
        this.name = name;
        this.on = on;
        this.new_ = new_;
        this.profile = profile;
        this.acvatar = acvatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public boolean isNew_() {
        return new_;
    }

    public void setNew_(boolean new_) {
        this.new_ = new_;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public int getAcvatar() {
        return acvatar;
    }

    public void setAcvatar(int acvatar) {
        this.acvatar = acvatar;
    }
}
