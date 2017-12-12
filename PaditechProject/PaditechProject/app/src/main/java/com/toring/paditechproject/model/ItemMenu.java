package com.toring.paditechproject.model;

import android.content.Intent;

/**
 * Created by ToRing on 12/11/2017.
 */

public class ItemMenu {
    private String name;
    private String description;
    private Intent intent;

    public ItemMenu(String name, String description, Intent intent) {
        this.name = name;
        this.description = description;
        this.intent = intent;
    }

    public ItemMenu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }
}
