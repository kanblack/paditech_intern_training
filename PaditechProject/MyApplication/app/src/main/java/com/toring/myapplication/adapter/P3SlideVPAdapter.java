package com.toring.myapplication.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.toring.myapplication.item_fragment.ItemP3VPFragment;
import com.toring.myapplication.network.image_object.ImageObject;

import java.util.List;

/**
 * Created by tr on 12/18/17.
 */

public class P3SlideVPAdapter extends FragmentStatePagerAdapter {
    private Context context;
    private List<ImageObject> imageObjectList;
    private boolean isFacebook = false;

    public P3SlideVPAdapter(FragmentManager fm, Context context, List<ImageObject> imageObjectList, boolean isFacebook) {
        super(fm);
        this.context = context;
        this.imageObjectList = imageObjectList;
        this.isFacebook = isFacebook;
    }

    @Override
    public Fragment getItem(int position) {
        ItemP3VPFragment itemP3VPFragment = new ItemP3VPFragment();
        itemP3VPFragment.setImageObject(imageObjectList.get(position));
        itemP3VPFragment.setFacebook(isFacebook);
        return itemP3VPFragment;
    }

    @Override
    public int getCount() {
        return imageObjectList.size();
    }
}
