package com.toring.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.toring.myapplication.R;

import java.util.List;

/**
 * Created by tr on 12/22/17.
 */

public class P5DrawImageAdapter extends RecyclerView.Adapter<P5DrawImageAdapter.ImageVH> {
    private Context context;
    private List<Integer> imageList;

    private View.OnClickListener onClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public P5DrawImageAdapter(Context context, List<Integer> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public ImageVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_image_draw, parent, false);
        return new ImageVH(view);
    }

    @Override
    public void onBindViewHolder(ImageVH holder, int position) {
        holder.bindViewHolder(position);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ImageVH extends RecyclerView.ViewHolder {
        private ImageView ivImage;
        private View view;

        public ImageVH(View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.iv_image);

            view = itemView;
        }

        public void bindViewHolder(int position) {
            ivImage.setImageResource(imageList.get(position));

            view.setTag(imageList.get(position));
            view.setOnClickListener(onClickListener);
        }
    }
}
