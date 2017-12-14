package com.pesteam.watchimage;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
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
import com.pesteam.watchimage.getData.ChildScreen1Class;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bangindong on 12/14/2017.
 */

public class AdapterScreen1 extends RecyclerView.Adapter<AdapterScreen1.BaseHolder> {

    private List<String> lists = new ArrayList<>();

    public void setLists(List<String> lists) {
        this.lists = lists;
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

    abstract class BaseHolder extends RecyclerView.ViewHolder{

        BaseHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void bindData(int position){
            onBindingData(position);
        }

        protected abstract void onBindingData(int position);
    }

    class ChildHolder extends BaseHolder implements View.OnClickListener{

        @BindView(R.id.img_child_rvc_screen1)
        ImageView img_child;
        @BindView(R.id.tex_title_child_rvc_screen1)
        TextView tx_title;
        @BindView(R.id.tex_descrip_child_rvc_screen1)
        TextView tx_des;

        ChildHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        protected void onBindingData(int position) {
            Glide.with(itemView).load(lists.get(position)).into(img_child);
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(itemView.getContext(),Screen4Activity.class);
            intent.putExtra("img_url",lists.get(getLayoutPosition()));
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) itemView.getContext(),
                            img_child,
                            ViewCompat.getTransitionName(img_child));
            itemView.getContext().startActivity(intent, optionsCompat.toBundle());
        }
    }
}
