package com.pesteam.watchimage.saveImage;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by bangindong on 12/21/2017.
 */

public class SaveImage {

    private Context context;
    private String img_url;
    private Bitmap image;
    private int position;
    private int whatTypeSave;
    public static final int NO_FRAME = 0;
    public static final int FRAME = 1;

    public Bitmap getImage() {
        return image;
    }

    public int getPosition() {
        return position;
    }

    public SaveImage(Context context, String img_url, int position, int whatTypeScreen) {
        this.context = context;
        this.img_url = img_url;
        this.position = position;
        this.whatTypeSave = whatTypeScreen;
    }

    void checkPermission(Bitmap bitmap){
        image = bitmap;
        Log.e("checkPermission: ", "sdsđsds" );
        if (Build.VERSION.SDK_INT >= 23) {
            if (context.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e( "checkPermission: ", "ddddddddddddd" );
                saveToInternalStorage(image,position);
            } else {
                Log.e( "checkPermission: ", "ddddddddddddd" );
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

            }
        } else {
            saveToInternalStorage(image,position);
        }
    }


    public void saveToInternalStorage(Bitmap bitmapImage, int position){

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

    public void loadImage(final Bitmap bitmapPath) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .build();
        ImageLoader.getInstance().init(config);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.loadImage(img_url, new SimpleImageLoadingListener(){
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                image = loadedImage;
                switch (whatTypeSave){
                    case NO_FRAME:
                        checkPermission(image);
                        break;
                    case FRAME:
                        saveImageScreenHasFrame(bitmapPath);
                        break;
                }
                Log.e("onLoadingComplete: ", "aaaa" );
                ((Activity) context).finish();
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                Log.e("onLoadingFailed: ", "fail" );
            }
        });
    }


    private void saveImageScreenHasFrame(Bitmap bitmapPath){
        Log.e("saveImageScr: ", " " );
        Bitmap bitmapPathScale = Bitmap.createScaledBitmap(bitmapPath,image.getWidth(),image.getHeight(),true);
        Bitmap bitmapBackground = Bitmap.createBitmap(image.getWidth(),image.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapBackground);
        canvas.drawBitmap(image,0,0,null);
        canvas.drawBitmap(bitmapPathScale,0,0,null);
        checkPermission(bitmapBackground);
    }


}
