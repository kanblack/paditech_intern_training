package com.toring.paditechproject.model;

import java.util.List;

/**
 * Created by tr on 12/13/17.
 */

public class Noti {
    private List<String> nameList;
    private int action;
    private String time;
    private boolean seen;

    public Noti(List<String> nameList, int action, String time, boolean seen) {
        this.nameList = nameList;
        this.action = action;
        this.time = time;
        this.seen = seen;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
