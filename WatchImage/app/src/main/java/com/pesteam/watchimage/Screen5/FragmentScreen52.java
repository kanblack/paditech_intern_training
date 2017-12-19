package com.pesteam.watchimage.Screen5;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pesteam.watchimage.Canvas.CanvasView;
import com.pesteam.watchimage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bangindong on 12/18/2017.
 */

public class FragmentScreen52 extends Fragment {

    @BindView(R.id.rcv_screen_52)
    RecyclerView rcv;
    @BindView(R.id.canvas_big_screen_52)
    CanvasView canvas_big;
    private Screen5Activity mainActivity;
    private AdapterScreen52 adapter = new AdapterScreen52(this);
    private String url_img;

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_52,container,false);
        ButterKnife.bind(this, view);
        start();
        changeFragment();
        return view;
    }

    private void changeFragment() {
        mainActivity.icon_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frag_activity5, new FragmentScreen5()).addToBackStack(null).commit();
            }
        });
    }

    private void start() {
        rcv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcv.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof Screen5Activity){
            this.mainActivity = (Screen5Activity) context;
        }
    }
}
