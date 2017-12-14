package com.toring.paditechproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.toring.paditechproject.R;
import com.toring.paditechproject.model.Person;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ToRing on 12/13/2017.
 */

public class FirstP5Adapter extends RecyclerView.Adapter<FirstP5Adapter.VH> {
    private Context context;
    private List<Person> personList;

    public FirstP5Adapter(Context context, List<Person> personList) {
        this.context = context;
        this.personList = personList;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        Log.e("LOL", "onCreateViewHolder: ");
        if (viewType == 0) {
            View view = inflater.inflate(R.layout.item_add_to_you_day, parent, false);
            return new VH1(view);
        } else {
            View view = inflater.inflate(R.layout.item_firt_p5, parent, false);
            return new VH2(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Log.e("LOL", "onBindViewHolder: SCroll");
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public abstract class VH extends RecyclerView.ViewHolder {
        private TextView tvName;
        private ImageView ivOn;
        private CircleImageView ivBia, ivAvatar;

        public VH(View itemView) {
            super(itemView);
        }

        public void setData(int pos) {
            onData(pos);
        }

        public abstract void onData(int position);
    }

    public class VH1 extends VH {
        public VH1(View itemView) {
            super(itemView);
        }

        @Override
        public void onData(int position) {

        }
    }

    public class VH2 extends VH {
        private TextView tvName;
        private ImageView ivOn;
        private CircleImageView ivBia, ivAvatar;

        public VH2(View itemView) {
            super(itemView);

            ivBia = itemView.findViewById(R.id.iv_bia);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);

            ivOn = itemView.findViewById(R.id.iv_on);
            tvName = itemView.findViewById(R.id.tv_name);
        }

        @Override
        public void onData(int position) {
            Person person = personList.get(position - 1);
            if (person.isNew_()) {
                ivAvatar.setImageResource(person.getAva());
                ivBia.setImageResource(person.getBia());
            } else {
                ivAvatar.setVisibility(View.GONE);
                ivBia.setBorderWidth(0);
            }

            if (!person.isOn()) {
                ivOn.setVisibility(View.GONE);
            }

            tvName.setText(person.getName());
        }
    }
}
