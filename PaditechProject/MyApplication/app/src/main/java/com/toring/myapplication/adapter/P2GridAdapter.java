package com.toring.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.toring.myapplication.R;
import com.toring.myapplication.activity.P4Activity;
import com.toring.myapplication.glide.DisplayPicture;

import java.util.List;

/**
 * Created by tr on 12/18/17.
 */

public class P2GridAdapter extends BaseAdapter {

    public P2GridAdapter(Activity context, List<String> pictureList, boolean isFacebook) {
        super(context, pictureList, isFacebook);
    }

    @Override
    public VHP2Grid onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_p2_grid, parent, false);
        return new VHP2Grid(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }

    public class VHP2Grid extends BaseAdapter.VH{
        private View view;

        public VHP2Grid(View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.iv_picture);
            view = itemView;
        }

        public void bindView(final int position) {
//            DisplayPicture.displayImageCrop(context, pictureList.get(position), ivPicture);

            super.bindView(position);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, P4Activity.class);

                    intent.putExtra(context.getResources().getString(R.string.picture), pictureList.get(position));
                    intent.putExtra(context.getResources().getString(R.string.is_facebook), isFacebook);

                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((Activity) context, (View)ivPicture, "detail");
                    context.startActivity(intent, options.toBundle());
                }
            });
        }
    }
}
