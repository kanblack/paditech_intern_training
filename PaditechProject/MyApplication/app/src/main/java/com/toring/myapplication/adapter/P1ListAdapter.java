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
import android.widget.TextView;

import com.toring.myapplication.R;
import com.toring.myapplication.activity.P4Activity;
import com.toring.myapplication.glide.DisplayPicture;

import java.util.List;

/**
 * Created by tr on 12/18/17.
 */

public class P1ListAdapter extends RecyclerView.Adapter<P1ListAdapter.VHP1List> {
    private Activity context;
    private List<String> pictureList;

    public P1ListAdapter(Activity context, List<String> pictureList) {
        this.context = context;
        this.pictureList = pictureList;
    }

    @Override
    public VHP1List onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_p1_list, parent, false);
        return new VHP1List(view);
    }

    @Override
    public void onBindViewHolder(VHP1List holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }

    public class VHP1List extends RecyclerView.ViewHolder{
        private ImageView ivPicture;
        private TextView tv;
        private View view;

        public VHP1List(View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.iv_picture);
            tv = itemView.findViewById(R.id.tv_link);

            view = itemView;
        }

        public void bindView(final int position) {
            DisplayPicture.displayImage(context, pictureList.get(position), ivPicture);
            tv.setText(pictureList.get(position));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, P4Activity.class);

                    intent.putExtra(context.getResources().getString(R.string.picture), pictureList.get(position));
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(context, (View)ivPicture, "profile");
                    context.startActivity(intent, options.toBundle());
                }
            });
        }
    }
}
