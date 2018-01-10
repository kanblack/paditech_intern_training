package com.toring.myapplication.adapter;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.toring.myapplication.R;
import com.toring.myapplication.activity.P4Activity;
import com.toring.myapplication.fragment.FragmentBase;
import com.toring.myapplication.network.image_object.ImageObject;

import java.util.List;

public class P1ListAdapter extends BaseAdapter {

    public P1ListAdapter(FragmentBase context, List<ImageObject> pictureList, String album) {
        super(context, pictureList, album);
    }

    @Override
    public VHP1List onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context.getContext());
        View view = inflater.inflate(R.layout.item_p1_list, parent, false);
        return new VHP1List(view);
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

            if (imageObjectList.get(position).getName() == null || imageObjectList.get(position).getName().isEmpty()) {
                tv.setText(imageObjectList.get(position).getUrl());
            } else {
                tv.setText(imageObjectList.get(position).getName());
            }

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context.getContext(), P4Activity.class);

                    intent.putExtra(context.getResources().getString(R.string.picture), imageObjectList.get(position));

                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(context.getActivity(), (View) ivPicture, "detail");
                    context.startActivity(intent, options.toBundle());
                }
            });
        }
    }
}
