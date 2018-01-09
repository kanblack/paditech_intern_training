package com.toring.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toring.myapplication.R;
import com.toring.myapplication.adapter.P3SlideRVAdapter;
import com.toring.myapplication.adapter.P3SlideVPAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class P3SlideFragment extends FragmentBase {
    private ViewPager vp;
    private RecyclerView rv;
    private P3SlideVPAdapter vpAdapter;
    private P3SlideRVAdapter rvAdapter;

    public P3SlideFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_p3_slide, container, false);
        vp = view.findViewById(R.id.vp_slide);
        rv = view.findViewById(R.id.rv_slide);

        vpAdapter = new P3SlideVPAdapter(this.getFragmentManager(),
                this.getContext(), pictureList, false);
        vp.setAdapter(vpAdapter);

        rvAdapter = new P3SlideRVAdapter(this, pictureList, album);
        rv.setAdapter(rvAdapter);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(linearLayoutManager);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                DisplayMetrics metrics = P3SlideFragment.this.getActivity().getResources().getDisplayMetrics();
                float w = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 51, metrics);
                w += 50;
                linearLayoutManager.scrollToPositionWithOffset(position,
                        (int) ((P3SlideFragment.this.getView().getWidth()-w)/2));

                int oldIndex = rvAdapter.currentIndex;
                rvAdapter.currentIndex = position;
                rvAdapter.notifyItemChanged(position);
                rvAdapter.notifyItemChanged(oldIndex);

                if (position == pictureList.size() - 1){
                    P3SlideFragment.this.getViewAllImageFragment().loadMorePhoto();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e("ok", "onPageScrollStateChanged: " + state);
            }
        });

        rvAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem((Integer) view.getTag(), true);
                int oldIndex = rvAdapter.currentIndex;
                rvAdapter.currentIndex = (Integer) view.getTag();
                rvAdapter.notifyItemChanged((Integer) view.getTag());
                rvAdapter.notifyItemChanged(oldIndex);
            }
        });
        return view;
    }

    @Override
    public void loadMore() {
        vpAdapter.notifyDataSetChanged();
        rvAdapter.notifyDataSetChanged();
    }
}
