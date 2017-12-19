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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class P5DrawFragment extends Fragment {
    private RecyclerView rvColor;
    private List<Integer> colorList;
    private View.OnClickListener onClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public P5DrawFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_p5_draw, container, false);

        colorList = new ArrayList<>();
        colorList.add(R.color.color_1);
        colorList.add(R.color.color_2);
        colorList.add(R.color.color_3);
        colorList.add(R.color.color_4);
        colorList.add(R.color.color_5);
        colorList.add(R.color.color_6);
        colorList.add(R.color.color_7);
        colorList.add(R.color.color_8);
        colorList.add(R.color.color_9);
        colorList.add(R.color.color_10);

        rvColor = view.findViewById(R.id.rv_draw_color);
        P5DrawColorAdapter adapter = new P5DrawColorAdapter(this.getContext(), colorList);
        adapter.setOnClickListener(onClickListener);
        rvColor.setAdapter(adapter);
        rvColor.setLayoutManager(new LinearLayoutManager(this.getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        return view;
    }

}