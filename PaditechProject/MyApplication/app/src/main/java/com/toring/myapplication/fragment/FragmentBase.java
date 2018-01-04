package com.toring.myapplication.fragment;

import android.support.v4.app.Fragment;

import com.toring.myapplication.adapter.BaseAdapter;

import java.util.List;

/**
 * Created by ToRing on 1/2/2018.
 */

public abstract class FragmentBase extends Fragment {
    protected List<String> pictureList;
    protected NoFacebookFragment noFacebookFragment;
    protected String album;

    public NoFacebookFragment getNoFacebookFragment() {
        return noFacebookFragment;
    }

    public void setNoFacebookFragment(NoFacebookFragment noFacebookFragment) {
        this.noFacebookFragment = noFacebookFragment;
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
