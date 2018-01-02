package com.toring.myapplication.fragment;

import android.support.v4.app.Fragment;

import java.util.List;

/**
 * Created by ToRing on 1/2/2018.
 */

public class FragmentBase extends Fragment {
    protected List<String> pictureList;
    protected boolean isFacebook;

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }

    public boolean isFacebook() {
        return isFacebook;
    }

    public void setFacebook(boolean facebook) {
        isFacebook = facebook;
    }
}
