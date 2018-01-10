package com.toring.myapplication.adapter;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toring.myapplication.R;
import com.toring.myapplication.activity.P4Activity;
import com.toring.myapplication.fragment.FragmentBase;
import com.toring.myapplication.network.image_object.ImageObject;

import java.util.List;

/**
 * Created by tr on 12/18/17.
 */

public class P2GridAdapter extends BaseAdapter {

    public P2GridAdapter(FragmentBase context, List<ImageObject> pictureList, String album) {
        super(context, pictureList, album);
    }

    @Override
    public VHP2Grid onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context.getContext());
        View view = layoutInflater.inflate(R.layout.item_p2_grid, parent, false);
        return new VHP2Grid(view);
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

    public class VHP2Grid extends BaseAdapter.VH {
        private View view;

        public VHP2Grid(View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.iv_picture);
            view = itemView;
        }

        public void bindView(final int position) {
            super.bindView(position);

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
