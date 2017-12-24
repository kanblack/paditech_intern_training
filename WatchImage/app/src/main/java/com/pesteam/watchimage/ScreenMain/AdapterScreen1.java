package com.pesteam.watchimage.ScreenMain;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.pesteam.watchimage.R;
import com.pesteam.watchimage.Screen4Activity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bangindong on 12/14/2017.
 */

public class AdapterScreen1 extends RecyclerView.Adapter<AdapterScreen1.BaseHolder> {

    private List<String> lists = new ArrayList<>();
    private FragmentScreen1 fragmentScreen1;
    void setLists(List<String> lists) {
        this.lists = lists;
    }

    AdapterScreen1(FragmentScreen1 fragmentScreen1) {
        this.fragmentScreen1 = fragmentScreen1;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChildHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.child_rcv_screen1, parent, false)) {
        };
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    abstract class BaseHolder extends RecyclerView.ViewHolder {

        BaseHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindData(int position) {
            onBindingData(position);
        }

        protected abstract void onBindingData(int position);
    }

    class ChildHolder extends BaseHolder implements View.OnClickListener {

        @BindView(R.id.img_child_rvc_screen1)
        ImageView img_child;
        @BindView(R.id.tex_title_child_rvc_screen1)
        TextView tx_title;
        @BindView(R.id.tex_descrip_child_rvc_screen1)
        TextView tx_des;
        Bitmap b;

        ChildHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        protected void onBindingData(int position) {
            fragmentScreen1.progress.setVisibility(View.VISIBLE);
            Glide.with(itemView.getContext())
                    .load(lists.get(position))
                    .apply(new RequestOptions().placeholder(R.drawable.image))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            fragmentScreen1.progress.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            fragmentScreen1.progress.setVisibility(View.GONE);
                            return false;
                        }
                    }).into(img_child);
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(itemView.getContext(), Screen4Activity.class);
            intent.putExtra("img_url", lists.get(getLayoutPosition()));
            intent.putExtra("position", getLayoutPosition());
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) itemView.getContext(),
                            img_child,
                            ViewCompat.getTransitionName(img_child));
            itemView.getContext().startActivity(intent, optionsCompat.toBundle());
        }
    }
}
