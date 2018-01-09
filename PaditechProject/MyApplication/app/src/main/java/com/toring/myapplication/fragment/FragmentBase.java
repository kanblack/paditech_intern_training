package com.toring.myapplication.fragment;

import android.support.v4.app.Fragment;

import java.util.List;

/**
 * Created by ToRing on 1/2/2018.
 */

public abstract class FragmentBase extends Fragment {
    protected List<String> pictureList;
    protected ViewAllImageFragment viewAllImageFragment;
    protected String album;

    public ViewAllImageFragment getViewAllImageFragment() {
        return viewAllImageFragment;
    }

    public void setViewAllImageFragment(ViewAllImageFragment viewAllImageFragment) {
        this.viewAllImageFragment = viewAllImageFragment;
    }

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public abstract void loadMore();
}
