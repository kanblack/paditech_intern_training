package com.toring.paditechproject.model;

/**
 * Created by ToRing on 12/14/2017.
 */

public class Message {
    private boolean online;
    private int avatar;
    private String name;
    private String mes;
    private boolean seen;
    private boolean my_seen;
    private boolean make_friends;
    private String time;

    public Message(boolean online, int avatar, String name, String mes, boolean seen, boolean my_seen, boolean make_friends, String time) {
        this.online = online;
        this.avatar = avatar;
        this.name = name;
        this.mes = mes;
        this.seen = seen;
        this.my_seen = my_seen;
        this.make_friends = make_friends;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public boolean isMake_friends() {
        return make_friends;
    }

    public void setMake_friends(boolean make_friends) {
        this.make_friends = make_friends;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isMy_seen() {
        return my_seen;
    }

    public void setMy_seen(boolean my_seen) {
        this.my_seen = my_seen;
    }
}
