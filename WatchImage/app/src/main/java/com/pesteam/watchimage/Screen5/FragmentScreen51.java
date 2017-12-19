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
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pesteam.watchimage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bangindong on 12/18/2017.
 */

public class FragmentScreen51 extends Fragment {

    @BindView(R.id.img_big_screen_51_52)
    ImageView img_big;
    @BindView(R.id.rcv_screen_51_52)
    RecyclerView rcv;
    private Screen5Activity mainActivity;
    private AdapterScreen51 adapter = new AdapterScreen51(this);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_51,container,false);
        ButterKnife.bind(this, view);
        start();
        getData();
        changeFragment();
        return view;
    }

    private void getData() {
        adapter.setImg_url(mainActivity.getImg_url());
        adapter.setBitmap(mainActivity.getImage());
        rcv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcv.setAdapter(adapter);
    }


    private void start() {
            Glide.with(this).load(mainActivity.getImg_url()).into(img_big);
        }


    private void changeFragment() {
        mainActivity.icon_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frag_activity5, new FragmentScreen5()).addToBackStack(null).commit();
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
