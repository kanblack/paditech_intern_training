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
import com.toring.paditechproject.model.Task;

import java.util.List;

/**
 * Created by tr on 12/12/17.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskVH> {
    private Context context;
    private List<Task> taskList;

    public TaskAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @Override
    public TaskVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_task, parent, false);
        return new TaskVH(view);
    }

    @Override
    public void onBindViewHolder(TaskVH holder, int position) {
        holder.setData(taskList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
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

        public void setData(Task data, int poosition) {
            tvName.setText(data.getName());

            StateListDrawable shapeDrawable = (StateListDrawable) ivOval.getBackground();
            shapeDrawable.setColorFilter(Color.parseColor(data.getColor()), PorterDuff.Mode.SRC_ATOP);

            if (poosition == 0) {
                viewTop.setVisibility(View.GONE);
            }

            if (poosition == taskList.size() - 1) {
                viewBotom.setVisibility(View.GONE);
            }
        }
    }
}
