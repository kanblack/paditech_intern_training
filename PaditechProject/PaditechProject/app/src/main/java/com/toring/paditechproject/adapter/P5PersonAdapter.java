package com.toring.paditechproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.toring.paditechproject.R;
import com.toring.paditechproject.model.P5Person;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ToRing on 12/13/2017.
 */

public class P5PersonAdapter extends RecyclerView.Adapter<P5PersonAdapter.VH> {
    private Context context;
    private List<P5Person> p5PersonList;

    public P5PersonAdapter(Context context, List<P5Person> p5PersonList) {
        this.context = context;
        this.p5PersonList = p5PersonList;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == 0) {
            View view = inflater.inflate(R.layout.item_p5_person_frst_item, parent, false);
            return new FirstItemVh(view);
        } else {
            View view = inflater.inflate(R.layout.item_p5_person_normal, parent, false);
            return new NormalItemVH(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bindViewHolder(position);
    }

    @Override
    public int getItemCount() {
        return p5PersonList.size();
    }

    public abstract class VH extends RecyclerView.ViewHolder {

        public VH(View itemView) {
            super(itemView);
        }

        public void bindViewHolder(int pos) {
            onBindingViewHolder(pos);
        }

        public abstract void onBindingViewHolder(int position);
    }

    public class FirstItemVh extends VH {
        public FirstItemVh(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindingViewHolder(int position) {

        }
    }

    public class NormalItemVH extends VH {
        private TextView tvName;
        private ImageView ivOn;
        private CircleImageView ivProfile, ivAvatar;

        public NormalItemVH(View itemView) {
            super(itemView);

            ivProfile = itemView.findViewById(R.id.iv_profile);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);

            ivOn = itemView.findViewById(R.id.iv_on);
            tvName = itemView.findViewById(R.id.tv_name);
        }

        @Override
        public void onBindingViewHolder(int position) {
            P5Person p5Person = p5PersonList.get(position - 1);
            if (p5Person.isNew_()) {
                ivAvatar.setImageResource(p5Person.getAcvatar());
                ivProfile.setImageResource(p5Person.getProfile());
            } else {
                ivAvatar.setVisibility(View.GONE);
                ivProfile.setBorderWidth(0);
            }

            if (!p5Person.isOn()) {
                ivOn.setVisibility(View.GONE);
            }

            tvName.setText(p5Person.getName());
        }
    }
}
