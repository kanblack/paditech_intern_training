package com.toring.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private View currentView;


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
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(linearLayoutManager);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                rv.scrollToPosition(position);
               select(linearLayoutManager.getChildAt(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rvAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem((Integer) view.getTag(), true);
                select(view);
            }
        });

        return view;
    }

    public void select(View view){
        if (currentView != null){
            currentView.setBackgroundColor(getResources().getColor(R.color.background_color));
        }
        view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        currentView = view;
    }

}
