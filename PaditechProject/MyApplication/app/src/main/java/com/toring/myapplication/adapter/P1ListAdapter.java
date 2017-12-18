package com.toring.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
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
    private Context context;
    private List<String> pictureList;

    public P1ListAdapter(Context context, List<String> pictureList) {
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

        public VHP1List(View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.iv_picture);
            tv = itemView.findViewById(R.id.tv_link);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, P4Activity.class));
                }
            });
        }

        public void bindView(int position) {
            DisplayPicture.displayImage(context, pictureList.get(position), ivPicture);
            tv.setText(pictureList.get(position));
        }
    }
}
