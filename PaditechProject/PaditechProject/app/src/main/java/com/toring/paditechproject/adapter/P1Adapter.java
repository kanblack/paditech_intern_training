package com.toring.paditechproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by tr on 12/17/17.
 */

public class P1Adapter extends RecyclerView.Adapter<P1Adapter.P1PartVH>{
    private Context context;
    private List

    @Override
    public P1PartVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from()
    }

    @Override
    public void onBindViewHolder(P1PartVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class P1PartVH extends RecyclerView.ViewHolder{
        public P1PartVH(View itemView) {
            super(itemView);
        }
    }
}
