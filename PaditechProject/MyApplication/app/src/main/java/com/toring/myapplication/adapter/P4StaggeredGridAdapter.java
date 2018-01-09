package com.toring.myapplication.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.squareup.picasso.Picasso;
import com.toring.myapplication.R;
import com.toring.myapplication.activity.P4Activity;
import com.toring.myapplication.customvie.DynamicHeightImageView;
import com.toring.myapplication.fragment.FragmentBase;
import com.toring.myapplication.glide.DisplayPicture;
import com.toring.myapplication.glide.GlideApp;

import java.util.List;

/**
 * Created by tr on 12/18/17.
 */

public class P4StaggeredGridAdapter extends BaseAdapter {

    public P4StaggeredGridAdapter(FragmentBase context, List<String> pictureList, String album) {
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
        return pictureList.size();
    }

    public class VHP4GridStaggered extends VH {
        private View view;

        public VHP4GridStaggered(View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.iv_picture);
            view = itemView;
        }

        public void bindView(final int position) {
//            ViewGroup.LayoutParams params = ivPicture.getLayoutParams();
//            // image fit width
//            params.height = (int) (300 + (position%5)*20);
//
//            DisplayPicture.displayImage(context.getContext(), pictureList.get(position), ivPicture);
//            ivPicture.setLayoutParams(params);

            GlideApp.with(context)
                    .asBitmap()
                    .load(pictureList.get(position))
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            float n = (resource.getHeight() / (float) resource.getWidth());

                            ViewGroup.LayoutParams params = ivPicture.getLayoutParams();
                            // image fit width
                            params.height = (int) (n * ivPicture.getWidth());

//                            dynamicHeightImageView.setRatio(n);
                            ivPicture.setLayoutParams(params);

                            ivPicture.setImageBitmap(resource);
                        }
                    });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context.getContext(), P4Activity.class);

                    intent.putExtra(context.getResources().getString(R.string.picture), pictureList.get(position));

                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(context.getActivity(), ivPicture, "detail");
                    context.startActivity(intent, options.toBundle());
                }
            });
        }
    }
}
