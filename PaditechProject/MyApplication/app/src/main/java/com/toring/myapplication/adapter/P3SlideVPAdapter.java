package com.toring.myapplication.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.toring.myapplication.item_fragment.ItemP3VPFragment;

import java.util.List;

/**
 * Created by tr on 12/18/17.
 */

public class P3SlideVPAdapter extends FragmentStatePagerAdapter {
    private Context context;
    private List<String> pictureList;

    public P3SlideVPAdapter(FragmentManager fm, Context context, List<String> pictureList) {
        super(fm);
        this.context = context;
        this.pictureList = pictureList;
    }

    @Override
    public Fragment getItem(int position) {
        ItemP3VPFragment itemP3VPFragment = new ItemP3VPFragment();
        itemP3VPFragment.setPicturePath(pictureList.get(position));
        return itemP3VPFragment;
    }

    @Override
    public int getCount() {
        return pictureList.size();
    }
}
