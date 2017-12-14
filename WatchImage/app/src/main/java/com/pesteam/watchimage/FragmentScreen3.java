package com.pesteam.watchimage;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by bangindong on 12/14/2017.
 */

public class FragmentScreen3 extends android.support.v4.app.Fragment {
    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_3,container,false);
        ButterKnife.bind(this, view);
        changeFragment();
        return view;
    }

    public void changeFragment(){
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

        if(context instanceof MainActivity){
        }               this.mainActivity = (MainActivity) context;
    }

}
