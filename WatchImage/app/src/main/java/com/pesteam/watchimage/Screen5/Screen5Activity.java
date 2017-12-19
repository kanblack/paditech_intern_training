package com.pesteam.watchimage.Screen5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.pesteam.watchimage.R;
import com.pesteam.watchimage.ScreenMain.FragmentScreen1;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Screen5Activity extends AppCompatActivity {

    @BindView(R.id.icon_back_img_screen5)
    ImageButton icon_back_img;
    private android.support.v4.app.FragmentManager fm;
    private FragmentTransaction ft_tran;
    private String img_url;
    private Bitmap image;

    public Bitmap getImage() {
        return image;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen5);
        ButterKnife.bind(this);
        getData();
        start();
        loadImage();
    }

    private void getData() {
            Intent intent = getIntent();
            img_url = intent.getStringExtra("img_url");
    }

    @SuppressLint("CommitTransaction")
    private void start() {
        fm = getSupportFragmentManager();
        ft_tran = fm.beginTransaction();

    }

    private void loadImage() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .build();
        ImageLoader.getInstance().init(config);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.loadImage(img_url, new SimpleImageLoadingListener(){
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                image = loadedImage;
                Log.e("onLoadingComplete: ", "aaaa" );
                ft_tran.replace(R.id.frag_activity5, new FragmentScreen5());
                ft_tran.addToBackStack(null);
                ft_tran.commitAllowingStateLoss();
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                Log.e("onLoadingFailed: ", "fail" );
            }
        });

    }
}
