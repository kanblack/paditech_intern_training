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
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.ln_have_progress)
    LinearLayout ln_have_progress;
    public static final int FRAGMENT_5 = 0;
    public static final int FRAGMENT_51 = 1;
    public static final int FRAGMENT_52 = 2;
    private int what_fragment;
    private android.support.v4.app.FragmentManager fm;
    private FragmentTransaction ft_tran;
    private String img_url;
    private Bitmap image;
    private int position;

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
            position = intent.getIntExtra("position",0);
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
                ln_have_progress.setVisibility(View.GONE);
                progress.setVisibility(View.GONE);

                image = loadedImage;
                Log.e("onLoadingComplete: ", "aaaa" );
                ft_tran.replace(R.id.frag_activity5, new FragmentScreen5());
                what_fragment = FRAGMENT_5;
                ft_tran.addToBackStack(null);
                ft_tran.commitAllowingStateLoss();
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                Log.e("onLoadingFailed: ", "fail" );
            }
        });

    }

    public void checkPermission(Bitmap bitmap){
        image = bitmap;
        if (Build.VERSION.SDK_INT >= 23) {
            if (this.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                saveToInternalStorage(image,position);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

            }
        } else {
            saveToInternalStorage(image,position);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v("Permission: ",  permissions[0] + "was " + grantResults[0]);
            saveToInternalStorage(image,position);
        }
        else {
            Toast.makeText(this,"Bạn không cho lưu vào thẻ nhớ, không thể lưu được",Toast.LENGTH_LONG).show();
        }
    }

    private void saveToInternalStorage(Bitmap bitmapImage, int position){

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
        String fname = "Image"+position+".jpg";
        File file = new File(path + fname);
        Log.e( "saveToInternalStorage: ", file.toString() );

        try {
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            FileOutputStream out = new FileOutputStream(file);
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
