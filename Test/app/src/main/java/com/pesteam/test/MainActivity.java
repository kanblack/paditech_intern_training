package com.pesteam.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Log.e( "onCreate: ", displayMetrics.widthPixels+ "   " + displayMetrics.heightPixels );
        EditableImageView editableImageView = (EditableImageView) findViewById(R.id.abc);
        editableImageView.setImage("https://www.w3schools.com/w3css/img_fjords.jpg");
    }
}
