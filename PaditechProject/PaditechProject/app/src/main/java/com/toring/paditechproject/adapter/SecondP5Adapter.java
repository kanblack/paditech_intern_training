package com.toring.paditechproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.toring.paditechproject.R;
import com.toring.paditechproject.model.Message;
import com.toring.paditechproject.model.Person;

import java.util.List;

public class SecondP5Adapter extends RecyclerView.Adapter<SecondP5Adapter.VH> {
    private Context context;
    private List messageList;

    public SecondP5Adapter(Context context, List messageList) {
        this.context = context;
        this.messageList = messageList;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        if (viewType == 0) {
            view = inflater.inflate(R.layout.item_second_recycle, parent, false);
            return new VH1(view);
        } else {
            view = inflater.inflate(R.layout.item_second_p5, parent, false);
            return new VH2(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class VH2 extends SecondP5Adapter.VH {
        TextView tvName, tvTime, tvMessage;
        ImageView ivSeen, ivOnline, ivAvatar;

        public VH2(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_sender);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvMessage = itemView.findViewById(R.id.tv_mes);

            ivSeen = itemView.findViewById(R.id.iv_seen);
            ivOnline = itemView.findViewById(R.id.iv_online);
            ivAvatar = itemView.findViewById(R.id.iv_avatar_sender);
        }

        @Override
        public void onData(int pos) {
            Message data = (Message) messageList.get(pos);

            ivAvatar.setImageResource(data.getAvatar());
            tvName.setText(data.getName());
            tvMessage.setText(data.getMes());
            tvTime.setText(data.getTime());

            if (!data.isMy_seen()) {
                tvName.setTypeface(tvName.getTypeface(), Typeface.BOLD);
                tvMessage.setTypeface(tvMessage.getTypeface(), Typeface.BOLD);
            }

            if (!data.isSeen()) {
                ivSeen.setVisibility(View.GONE);
            }

            if (!data.isOnline()) {
                ivOnline.setVisibility(View.GONE);
            }

            Log.e("adapter", "onData: + 2" );

        }
    }

    public class VH1 extends SecondP5Adapter.VH {
        RecyclerView recyclerView;

        public VH1(View itemView) {
            super(itemView);

            recyclerView = itemView.findViewById(R.id.rv);
        }

        @Override
        public void onData(int pos) {
            List<Person> personList = (List<Person>) messageList.get(pos);
            Log.e("adapter", "onData: + 1" );

            FirstP5Adapter firstP5Adapter = new FirstP5Adapter(context, personList);
            recyclerView.setAdapter(firstP5Adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        }
    }

    public class VH extends RecyclerView.ViewHolder {
        public VH(View itemView) {
            super(itemView);
        }

        public void setData(int pos) {
            onData(pos);
        }

        public void onData(int pos) {
            Log.e("adapter", "onData: + parent" );
        }
    }
}
