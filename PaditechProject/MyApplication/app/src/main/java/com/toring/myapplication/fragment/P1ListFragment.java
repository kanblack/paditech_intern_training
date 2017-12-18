package com.toring.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toring.myapplication.R;
import com.toring.myapplication.adapter.P1ListAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class P1ListFragment extends Fragment {
    private RecyclerView rvList;
    private List<String> pictureList;

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }

    public P1ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_p1_list, container, false);
        rvList = view.findViewById(R.id.rv_list);
        P1ListAdapter adapter = new P1ListAdapter(this.getContext(), pictureList);
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return view;
    }

}
