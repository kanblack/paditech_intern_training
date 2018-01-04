package com.toring.myapplication.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.toring.myapplication.fragment.FragmentBase;
import com.toring.myapplication.glide.DisplayPicture;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by ToRing on 1/2/2018.
 */

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.VH> {
    protected FragmentBase context;
    protected List<String> pictureList;
    protected String album;

    public BaseAdapter(FragmentBase context, List<String> pictureList, String album) {
        this.context = context;
        this.pictureList = pictureList;
        this.album = album;
    }

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bindView(position);

        if (position == pictureList.size() - 1 && album != null){
            context.getNoFacebookFragment().loadMorePhoto();
        }

        Log.e("LOADMORE", "onBindViewHolder: " + position );
    }

    public class VH extends RecyclerView.ViewHolder {
        protected View view;
        protected ImageView ivPicture;

        public VH(View itemView) {
            super(itemView);
        }

        public void bindView(final int position) {
            DisplayPicture.displayImageCrop(context.getContext(), pictureList.get(position), ivPicture);
        }
    }
}
