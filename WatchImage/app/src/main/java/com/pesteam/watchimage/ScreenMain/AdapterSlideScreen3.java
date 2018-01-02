package com.pesteam.watchimage.ScreenMain;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pesteam.watchimage.R;
import com.pesteam.watchimage.ScreenMain.MainActivity;
import com.pesteam.watchimage.facebook.Albums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bangindong on 12/28/2017.
 */

public class AdapterSlideScreen3 extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Albums> listImg = new ArrayList<>();

    public AdapterSlideScreen3(Context context) {
        this.context = context;
    }

    public void setListImg(List<Albums> listImg) {
        this.listImg = listImg;
    }

    @Override
    public int getCount() {
        return listImg.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.child_view_paper,container,false);

        ImageView imgView = (ImageView) view.findViewById(R.id.img_view_paper);

        Glide.with(context).load(listImg.get(position).getUrlCoverPhoto()).into(imgView);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
