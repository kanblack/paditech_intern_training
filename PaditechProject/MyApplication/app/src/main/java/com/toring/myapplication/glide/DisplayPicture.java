package com.toring.myapplication.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.toring.myapplication.R;

/**
 * Created by tr on 12/18/17.
 */

public class DisplayPicture {
    public static void displayImage(Context context, String picturePath, ImageView iv) {
        GlideApp.with(context)
                .load(picturePath)
                .centerInside()
                .thumbnail(0.5f)
//                .placeholder(R.drawable.ic_loading)
                .error(R.mipmap.ic_launcher)
                .into(iv);
    }

    public static void displayImageCrop(Context context, String picturePath, ImageView iv) {
        GlideApp.with(context)
                .load(picturePath)
                .centerCrop()
//                .placeholder(R.drawable.ic_loading)
                .error(R.mipmap.ic_launcher)
                .into(iv);
    }
}
