package com.toring.paditechproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannedString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.toring.paditechproject.R;
import com.toring.paditechproject.model.Noti;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tr on 12/13/17.
 */

public class NotifacationAdapter extends RecyclerView.Adapter<NotifacationAdapter.NotiVH> {
    private Context context;
    private List<Noti> notiList;

    public NotifacationAdapter(Context context, List<Noti> notiList) {
        this.context = context;
        this.notiList = notiList;
    }

    @Override
    public NotiVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_noti, parent, false);
        return new NotiVH(view);
    }

    @Override
    public void onBindViewHolder(NotiVH holder, int position) {
        holder.setData(notiList.get(position));
    }

    @Override
    public int getItemCount() {
        return notiList.size();
    }

    public class NotiVH  extends RecyclerView.ViewHolder{
        private View view;
        private TextView tvDes, tvTimeAgo;
        private ImageView ivAction;
        private CircleImageView ivAva;

        public NotiVH(View itemView) {
            super(itemView);
            view = itemView;

            tvDes = itemView.findViewById(R.id.tv_des);
            tvTimeAgo = itemView.findViewById(R.id.tv_time_ago);
            ivAction = itemView.findViewById(R.id.iv_action);
            ivAction = itemView.findViewById(R.id.iv_ava);
        }

        public void setData(Noti data) {
            if (!data.isSeen()){
                view.setBackgroundColor(Color.parseColor("#dfe5f2"));
            }

            switch (data.getAction()){
                case 1:
                    Picasso.with(context).load(R.drawable.ic_like).into(ivAction);
                    break;
                case 2:
                    Picasso.with(context).load(R.drawable.ic_wow).into(ivAction);
                    break;
                case 3:
                    Picasso.with(context).load(R.drawable.ic_heart).into(ivAction);
                    break;
                case 4:
                    Picasso.with(context).load(R.drawable.ic_sad).into(ivAction);
                    break;
            }

            Picasso.with(context).load(R.drawable.iv_car_hospital).into(ivAction);

            tvTimeAgo.setText(data.getTime());


            tvDes.setText(convert(data.getNameList()) + "ddax");
        }
    }

    private CharSequence convert(List<String> names){
        if (names.size()<= 1){
            return (Html.fromHtml("<b>" + names.get(0) + "</b>"));
        }else if (names.size() == 2){
            return (Html.fromHtml("<b>" + names.get(0) + "</b>")+ " và "
            +Html.fromHtml("<b>" + names.get(1) + "</b>"));
        }else if (names.size() == 3){
            return (Html.fromHtml("<b>" + names.get(0) + "</b>")+ ", "
                    +Html.fromHtml("<b>" + names.get(1) + "</b>")+ " và "
                    +Html.fromHtml("<b>" + names.get(2) + "</b>"));
        }else {
            CharSequence c = (Html.fromHtml("<b>" + names.get(0) + "</b>")+ ", "
                    +Html.fromHtml("<b>" + names.get(1) + "</b>")
            +Html.fromHtml("<b>" + (names.size() - 2) + "</b>")  );

            return c;
        }
    }
}
