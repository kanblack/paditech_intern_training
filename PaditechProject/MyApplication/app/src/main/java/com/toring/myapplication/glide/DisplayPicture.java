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
                .error(R.mipmap.ic_launcher)
                .into(iv);
    }

    public static void displayImage(Context context, int picturePath, ImageView iv) {
        GlideApp.with(context)
                .load(picturePath)
                .centerInside()
                .thumbnail(0.5f)
                .error(R.mipmap.ic_launcher)
                .into(iv);
    }

    public static void displayImageCrop(Context context, String picturePath, ImageView iv) {
        GlideApp.with(context)
                .load(picturePath)
                .centerCrop()
                .error(R.mipmap.ic_launcher)
                .into(iv);
    }

    public static void displayImageCrop(Context context, int picturePath, ImageView iv) {
        GlideApp.with(context)
                .load(picturePath)
                .centerCrop()
                .error(R.mipmap.ic_launcher)
                .into(iv);
    }

    public static void displayImageCircleCrop(Context context, String picturePath, ImageView iv) {
        GlideApp.with(context)
                .load(picturePath)
                .circleCrop()
                .error(R.mipmap.ic_launcher)
                .into(iv);
    }

    public static void displayImageCircleCrop(Context context, int picturePath, ImageView iv) {
        GlideApp.with(context)
                .load(picturePath)
                .circleCrop()
                .error(R.mipmap.ic_launcher)
                .into(iv);
    }
}
