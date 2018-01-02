package com.pesteam.test;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Adapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SliderAdapter sliderAdapter;
    private Adapet adapet;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        Log.e( "onCreate: ", displayMetrics.widthPixels+ "   " + displayMetrics.heightPixels );
//        EditableImageView editableImageView = (EditableImageView) findViewById(R.id.abc);
//        editableImageView.setImage("https://www.w3schools.com/w3css/img_fjords.jpg");

//        recyclerView = (RecyclerView) findViewById(R.id.recyc);
//
//        adapet = new Adapet();
//        viewPager = (ViewPager) findViewById(R.id.view);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        recyclerView.setAdapter(adapet);
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        ((LinearLayoutManager)recyclerView.getLayoutManager()).scrollToPositionWithOffset(100,displayMetrics.widthPixels/2);
//        sliderAdapter = new SliderAdapter(this);
//
//        viewPager.setAdapter(sliderAdapter);
//        viewPager.addOnPageChangeListener(listener);

    }

    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
