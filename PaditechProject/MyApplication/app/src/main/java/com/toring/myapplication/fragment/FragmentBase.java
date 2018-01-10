package com.toring.myapplication.fragment;

import android.support.v4.app.Fragment;

import com.toring.myapplication.network.image_object.ImageObject;

import java.util.List;

/**
 * Created by ToRing on 1/2/2018.
 */

public abstract class FragmentBase extends Fragment {
    protected List<ImageObject> imageObjectList;
    protected ViewAllImageFragment viewAllImageFragment;
    protected String album;

    public ViewAllImageFragment getViewAllImageFragment() {
        return viewAllImageFragment;
    }

    public void setViewAllImageFragment(ViewAllImageFragment viewAllImageFragment) {
        this.viewAllImageFragment = viewAllImageFragment;
    }

    public List<ImageObject> getImageObjectList() {
        return imageObjectList;
    }

    public void setImageObjectList(List<ImageObject> imageObjectList) {
        this.imageObjectList = imageObjectList;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public abstract void loadMore(int start, int end);
}
