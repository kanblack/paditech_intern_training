package com.toring.paditechproject.model;

/**
 * Created by ToRing on 12/13/2017.
 */

public class Person {
    private String name;
    private boolean on;
    private boolean new_;
    private int bia;
    private int ava;

    public Person(String name, boolean on, boolean new_, int bia, int ava) {
        this.name = name;
        this.on = on;
        this.new_ = new_;
        this.bia = bia;
        this.ava = ava;
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

    public int getBia() {
        return bia;
    }

    public void setBia(int bia) {
        this.bia = bia;
    }

    public int getAva() {
        return ava;
    }

    public void setAva(int ava) {
        this.ava = ava;
    }
}
