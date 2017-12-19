package com.pesteam.watchimage.ScreenMain;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pesteam.watchimage.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by bangindong on 12/14/2017.
 */

public class AdapterScreen2 extends BaseAdapter {

    private Context context;
    private List<String> list = new ArrayList<>();
    private ImageView imageView;

    void setList(List<String> list) {
        this.list = list;
    }

    public ImageView getImageView() {
        return imageView;
    }

    AdapterScreen2(Context context) {
        this.context = context;
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
        if (view == null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            v = LayoutInflater.from(context).inflate(R.layout.child_grv_screen2,null);
            v.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (displayMetrics.widthPixels)/3));
        } else {
            v = view;
        }

        imageView = v.findViewById(R.id.img_child_grv_screen2);
        Glide.with(context).load(list.get(i)).into(imageView);
        return v;
    }


}
