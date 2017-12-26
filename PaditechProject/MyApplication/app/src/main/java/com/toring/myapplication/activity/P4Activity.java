package com.toring.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.toring.myapplication.R;
import com.toring.myapplication.glide.DisplayPicture;
import com.toring.myapplication.glide.GlideApp;
import com.toring.myapplication.glide.SaveImage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;

public class P4Activity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tvLink;
    private ImageView ivPopup;
    private ImageView ivPicture;

    private String picturePath;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_p4);

        toolbar = this.findViewById(R.id.toolbar);
        tvLink = this.findViewById(R.id.tv_link);
        ivPicture = this.findViewById(R.id.iv_picture);
        ivPopup = this.findViewById(R.id.popup);

        picturePath = getIntent().getStringExtra(this.getResources().getString(R.string.picture));
        DisplayPicture.displayImage(this, picturePath, ivPicture);
        tvLink.setText(picturePath);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                P4Activity.this.onBackPressed();
            }
        });

        ivPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(P4Activity.this, ivPopup);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.edit) {
                            Intent intent = new Intent(P4Activity.this, P5Activity.class);
                            intent.putExtra(P4Activity.this.getResources().getString(R.string.picture), picturePath);
                            P4Activity.this.startActivity(intent);
                        } else {
                            GlideApp.with(P4Activity.this)
                                    .asBitmap()
                                    .load(picturePath)
                                    .into(new SimpleTarget<Bitmap>() {
                                        @Override
                                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                            SaveImage.saveImage(resource, P4Activity.this, picturePath);
                                        }
                                    });
                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu

            }
        });
    }


}
