package com.toring.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.toring.myapplication.R;
import com.toring.myapplication.activity.P4Activity;
import com.toring.myapplication.glide.DisplayPicture;
import com.toring.myapplication.item_fragment.ItemP3VPFragment;

import org.json.JSONException;

import java.util.List;

public class P1ListAdapter extends BaseAdapter {

    public P1ListAdapter(Activity context, List<String> pictureList, boolean isFacebook) {
        super(context, pictureList, isFacebook);
    }

    @Override
    public VHP1List onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_p1_list, parent, false);
        return new VHP1List(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }

    public class VHP1List extends BaseAdapter.VH {
        private TextView tv;
        private View view;

        public VHP1List(View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.iv_picture);
            tv = itemView.findViewById(R.id.tv_link);

            view = itemView;
        }

        public void bindView(final int position) {
            super.bindView(position);

            tv.setText(pictureList.get(position));

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, P4Activity.class);

                    intent.putExtra(context.getResources().getString(R.string.picture), pictureList.get(position));
                    intent.putExtra(context.getResources().getString(R.string.is_facebook), isFacebook);

                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(context, (View) ivPicture, "detail");
                    context.startActivity(intent, options.toBundle());
                }
            });
        }
    }
}
