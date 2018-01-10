package com.toring.myapplication.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.toring.myapplication.R;
import com.toring.myapplication.activity.P4Activity;
import com.toring.myapplication.customvie.DynamicHeightImageView;
import com.toring.myapplication.fragment.FragmentBase;
import com.toring.myapplication.glide.GlideApp;
import com.toring.myapplication.network.image_object.ImageObject;

import java.util.List;

/**
 * Created by tr on 12/18/17.
 */

public class P4StaggeredGridAdapter extends BaseAdapter {

    public P4StaggeredGridAdapter(FragmentBase context, List<ImageObject> pictureList, String album) {
        super(context, pictureList, album);
    }

    @Override
    public VHP4GridStaggered onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context.getContext());
        View view = layoutInflater.inflate(R.layout.item_p4_staggered, parent, false);
        return new VHP4GridStaggered(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return imageObjectList.size();
    }

    public class VHP4GridStaggered extends VH {
        private View view;
        private TextView tvUrl;
        private DynamicHeightImageView dynamicHeightImageView;

        public VHP4GridStaggered(View itemView) {
            super(itemView);
//            ivPicture = itemView.findViewById(R.id.iv_picture);
            dynamicHeightImageView = itemView.findViewById(R.id.iv_picture);
            tvUrl = itemView.findViewById(R.id.tv_url);
            view = itemView;
        }

        public void bindView(final int position) {

            if (imageObjectList.get(position).getName() == null || imageObjectList.get(position).getName().isEmpty()) {
                tvUrl.setText(imageObjectList.get(position).getUrl());
            } else {
                tvUrl.setText(imageObjectList.get(position).getName());
            }

            if (imageObjectList.get(position).getHeight() > 0) {
                float n = (imageObjectList.get(position).getHeight() / (float) imageObjectList.get(position).getWidth());

                ViewGroup.LayoutParams params = dynamicHeightImageView.getLayoutParams();
                // image fit width
                params.height = (int) (n * dynamicHeightImageView.getWidth());

                dynamicHeightImageView.setLayoutParams(params);
                dynamicHeightImageView.setRatio(n);
                GlideApp.with(context).load(imageObjectList.get(position).getUrl()).into(dynamicHeightImageView);
            } else {
                GlideApp.with(context)
                        .asBitmap()
                        .load(imageObjectList.get(position).getUrl())
                        .priority(Priority.IMMEDIATE)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                float n = (resource.getHeight() / (float) resource.getWidth());

                                ViewGroup.LayoutParams params = dynamicHeightImageView.getLayoutParams();
                                // image fit width
                                params.height = (int) (n * dynamicHeightImageView.getWidth());

                                dynamicHeightImageView.setLayoutParams(params);
                                imageObjectList.get(position).setHeight(resource.getHeight());
                                imageObjectList.get(position).setWidth(resource.getWidth());

                                dynamicHeightImageView.setImageBitmap(resource);
                            }
                        });
            }

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context.getContext(), P4Activity.class);

                    intent.putExtra(context.getResources().getString(R.string.picture), imageObjectList.get(position));

                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(context.getActivity(), dynamicHeightImageView, "detail");
                    context.startActivity(intent, options.toBundle());
                }
            });
        }
    }
}
