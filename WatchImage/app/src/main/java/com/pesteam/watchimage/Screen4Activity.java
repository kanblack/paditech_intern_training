package com.pesteam.watchimage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.pesteam.watchimage.Screen5.Screen5Activity;
import com.pesteam.watchimage.ScreenMain.MainActivity;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Screen4Activity extends AppCompatActivity {

    @BindView(R.id.bt_im_menu_screen4)
    ImageButton im_bt_menu;
    @BindView(R.id.img_big_screen4)
    ImageView img_big;
    @BindView(R.id.bt_im_back_screen4)
    ImageButton im_bt_back;
    private String url_img_big;
    private Bitmap image;
    private int position;
    private Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen4);
        ButterKnife.bind(this);
        getImg_url();
        setView();
        actionButton();
    }

    private void actionButton() {
        im_bt_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatMenu();
            }
        });

        im_bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void loadImage() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .build();
        ImageLoader.getInstance().init(config);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.loadImage(url_img_big, new SimpleImageLoadingListener(){
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                image = loadedImage;
                checkPermission();
                Toast.makeText(Screen4Activity.this, "Đã down xong", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void getImg_url() {
        Intent intent = getIntent();
        url_img_big = intent.getStringExtra("img_url");
        position = intent.getIntExtra("position",0);
    }

    private void setView() {
        Glide.with(this).load(url_img_big).into(img_big);
    }

    private void creatMenu() {
        PopupMenu popupMenu = new PopupMenu(Screen4Activity.this, im_bt_menu);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu_screen4, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.edit_screen4:
                        Intent intent = new Intent(Screen4Activity.this, Screen5Activity.class);
                        intent.putExtra("img_url",url_img_big);
                        intent.putExtra("position",position);
                        startActivity(intent);
                        break;
                    case R.id.download_screen4:
                        loadImage();
                        Log.e( "onMenuItemClick: ", " chờ ảnh" );
                        Toast.makeText(Screen4Activity.this, "Đang down ảnh, bạn chờ tí nha !!", Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });
        popupMenu.show();

    }

    public void checkPermission(){
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

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() +File.separator;
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

    @Override
    public void onBackPressed() {
        finish();

    }


}
