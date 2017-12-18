package com.toring.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.toring.myapplication.R;
import com.toring.myapplication.glide.DisplayPicture;

import java.util.List;

/**
 * Created by tr on 12/18/17.
 */

public class P3SlideRVAdapter extends RecyclerView.Adapter<P3SlideRVAdapter.VHP3SLide> {
    private Context context;
    private List<String> pitureList;

    public P3SlideRVAdapter(Context context, List<String> pitureList) {
        this.context = context;
        this.pitureList = pitureList;
    }

    @Override
    public VHP3SLide onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_p3_slide, parent, false);
        return new VHP3SLide(view);
    }

    @Override
    public void onBindViewHolder(VHP3SLide holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return pitureList.size();
    }

    public class VHP3SLide extends RecyclerView.ViewHolder {
        private ImageView ivPicture;
        public VHP3SLide(View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.iv_picture);
        }

        public void bindView(int position) {
            DisplayPicture.displayImage(context, pitureList.get(position), ivPicture);
        }
    }
}
