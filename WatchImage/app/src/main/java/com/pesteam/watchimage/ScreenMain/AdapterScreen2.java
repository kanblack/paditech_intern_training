package com.pesteam.watchimage.ScreenMain;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.pesteam.watchimage.R;
import com.pesteam.watchimage.facebook.Albums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bangindong on 12/14/2017.
 */

public class AdapterScreen2 extends BaseAdapter {

    private FragmentScreen2 fragmentScreen2;
    private List<Albums> list = new ArrayList<>();
    private ImageView imageView;
    private TextView textView;

    void setList(List<Albums> list) {
        this.list = list;
    }

    public ImageView getImageView() {
        return imageView;
    }

    AdapterScreen2(FragmentScreen2 fragmentScreen2) {
        this.fragmentScreen2 = fragmentScreen2;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        fragmentScreen2.getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (view == null) {
            v = LayoutInflater.from(fragmentScreen2.getActivity()).inflate(R.layout.child_grv_screen2,null);
            v.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (displayMetrics.widthPixels)/3));
        } else {
            v = view;
        }

        imageView = v.findViewById(R.id.img_child_grv_screen2);
        textView = v.findViewById(R.id.tex_title_child_grv_screen2);
        textView.setText(list.get(i).getName());
        Glide.with(fragmentScreen2)
                .load(list.get(i).getUrlCoverPhoto())
                .apply(new RequestOptions().placeholder(R.drawable.image))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        fragmentScreen2.progress.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        fragmentScreen2.progress.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(imageView);
        return v;
    }


}
