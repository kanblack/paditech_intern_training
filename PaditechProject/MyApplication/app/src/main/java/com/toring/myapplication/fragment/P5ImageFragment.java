package com.toring.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toring.myapplication.R;
import com.toring.myapplication.adapter.P5DrawColorAdapter;
import com.toring.myapplication.adapter.P5DrawImageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class P5ImageFragment extends Fragment {
    private RecyclerView rvImage;
    private List<Integer> imageList;
    private View.OnClickListener onClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public P5ImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_p5_image, container, false);

        imageList = new ArrayList<>();
        imageList.add(R.drawable.image_1);
        imageList.add(R.drawable.image_2);

        rvImage = view.findViewById(R.id.rv_draw_image);
        P5DrawImageAdapter adapter = new P5DrawImageAdapter(this.getContext(), imageList);
        adapter.setOnClickListener(onClickListener);
        rvImage.setAdapter(adapter);
        rvImage.setLayoutManager(new LinearLayoutManager(this.getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        return view;
    }

}