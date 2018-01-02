package com.pesteam.watchimage.ScreenMain;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pesteam.watchimage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bangindong on 12/14/2017.
 */

public class FragmentScreen3 extends android.support.v4.app.Fragment {

    @BindView(R.id.rcv_screen3)
    RecyclerView rcv_screen3;
    @BindView(R.id.view_paper_screen3)
    ViewPager slide;
    private AdapterScreen3 adapter = new AdapterScreen3(this);
    private MainActivity mainActivity;
    private AdapterSlideScreen3 slideScreen3;
    private GestureDetector gestureDetector;
    private int SWIPE_THRESHOLD = 100;
    private int SWIPE_VELOCITY_THRESHOLD = 100;
    public static final int LEFT_TO_RIGHT = 0;
    public static final int RIGHT_TO_LEFT = 1;
    private int positionHere = 0;
    private DisplayMetrics displayMetrics = new DisplayMetrics();

    public DisplayMetrics getDisplayMetrics() {
        return displayMetrics;
    }

    public int getPositionHere() {
        return positionHere;
    }


    public void setPositionHere(int positionHere) {
        this.positionHere = positionHere;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_3, container, false);
        ButterKnife.bind(this, view);
        mainActivity.setWhatFragment(MainActivity.FRAG_3);
//        flingToNewImage();
        changeFragment();
        getData();
        return view;
    }

//    private class myGesture extends GestureDetector.SimpleOnGestureListener {
//
//        private FragmentScreen3 fragmentScreen3;
//
//        myGesture(FragmentScreen3 fragmentScreen3) {
//            this.fragmentScreen3 = fragmentScreen3;
//        }

//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            if (e2.getX() - e1.getX() > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
//                flingImage(nextPosition(LEFT_TO_RIGHT));
//            }
//
//            if (e1.getX() - e2.getX() > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
//                flingImage(nextPosition(RIGHT_TO_LEFT));
//            }
//            return super.onFling(e1, e2, velocityX, velocityY);
//        }
//


//    @SuppressLint("ClickableViewAccessibility")
//    private void flingToNewImage() {
//        gestureDetector = new GestureDetector(this.getContext(), new myGesture(this));
//        big_img.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                gestureDetector.onTouchEvent(motionEvent);
//                return true;
//            }
//        });
//    }

    //    private int nextPosition(int whatAction){
//        int a = 0;
//        switch (whatAction){
//            case RIGHT_TO_LEFT:
//                if(positionHere == mainActivity.getUrl_image().size()-1){
//                    a = 0;
//                } else {
//                    a = positionHere+1;
//                }
//                break;
//            case LEFT_TO_RIGHT:
//                if(positionHere == 0){
//                    a = mainActivity.getUrl_image().size()-1;
//                }
//                else{
//                    a = positionHere-1;
//                }
//                break;
//        }
//        return a;
//    }
    private void getData() {
        positionHere = mainActivity.getAlbums().size() * 100;
        adapter.setList_url_img(mainActivity.getAlbums());
        adapter.setImg_chose(positionHere);
        rcv_screen3.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcv_screen3.setAdapter(adapter);
        mainActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        ((LinearLayoutManager) rcv_screen3.getLayoutManager()).scrollToPositionWithOffset(positionHere, displayMetrics.widthPixels / 3);
        slideScreen3 = new AdapterSlideScreen3(this.getContext());
        slideScreen3.setListImg(mainActivity.getAlbums());
        slideScreen3.notifyDataSetChanged();
        slide.setAdapter(slideScreen3);
        slide.addOnPageChangeListener(listener);
    }

    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position > (positionHere % (mainActivity.getAlbums().size()))) {
                positionHere = positionHere + 1;
                ((LinearLayoutManager) rcv_screen3.getLayoutManager()).scrollToPositionWithOffset(positionHere, displayMetrics.widthPixels / 3);
            } else if (position < (positionHere % (mainActivity.getAlbums().size()))) {
                positionHere = positionHere - 1;
                ((LinearLayoutManager) rcv_screen3.getLayoutManager()).scrollToPositionWithOffset(positionHere, displayMetrics.widthPixels / 3);
            }
            flingImage(positionHere);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void flingImage(final int position_img) {
        adapter.setImg_chose(position_img);
        final int finalNextPosition1 = position_img;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                for (int i = finalNextPosition1 - 5; i < finalNextPosition1 +5; i++) {
                    if (rcv_screen3.findViewHolderForAdapterPosition(i) != null) {
                        if (i != finalNextPosition1) {
                            AdapterScreen3.ChildHolder childHolder = (AdapterScreen3.ChildHolder) rcv_screen3.findViewHolderForLayoutPosition(i);
                            childHolder.changeColor(childHolder, AdapterScreen3.ChildHolder.CHANGE_TO_MOREGREYWHITE);
                        } else {
                            AdapterScreen3.ChildHolder childHolder = (AdapterScreen3.ChildHolder) rcv_screen3.findViewHolderForLayoutPosition(i);
                            adapter.setChildHolder(childHolder);
                            childHolder.changeColor(childHolder, AdapterScreen3.ChildHolder.CHANGE_TO_GREYWHITE);
                        }
                    }
                }
            }
        }, 50);

    }


    public void changeFragment() {
        mainActivity.bt_change_frag.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CommitTransaction")
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frag_screen123, new FragmentScreen1()).addToBackStack(null).commit();
                mainActivity.bt_change_frag.setImageResource(R.drawable.icon_tool_bar_screen1);
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            this.mainActivity = (MainActivity) context;
        }
    }
}
