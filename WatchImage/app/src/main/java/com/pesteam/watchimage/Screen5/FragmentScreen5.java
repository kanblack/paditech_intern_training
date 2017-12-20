package com.pesteam.watchimage.Screen5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.pesteam.watchimage.R;
import com.pesteam.watchimage.ScreenMain.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bangindong on 12/18/2017.
 */

public class FragmentScreen5 extends Fragment {

    private Screen5Activity mainActivity;
    @BindView(R.id.img_screen5)
    ImageView img;
    @BindView(R.id.bt_draw_screen5)
    Button bt_draw;
    @BindView(R.id.bt_frame_screen5)
    Button bt_frame;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_5,container,false);
        ButterKnife.bind(this, view);
        start();
        changeFragment();
        return view;
    }

    private void start() {
        Glide.with(this).load(mainActivity.getImg_url()).into(img);
    }


    public void changeFragment(){
        mainActivity.icon_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.finish();
            }
        });
        mainActivity.icon_accept.setVisibility(View.INVISIBLE);
        bt_frame.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CommitTransaction")
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frag_activity5, new FragmentScreen51()).addToBackStack(null).commit();
            }
        });
        bt_draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frag_activity5, new FragmentScreen52()).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof Screen5Activity){
            this.mainActivity = (Screen5Activity) context;
        }
    }
}
