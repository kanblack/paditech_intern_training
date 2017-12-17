package com.toring.paditechproject.model;

/**
 * Created by tr on 12/12/17.
 */

public class P3Task {
    private String name;
    private String color;

    public P3Task(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
