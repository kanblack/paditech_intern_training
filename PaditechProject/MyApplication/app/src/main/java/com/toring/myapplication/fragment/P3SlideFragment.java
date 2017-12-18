package com.toring.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toring.myapplication.R;
import com.toring.myapplication.adapter.P3SlideRVAdapter;
import com.toring.myapplication.adapter.P3SlideVPAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class P3SlideFragment extends Fragment {
    private ViewPager vp;
    private RecyclerView rv;
    private List<String> pictureList;

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }

    public P3SlideFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_p3_slide, container, false);
        vp = view.findViewById(R.id.vp_slide);
        rv = view.findViewById(R.id.rv_slide);

        P3SlideVPAdapter vpAdapter = new P3SlideVPAdapter(this.getFragmentManager(),
                this.getContext(), pictureList);
        vp.setAdapter(vpAdapter);

        P3SlideRVAdapter rvAdapter = new P3SlideRVAdapter(this.getContext(), pictureList);
        rv.setAdapter(rvAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        return view;
    }

}
