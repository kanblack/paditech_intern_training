package com.pesteam.test;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bangindong on 12/28/2017.
 */

public class Adapet extends RecyclerView.Adapter<Adapet.BaseHolder> {
    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChildHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyc_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }



    abstract class BaseHolder extends RecyclerView.ViewHolder {

        BaseHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void bind(int position) {
            onBinding(position);
        }

        abstract void onBinding(int position);

    }

    class ChildHolder extends BaseHolder{

        TextView textView;
        ChildHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textview);
        }

        @Override
        void onBinding(int position) {
            textView.setText(""+ position);
        }
    }
}
