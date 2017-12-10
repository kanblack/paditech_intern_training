package com.toring.paditechproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.toring.paditechproject.R;
import com.toring.paditechproject.item.ItemMenu;

import java.util.List;

/**
 * Created by ToRing on 12/11/2017.
 */

public class MyMenuAdapter extends RecyclerView.Adapter<MyMenuAdapter.MenuVH> {
    private Context context;
    private List<ItemMenu> itemMenuList;

    public MyMenuAdapter(Context context, List<ItemMenu> itemMenuList) {
        this.context = context;
        this.itemMenuList = itemMenuList;
    }

    @Override
    public MenuVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_menu_layout, parent, false);
        return new MenuVH(view);
    }

    @Override
    public void onBindViewHolder(MenuVH holder, int position) {
        holder.setData(itemMenuList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemMenuList.size();
    }

    public class MenuVH extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvDes;
        private View view;

        public MenuVH(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvDes = itemView.findViewById(R.id.tv_des);

            view = itemView;
        }

        public void setData(final ItemMenu data) {
            tvName.setText(data.getName());
            tvDes.setText(data.getDescription());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(data.getIntent());
                }
            });
        }
    }
}
