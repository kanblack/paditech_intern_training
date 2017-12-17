package com.toring.paditechproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.toring.paditechproject.R;
import com.toring.paditechproject.model.P3Task;

import java.util.List;

/**
 * Created by tr on 12/12/17.
 */

public class P3TaskAdapter extends RecyclerView.Adapter<P3TaskAdapter.TaskVH> {
    private Context context;
    private List<P3Task> p3TaskList;

    public P3TaskAdapter(Context context, List<P3Task> p3TaskList) {
        this.context = context;
        this.p3TaskList = p3TaskList;
    }

    @Override
    public TaskVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_p3_task, parent, false);
        return new TaskVH(view);
    }

    @Override
    public void onBindViewHolder(TaskVH holder, int position) {
        holder.setData(p3TaskList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return p3TaskList.size();
    }

    public class TaskVH extends RecyclerView.ViewHolder {
        private TextView tvName;
        private ImageView ivOval;
        private View viewTop, viewBotom;

        public TaskVH(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_task);
            ivOval = itemView.findViewById(R.id.iv_oval);
            viewTop = itemView.findViewById(R.id.v_connect_top);
            viewBotom = itemView.findViewById(R.id.v_connect_bottom);
        }

        public void setData(P3Task data, int poosition) {
            tvName.setText(data.getName());

            StateListDrawable shapeDrawable = (StateListDrawable) ivOval.getBackground();
            shapeDrawable.setColorFilter(Color.parseColor(data.getColor()), PorterDuff.Mode.SRC_ATOP);

            if (poosition == 0) {
                viewTop.setVisibility(View.GONE);
            }

            if (poosition == p3TaskList.size() - 1) {
                viewBotom.setVisibility(View.GONE);
            }
        }
    }
}
