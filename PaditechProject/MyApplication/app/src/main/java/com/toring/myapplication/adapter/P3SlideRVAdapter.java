package com.toring.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
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
    private List<String> pictureList;
    public int currentIndex = 0;

    private View.OnClickListener onClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public P3SlideRVAdapter(Context context, List<String> pictureList) {
        this.context = context;
        this.pictureList = pictureList;
    }

    @Override
    public VHP3Slide onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_p3_slide, parent, false);
        return new VHP3Slide(view);
    }

    @Override
    public void onBindViewHolder(final VHP3Slide holder, final int position) {
        holder.bindView(position);

        if (position == currentIndex) {
            holder.view.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
//
//            ViewGroup.LayoutParams params1 = holder.ivPicture.getLayoutParams();
//            params1.width += 30;
//            params1.height += 50;
//            holder.ivPicture.setLayoutParams(params1);
//            holder.view.setPadding(10,5,10,5);
        } else {
            holder.view.setBackgroundColor(context.getResources().getColor(R.color.background_color));
//            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
//            float w = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50F, metrics);
//            float h = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 75F, metrics);
//            ViewGroup.LayoutParams params1 = holder.ivPicture.getLayoutParams();
//            params1.width = (int) w;
//            params1.height = (int) h;
//            holder.ivPicture.setLayoutParams(params1);
//            holder.view.setPadding(5,5,5,5);
        }
    }

    @Override
    public int getItemCount() {
        return pictureList.size();
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
            DisplayPicture.displayImageCrop(context, pictureList.get(position), ivPicture);
            view.setTag(position);
            view.setOnClickListener(onClickListener);
        }
    }
}
