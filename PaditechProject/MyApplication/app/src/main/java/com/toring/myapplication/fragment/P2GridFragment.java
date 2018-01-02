package com.toring.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toring.myapplication.R;
import com.toring.myapplication.adapter.P2GridAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class P2GridFragment extends FragmentBase {
    private RecyclerView rvGrid;
//    private List<String> pictureList;

//    public void setPictureList(List<String> pictureList) {
//        this.pictureList = pictureList;
//    }

    public P2GridFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_p2_grid, container, false);
        rvGrid = view.findViewById(R.id.rv_grid);
        P2GridAdapter adapter = new P2GridAdapter(this.getActivity(), pictureList, isFacebook);
        rvGrid.setAdapter(adapter);
        rvGrid.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        return view;
    }

}
