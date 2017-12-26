package com.toring.myapplication.glide;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.toring.myapplication.activity.P4Activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by ToRing on 12/26/2017.
 */

public class SaveImage {
    public static String saveImage(Bitmap image, Context context, String picturePath) {
        String savedImagePath = null;

        String s = picturePath.replace(".", "_");
        s = s.replace("/", "_");
        s = s.replace("-", "_");
        s = s.replace(":", "_");


        String imageFileName = UUID.randomUUID().toString() + ".jpg";
        File storageDir = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                        + "/my_app");
        boolean success = true;
        if (!storageDir.exists()) {
            success = storageDir.mkdirs();
        }
        if (success) {
            File imageFile = new File(storageDir, imageFileName);
            savedImagePath = imageFile.getAbsolutePath();
            try {
                OutputStream fOut = new FileOutputStream(imageFile);
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                fOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Add the image to the system gallery
            galleryAddPic(savedImagePath, context);
            Toast.makeText(context, "IMAGE SAVED", Toast.LENGTH_LONG)
                    .show();
        }
        return savedImagePath;
    }

    private static void galleryAddPic(String imagePath, Context context) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(imagePath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }
}
