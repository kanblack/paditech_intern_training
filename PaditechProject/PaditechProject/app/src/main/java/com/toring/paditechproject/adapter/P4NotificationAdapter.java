package com.toring.paditechproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.toring.paditechproject.R;
import com.toring.paditechproject.model.P4Notification;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tr on 12/13/17.
 */

public class P4NotificationAdapter extends RecyclerView.Adapter<P4NotificationAdapter.NotificationVH> {
    private Context context;
    private List<P4Notification> p4NotificationList;

    public P4NotificationAdapter(Context context, List<P4Notification> p4NotificationList) {
        this.context = context;
        this.p4NotificationList = p4NotificationList;
    }

    @Override
    public NotificationVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_noti, parent, false);
        return new NotificationVH(view);
    }

    @Override
    public void onBindViewHolder(NotificationVH holder, int position) {
        holder.setData(p4NotificationList.get(position));
    }

    @Override
    public int getItemCount() {
        return p4NotificationList.size();
    }

    public class NotificationVH extends RecyclerView.ViewHolder {
        private View view;
        private TextView tvDes, tvTimeAgo;
        private ImageView ivAction;
        private CircleImageView ivAva;

        public NotificationVH(View itemView) {
            super(itemView);
            view = itemView;

            tvDes = itemView.findViewById(R.id.tv_des);
            tvTimeAgo = itemView.findViewById(R.id.tv_time_ago);
            ivAction = itemView.findViewById(R.id.iv_action);
            ivAction = itemView.findViewById(R.id.iv_ava);
        }

        public void setData(P4Notification data) {
            if (!data.isSeen()) {
                view.setBackgroundColor(Color.parseColor("#dfe5f2"));
            }

            String action_text = "";

            switch (data.getAction()) {
                case 1:
                    Picasso.with(context).load(R.drawable.ic_like).into(ivAction);
                    action_text = " đã thích ảnh của bạn";
                    break;
                case 2:
                    Picasso.with(context).load(R.drawable.ic_wow).into(ivAction);
                    action_text = " đã bày tỏ cảm xúc về bài viết của bạn";
                    break;
                case 3:
                    Picasso.with(context).load(R.drawable.ic_heart).into(ivAction);
                    action_text = " đã bày tỏ cảm xúc về bình luận của bạn của bạn";
                    break;
                case 4:
                    Picasso.with(context).load(R.drawable.ic_sad).into(ivAction);
                    action_text = " có sinh nhật ngày hôm nay. Hãy chúc chúng nó đi";
                    break;
            }

            Picasso.with(context).load(R.drawable.iv_car_hospital).into(ivAction);
            tvTimeAgo.setText(data.getTime());
            tvDes.setText(Html.fromHtml(convert(data.getNameList()) + "<pr>" + action_text + "</pr>"));
        }
    }

    private CharSequence convert(List<String> names) {
        if (names.size() <= 1) {
            return ("<b>" + names.get(0) + "</b>");
        } else if (names.size() == 2) {
            return ("<b>" + names.get(0) + "</b>" + "<pr> và </pr>" + "<b>" + names.get(1) + "</b>");
        } else if (names.size() == 3) {
            return ("<b>" + names.get(0) + "</b>" + "<pr>, </pr>"
                    + "<b>" + names.get(1) + "</b>" + "<pr> và </pr>"
                    + "<b>" + names.get(2) + "</b>");
        } else {
            CharSequence content = "<b>" + names.get(0) + "</b>" + "<pr>, </pr>"
                    + "<b>" + names.get(1) + "</b>" + "<pr> và </pr>"
                    + "<b>" + (names.size() - 2) + " other people </b>";
            return content;
        }
    }
}
