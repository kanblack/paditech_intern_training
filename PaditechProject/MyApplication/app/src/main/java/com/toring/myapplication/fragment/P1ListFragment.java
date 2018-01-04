package com.toring.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toring.myapplication.R;
import com.toring.myapplication.activity.MainActivity;
import com.toring.myapplication.adapter.P1ListAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class P1ListFragment extends FragmentBase {
    private RecyclerView rvList;
    private P1ListAdapter adapter;

    public P1ListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_p1_list, container, false);
        rvList = view.findViewById(R.id.rv_list);
        adapter = new P1ListAdapter(this, pictureList, album);
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return view;
    }

    @Override
    public void loadMore() {
        adapter.notifyDataSetChanged();
    }
}
