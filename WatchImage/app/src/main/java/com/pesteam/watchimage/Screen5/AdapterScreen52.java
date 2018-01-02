package com.pesteam.watchimage.Screen5;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.pesteam.watchimage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bangindong on 12/18/2017.
 */

public class AdapterScreen52 extends RecyclerView.Adapter<AdapterScreen52.BaseHolder> {


    private int[] color = new int[]{
            R.color.material_white,
            R.color.material_black,
            R.color.material_blue600,
            R.color.material_red600,
            R.color.material_pink600,
            R.color.material_purple600,
            R.color.material_deeppurple600,
            R.color.material_green600,
            R.color.material_yellow600,
            R.color.material_orange600,
            R.color.material_brown600,
            R.color.material_grey600,
            R.color.material_deeporange600
    };
    private FragmentScreen52 fragmentScreen52;

    AdapterScreen52(FragmentScreen52 fragmentScreen52) {
        this.fragmentScreen52 = fragmentScreen52;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChildHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.child_rcv_bottom_fragment_screen52,parent,false));
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return color.length;
    }

    abstract class BaseHolder extends RecyclerView.ViewHolder {
        BaseHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(int position){
            onBinding(position);
        }

        abstract void onBinding(int position);
    }

    class ChildHolder extends BaseHolder implements View.OnClickListener{

        @BindView(R.id.color_rcv_bottom_screen52)
        RelativeLayout color_rcv;

         ChildHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        void onBinding(int position) {
            color_rcv.setBackgroundColor(itemView.getResources().getColor(color[position]));
        }

        @Override
        public void onClick(View view) {
            fragmentScreen52.editable_img_screen52.canvasView.screen52ChangePaint(color[getLayoutPosition()]);
        }
    }
}

