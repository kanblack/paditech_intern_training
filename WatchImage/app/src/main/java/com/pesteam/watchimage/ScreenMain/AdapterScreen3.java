package com.pesteam.watchimage.ScreenMain;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.pesteam.watchimage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bangindong on 12/15/2017.
 */

public class AdapterScreen3 extends RecyclerView.Adapter<AdapterScreen3.BaseHolder> {

    private ArrayList<ChildHolder> childHolders = new ArrayList<>();
    private List<String> list_url_img = new ArrayList<>();
    private FragmentScreen3 fragmentScreen3;
    private int img_chose = 0;
    private ChildHolder childHolder;

    public void setChildHolder(ChildHolder childHolder) {
        this.childHolder = childHolder;
    }

    ArrayList<ChildHolder> getChildHolders() {
        return childHolders;
    }

    public void setImg_chose(int img_chose) {
        this.img_chose = img_chose;
    }

    AdapterScreen3(FragmentScreen3 fragmentScreen3) {
        this.fragmentScreen3 = fragmentScreen3;
    }

    void setList_url_img(List<String> list_url_img) {
        this.list_url_img = list_url_img;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ChildHolder childHolder = new ChildHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.child_rcv_bottom_fragment_screen3, parent, false));
        Log.e( "onCreateViewHolder: ", childHolders.size()+"" );
        return childHolder;
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return list_url_img.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    abstract class BaseHolder extends RecyclerView.ViewHolder {

        BaseHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindData(int position) {
            onBindingData(position);
        }

        abstract void onBindingData(int position);
    }

    class ChildHolder extends BaseHolder implements View.OnClickListener {

        @BindView(R.id.img_child_rcv_screen3)
        ImageView img_child_rcv;
        @BindView(R.id.background_rl_child_rcv_bottom_screen3)
        RelativeLayout relativeLayout;
        private int position1;
        static final int CHANGE_TO_GREYWHITE = 0 ;
        static final int CHANGE_TO_MOREGREYWHITE = 1;
        ChildHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        void onBindingData(int position) {
            Glide.with(itemView.getContext()).load(list_url_img.get(position)).into(img_child_rcv);
            this.position1 = position;
            Log.e( "onBindingData: ", position+"" );
            if (childHolder == null) {
                childHolder = this;
                Log.e( "binding: ", img_chose +"" );
            }
                if(position == img_chose){
                    changeColor(this,CHANGE_TO_GREYWHITE);
                } else {
                    changeColor(this,CHANGE_TO_MOREGREYWHITE);
                }

        }


        @Override
        public void onClick(View view) {
            if(img_chose != this.position1) {
                changeColor(childHolder,CHANGE_TO_MOREGREYWHITE);
            }
            fragmentScreen3.setPosition(this.position1);
            img_chose = this.position1;
            Glide.with(fragmentScreen3).load(list_url_img.get(img_chose)).into(fragmentScreen3.big_img);
            Log.e( "click: ", img_chose+"" );
            changeColor(this,CHANGE_TO_GREYWHITE);
            childHolder = this;
        }

        void changeColor(ChildHolder childHolder, int changeColor){
            switch (changeColor){
                case CHANGE_TO_GREYWHITE:
                    childHolder.relativeLayout.setBackgroundColor(itemView.getResources().getColor(R.color.colorGreyWhite));
                    break;
                case CHANGE_TO_MOREGREYWHITE:
                    childHolder.relativeLayout.setBackgroundColor(itemView.getResources().getColor(R.color.colorMoreGreyWhite));
                    break;
            }
        }



    }
}
