package com.pesteam.watchimage.Screen5;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.pesteam.watchimage.R;
import com.pesteam.watchimage.ScreenMain.FragmentScreen1;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Screen5Activity extends AppCompatActivity {

    @BindView(R.id.icon_back_img_screen5)
    ImageButton icon_back_img;
    @BindView(R.id.icon_accept_screen5)
    ImageButton icon_accept;
    private android.support.v4.app.FragmentManager fm;
    private FragmentTransaction ft_tran;
    private String img_url;
    private int position;

    public int getPosition() {
        return position;
    }

    public String getImg_url() {
        return img_url;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen5);
        ButterKnife.bind(this);
        getData();
        start();
        changefragment();
    }

    private void changefragment() {
        ft_tran.replace(R.id.frag_activity5, new FragmentScreen5());
        ft_tran.addToBackStack(null);
        ft_tran.commitAllowingStateLoss();
    }


    private void getData() {
            Intent intent = getIntent();
            img_url = intent.getStringExtra("img_url");
            position = intent.getIntExtra("position",0);
    }

    @SuppressLint("CommitTransaction")
    private void start() {
        fm = getSupportFragmentManager();
        ft_tran = fm.beginTransaction();

    }




}
