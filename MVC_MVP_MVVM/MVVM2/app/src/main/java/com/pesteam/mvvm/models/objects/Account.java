package com.pesteam.mvvm.models.objects;

/**
 * Created by bangindong on 1/5/2018.
 */

public class Account {

    private String name;
    private String pass;

    public Account(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public Account() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
