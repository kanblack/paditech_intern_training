package com.toring.myapplication.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.toring.myapplication.R;
import com.toring.myapplication.activity.P5Activity;

import java.util.List;

/**
 * Created by tr on 12/19/17.
 */

public class P5DrawColorAdapter extends RecyclerView.Adapter<P5DrawColorAdapter.P5DrawVH> {
    private Context context;
    private List<Integer> colorList;

    private View.OnClickListener onClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public P5DrawColorAdapter(Context context, List<Integer> colorList) {
        this.context = context;
        this.colorList = colorList;
    }

    @Override
    public P5DrawVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_color, parent, false);
        return new P5DrawVH(view);
    }

    @Override
    public void onBindViewHolder(P5DrawVH holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }

    public class P5DrawVH extends RecyclerView.ViewHolder {
        private ImageView ivColor;
        private View view;

        public P5DrawVH(View itemView) {
            super(itemView);
            ivColor = itemView.findViewById(R.id.iv_color);
            view = itemView;
        }

        public void bindData(int position) {

            GradientDrawable bgShape = (GradientDrawable) ivColor.getBackground();
            int idColor = colorList.get(position);
            bgShape.setColor(context.getResources().getColor(idColor));

            view.setTag(colorList.get(position));
            view.setOnClickListener(onClickListener);
        }
    }
}
