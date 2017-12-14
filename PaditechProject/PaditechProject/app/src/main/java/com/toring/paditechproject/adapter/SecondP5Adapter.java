package com.toring.paditechproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.toring.paditechproject.R;
import com.toring.paditechproject.model.Message;

import java.util.List;

public class SecondP5Adapter extends RecyclerView.Adapter<SecondP5Adapter.VH> {
    private Context context;
    private List<Message> messageList;

    public SecondP5Adapter(Context context, List<Message> messageList) {
        this.context = context;
        this.messageList = messageList;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_second_p5, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.setData(messageList.get(position));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView tvName, tvTime, tvMessage;
        ImageView ivSeen, ivOnline, ivAvatar;

        public VH(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_sender);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvMessage = itemView.findViewById(R.id.tv_mes);

            ivSeen = itemView.findViewById(R.id.iv_seen);
            ivOnline = itemView.findViewById(R.id.iv_online);
            ivAvatar = itemView.findViewById(R.id.iv_avatar_sender);
        }

        public void setData(Message data) {
            ivAvatar.setImageResource(data.getAvatar());
            tvName.setText(data.getName());
            tvMessage.setText(data.getMes());
            tvTime.setText(data.getTime());

            if (!data.isMy_seen()){
                tvName.setTypeface(tvName.getTypeface(), Typeface.BOLD);
                tvMessage.setTypeface(tvMessage.getTypeface(), Typeface.BOLD);
            }

            if (!data.isSeen()){
                ivSeen.setVisibility(View.GONE);
            }

            if (!data.isOnline()){
                ivOnline.setVisibility(View.GONE);
            }
        }
    }
}
