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

public class P3SlideRVAdapter extends RecyclerView.Adapter<P3SlideRVAdapter.VHP3Slide> {
    private Context context;
    private List<String> pitureList;

    private View.OnClickListener onClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public P3SlideRVAdapter(Context context, List<String> pitureList) {
        this.context = context;
        this.pitureList = pitureList;
    }

    @Override
    public VHP3Slide onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_p3_slide, parent, false);
        return new VHP3Slide(view);
    }

    @Override
    public void onBindViewHolder(VHP3Slide holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return pitureList.size();
    }

    public class VHP3Slide extends RecyclerView.ViewHolder {
        private ImageView ivPicture;
        private View view;

        public VHP3Slide(View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.iv_picture);
            view = itemView;
        }

        public void bindView(final int position) {
            DisplayPicture.displayImage(context, pitureList.get(position), ivPicture);
            view.setTag(position);
            view.setOnClickListener(onClickListener);
        }
    }
}
