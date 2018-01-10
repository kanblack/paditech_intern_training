package com.toring.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.toring.myapplication.R;
import com.toring.myapplication.glide.DisplayPicture;
import com.toring.myapplication.glide.SaveImage;
import com.toring.myapplication.network.image_object.ImageObject;

import java.util.UUID;

public class P4Activity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tvLink;
    private ImageView ivPopup;
    private ImageView ivPicture;

    private ImageObject imageObject;
    Bitmap bitmap;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_p4);

        toolbar = this.findViewById(R.id.toolbar);
        tvLink = this.findViewById(R.id.tv_link);
        ivPicture = this.findViewById(R.id.iv_picture);
        ivPopup = this.findViewById(R.id.popup);

        toolbar.setTitleTextColor(getResources().getColor(R.color.background_color));
        toolbar.setTitle("Detail");

        imageObject = (ImageObject) getIntent().getSerializableExtra(this.getResources().getString(R.string.picture));

        displayImage();

        if (imageObject.getName() == null || imageObject.getName().isEmpty()) {
            tvLink.setText(imageObject.getUrl());
        } else {
            tvLink.setText(imageObject.getName());
        }

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                P4Activity.this.onBackPressed();
            }
        });

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.loadImage(imageObject.getUrl(), new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                bitmap = loadedImage.copy(Bitmap.Config.ARGB_8888, true);
            }
        });

        ivPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                PopupMenu popup = new PopupMenu(P4Activity.this, ivPopup);
//                //Inflating the Popup using xml file
//                popup.getMenuInflater()
//                        .inflate(R.menu.popup_menu, popup.getMenu());
//
//                //registering popup with OnMenuItemClickListener
//                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    public boolean onMenuItemClick(MenuItem item) {
//                        if (item.getItemId() == R.id.edit) {
//                            Intent intent = new Intent(P4Activity.this, P5Activity.class);
//                            intent.putExtra(P4Activity.this.getResources().getString(R.string.picture), imageObject);
//                            P4Activity.this.startActivity(intent);
//                        } else {
//                            GlideApp.with(P4Activity.this)
//                                    .asBitmap()
//                                    .load(imageObject)
//                                    .into(new SimpleTarget<Bitmap>() {
//                                        @Override
//                                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
//                                            SaveImage.saveImage(resource, P4Activity.this, imageObject);
//                                        }
//                                    });
//                        }
//                        return true;
//                    }
//                });
//
//                popup.show(); //showing popup menu


                final LayoutInflater inflater = (LayoutInflater) P4Activity.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //Inflate the view from a predefined XML layout
                View layout = inflater.inflate(R.layout.popup,
                        (ViewGroup) findViewById(R.id.popup_element));
                // create a 300px width and 470px height PopupWindow
                DisplayMetrics metrics = P4Activity.this.getResources().getDisplayMetrics();
                int w = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, metrics);
                int h = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, metrics);
                final PopupWindow pw = new PopupWindow(layout, w,
                        h, true);
                // display the popup in the center
                pw.setBackgroundDrawable(new BitmapDrawable());
                pw.setOutsideTouchable(true);
                pw.showAsDropDown(ivPopup, 0, 0, Gravity.RIGHT);

                RelativeLayout rlDownLoad = layout.findViewById(R.id.rl_download);
                RelativeLayout rlEdit = layout.findViewById(R.id.rl_edit);

                rlEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pw.dismiss();
                        Intent intent = new Intent(P4Activity.this, P5Activity.class);
                        intent.putExtra(P4Activity.this.getResources().getString(R.string.picture), imageObject);
                        P4Activity.this.startActivity(intent);
                    }
                });

                rlDownLoad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (bitmap == null) {
                            ImageLoader imageLoader = ImageLoader.getInstance();
                            imageLoader.loadImage(imageObject.getUrl(), new SimpleImageLoadingListener() {
                                @Override
                                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                                    bitmap = loadedImage.copy(Bitmap.Config.ARGB_8888, true);
                                    SaveImage.saveImage(bitmap, P4Activity.this, UUID.randomUUID().toString());
                                }
                            });
                        } else {
                            SaveImage.saveImage(bitmap, P4Activity.this, UUID.randomUUID().toString());
                        }
                        pw.dismiss();
                    }
                });
            }
        });
    }

    private void displayImage() {
            DisplayPicture.displayImage(P4Activity.this, imageObject.getUrl(), ivPicture);
    }


}
