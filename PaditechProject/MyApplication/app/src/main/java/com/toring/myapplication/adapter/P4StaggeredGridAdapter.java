package com.toring.myapplication.adapter;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toring.myapplication.R;
import com.toring.myapplication.activity.P4Activity;
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
//            super.bindView(position);
            DisplayPicture.displayImage(context.getContext(), pictureList.get(position), ivPicture);
//            GlideApp.with(context)
//                    .load(pictureList.get(position))
//                    .fitCenter()
//                    .thumbnail(0.5f)
//                    .error(R.mipmap.ic_launcher)
//                    .into(ivPicture);

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
