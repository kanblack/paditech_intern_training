package com.toring.myapplication.activity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.toring.myapplication.R;
import com.toring.myapplication.customvie.DrawingView;
import com.toring.myapplication.fragment.P5DrawFragment;
import com.toring.myapplication.fragment.P5ImageFragment;
import com.toring.myapplication.glide.DisplayPicture;
import com.toring.myapplication.glide.GlideApp;
import com.toring.myapplication.glide.SaveImage;
import com.toring.myapplication.manager.ScreenManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class P5Activity extends AppCompatActivity implements View.OnClickListener {
    private String picturePath;
    private DrawingView ivPicture;

    private Toolbar toolbar;
    private RelativeLayout rlOption, rlDraw;

    private View.OnClickListener changeColor;
    private View.OnClickListener changeImage;

    private TextView tvDone;
    private View currentViewDraw, currentViewImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p5);

        picturePath = getIntent().getStringExtra(this.getResources().getString(R.string.picture));

        ivPicture = this.findViewById(R.id.iv_picture);
        toolbar = this.findViewById(R.id.toolbar);
        rlOption = this.findViewById(R.id.rl_option);
        rlDraw = this.findViewById(R.id.rl_draw);

        tvDone = this.findViewById(R.id.tv_done);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.loadImage(picturePath, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                Bitmap bitmap = loadedImage.copy(Bitmap.Config.ARGB_8888, true);
                ivPicture.setImage(bitmap);
            }
        });

        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ScreenManager.canBackFragment(P5Activity.this)) {
                    ivPicture.setCountPaths(0);
                    ivPicture.setCanDrawImage(false);
                    ivPicture.setCanDrawLine(false);
                    ivPicture.saveImage();
                    tvDone.setText(getResources().getString(R.string.save));
                    ScreenManager.backFragment(P5Activity.this);
                } else {
                    P5Activity.super.onBackPressed();
//                    ivPicture.setDrawingCacheEnabled(true);
                    //attempt to save
//                    String imgSaved = MediaStore.Images.Media.insertImage(
//                            getContentResolver(), ivPicture.getDrawingCache(),
//                            UUID.randomUUID().toString() + ".png", "drawing");
//                    Log.e("", "onClick: ");
                    ImageLoader imageLoader = ImageLoader.getInstance();
                    if (ivPicture.getImage() == null) {
                        imageLoader.loadImage(picturePath, new SimpleImageLoadingListener() {
                            @Override
                            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                                Bitmap bitmap = loadedImage.copy(Bitmap.Config.ARGB_8888, true);
                                SaveImage.saveImage(ivPicture.get(bitmap), P5Activity.this, UUID.randomUUID().toString());
                            }
                        });
                    } else {
                        SaveImage.saveImage(ivPicture.get(), P5Activity.this, UUID.randomUUID().toString());
                    }
                }
            }
        });

        changeColor = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivPicture.setColor((Integer) view.getTag());
                if (currentViewDraw != null) {
                    currentViewDraw.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
                view.setBackgroundColor(getResources().getColor(R.color.background_color));
                currentViewDraw = view;
            }
        };

        changeImage = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivPicture.setBitmap((Integer) view.getTag());
                if (currentViewImage != null) {
                    currentViewImage.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
                view.setBackgroundColor(getResources().getColor(R.color.background_color));
                currentViewImage = view;
            }
        };

        setData();
    }

    private void setData() {
            DisplayPicture.displayImage(P5Activity.this
                    , picturePath, ivPicture);

        ivPicture.post(new Runnable() {
            @Override
            public void run() {
                GlideApp.with(P5Activity.this)
                        .asBitmap()
                        .load(picturePath)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                float n = (resource.getWidth() / (float) resource.getHeight());
                                float m = (ivPicture.getWidth() / (float) ivPicture.getHeight());

                                ViewGroup.LayoutParams params = ivPicture.getLayoutParams();
                                // image fit width
                                if (n > m) {
                                    float k = (ivPicture.getWidth() / (float) resource.getWidth());
                                    params.height = (int) (resource.getHeight() * k);
                                    params.width = ivPicture.getWidth();
                                } else {
                                    float k = (ivPicture.getHeight() / (float) resource.getHeight());
                                    params.width = (int) (resource.getWidth() * k);
                                    params.height = ivPicture.getHeight();
                                }
                                ivPicture.setLayoutParams(params);
                            }
                        });
            }
        });


        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setTitleTextColor(getResources().getColor(R.color.background_color));
        toolbar.setTitle(this.getResources().getString(R.string.edit));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                P5Activity.this.onBackPressed();
            }
        });

        rlDraw.setOnClickListener(this);
        rlOption.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        if (ScreenManager.canBackFragment(P5Activity.this)) {

            AlertDialog.Builder builder = new AlertDialog.Builder(P5Activity.this, R.style.dialog);
//            builder.setTitle("Back");
            builder.setMessage("Những thay đổi sẽ không được lưu lại. Bạn chắc chắn?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ivPicture.setCanDrawLine(false);
                    ivPicture.setCanDrawImage(false);
                    ivPicture.reset();
                    tvDone.setText(getResources().getString(R.string.save));

                    ScreenManager.backFragment(P5Activity.this);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_draw: {
                P5DrawFragment p5DrawFragment = new P5DrawFragment();
                p5DrawFragment.setOnClickListener(changeColor);
                ScreenManager.replaceFragment(this, R.id.bottom_bar,
                        p5DrawFragment, true);
                ivPicture.setCanDrawLine(true);
                break;
            }
            case R.id.rl_option:
                P5ImageFragment p5ImageFragment = new P5ImageFragment();
                p5ImageFragment.setOnClickListener(changeImage);
                ScreenManager.replaceFragment(this, R.id.bottom_bar,
                        p5ImageFragment, true);
                ivPicture.setCanDrawImage(true);
                break;
        }

        tvDone.setText(getResources().getString(R.string.done));
    }
}
