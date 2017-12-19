package com.pesteam.watchimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
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

import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Screen4Activity extends AppCompatActivity {

    @BindView(R.id.bt_im_menu_screen4)
    ImageButton im_bt_menu;
    @BindView(R.id.img_big_screen4)
    ImageView img_big;
    private String url_img_big;
    private Bitmap image;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen4);
        ButterKnife.bind(this);
        getImg();
        creatMenu();
        loadImage();
        setView();
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
            }
        });

    }

    private void getImg() {
        Intent intent = getIntent();
        url_img_big = intent.getStringExtra("img_url");
        position = intent.getIntExtra("position",0);
    }

    private void setView() {
        Glide.with(this).load(url_img_big).into(img_big);
    }

    private void creatMenu() {
        im_bt_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(Screen4Activity.this, im_bt_menu);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu_screen4, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.edit_screen4:
                                Toast.makeText(Screen4Activity.this,"sdsdsd",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Screen4Activity.this, Screen5Activity.class);
                                intent.putExtra("img_url",url_img_big);
                                startActivity(intent);
                                break;
                            case R.id.download_screen4:
                                if(image == null){
                                    Toast.makeText(Screen4Activity.this,"Đang down ảnh, bạn chờ tí nha !!",Toast.LENGTH_LONG).show();
                                } else {
                                    saveToInternalStorage(image,position);
                                }
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

    private void saveToInternalStorage(Bitmap bitmapImage, int position){

        File path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        String fname = "Image"+position+".jpg";
        File file = new File(path, fname);
        Log.e( "saveToInternalStorage: ", file.toString() );

        try {
            path.mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
