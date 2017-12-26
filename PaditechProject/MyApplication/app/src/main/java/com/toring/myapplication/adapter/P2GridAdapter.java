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

public class P2GridAdapter extends RecyclerView.Adapter<P2GridAdapter.VHP2Grid> {
    private Context context;
    private List<String> pictureList;

    public P2GridAdapter(Context context, List<String> pictureList) {
        this.context = context;
        this.pictureList = pictureList;
    }

    @Override
    public VHP2Grid onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_p2_grid, parent, false);
        return new VHP2Grid(view);
    }

    @Override
    public void onBindViewHolder(VHP2Grid holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }

    public class VHP2Grid extends RecyclerView.ViewHolder{

        private ImageView ivPicture;
        private View view;

        public VHP2Grid(View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.iv_picture);
            view = itemView;
        }

        public void bindData(final int position) {
            DisplayPicture.displayImageCrop(context, pictureList.get(position), ivPicture);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, P4Activity.class);

                    intent.putExtra(context.getResources().getString(R.string.picture), pictureList.get(position));
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((Activity) context, (View)ivPicture, "detail");
                    context.startActivity(intent, options.toBundle());
                }
            });
        }
    }
}
