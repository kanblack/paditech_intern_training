package com.toring.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.toring.myapplication.fragment.FragmentBase;
import com.toring.myapplication.glide.DisplayPicture;
import com.toring.myapplication.network.image_object.ImageObject;

import java.util.List;

/**
 * Created by ToRing on 1/2/2018.
 */

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.VH> {
    protected FragmentBase context;
    protected List<ImageObject> imageObjectList;
    protected String album;

    public BaseAdapter(FragmentBase context, List<ImageObject> imageObjectList, String album) {
        this.context = context;
        this.imageObjectList = imageObjectList;
        this.album = album;
    }

    public List<ImageObject> getImageObjectList() {
        return imageObjectList;
    }

    public void setImageObjectList(List<ImageObject> imageObjectList) {
        this.imageObjectList = imageObjectList;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bindView(position);

        if (position == imageObjectList.size() - 1 && album != null){
            context.getViewAllImageFragment().loadMorePhoto();
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
            DisplayPicture.displayImageCrop(context.getContext(), imageObjectList.get(position).getUrl(), ivPicture);
        }
    }
}
