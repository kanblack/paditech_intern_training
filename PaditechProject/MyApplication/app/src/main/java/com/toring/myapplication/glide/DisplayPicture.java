package com.toring.myapplication.glide;

import android.content.Context;
import android.widget.ImageView;

import com.toring.myapplication.R;

/**
 * Created by tr on 12/18/17.
 */

public class DisplayPicture {
    public static void displayImage(Context context, String picturePath, ImageView iv){
        GlideApp.with(context)
                .load(picturePath)
                .placeholder(R.drawable.ic_loading_rotate)
                .error(R.mipmap.ic_launcher)
                .into(iv);
    }
}
