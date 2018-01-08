package com.toring.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toring.myapplication.R;
import com.toring.myapplication.adapter.P4StaggeredGridAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class P4StaggeredGridFragment extends FragmentBase {
    private RecyclerView rvList;
    private P4StaggeredGridAdapter adapter;

    public P4StaggeredGridFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_p4_staggered, container, false);
        rvList = view.findViewById(R.id.rv_staggered);
        adapter = new P4StaggeredGridAdapter(this, pictureList, album);

        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rvList.setLayoutManager(manager);
//        rvList.setItemAnimator(new DefaultItemAnimator());
        rvList.setAdapter(adapter);
//        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                manager.invalidateSpanAssignments();
//            }
//        });

        return view;
    }

    @Override
    public void loadMore() {
        adapter.notifyDataSetChanged();
    }
}
