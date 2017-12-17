package com.toring.paditechproject.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.toring.paditechproject.R;
import com.toring.paditechproject.network.model.P1ComboImage;
import com.toring.paditechproject.network.model.P1Image;
import com.toring.paditechproject.network.model.P1Section;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tr on 12/17/17.
 */

public class P1Adapter extends RecyclerView.Adapter<P1Adapter.P1PartVH> {
    private Context context;
    private List<P1Section> sectionList;

    public P1Adapter(Context context, List<P1Section> sectionList) {
        this.context = context;
        this.sectionList = sectionList;
    }

    @Override
    public P1PartVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_p1_main_item, parent, false);
        return new P1PartVH(view);
    }

    @Override
    public void onBindViewHolder(P1PartVH holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }

    public class P1PartVH extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private RecyclerView rv;

        public P1PartVH(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            rv = itemView.findViewById(R.id.rv_p1);
        }

        public void bindView(int pos) {
            P1Section section = sectionList.get(pos);
            tvTitle.setText(section.getTitle());
            tvTitle.setBackgroundResource(context.getResources().getIdentifier("bg_p1_" + (pos + 1), "drawable", context.getPackageName()));
            List<P1ComboImage> p1ComboImageList = new ArrayList<>();
            if (pos == 1) {
                List<P1Image> imageList = new ArrayList<>();
                for (int i = 0; i < section.getP1Images().size(); i++) {
                    imageList.add(section.getP1Images().get(i));
                    if ((i + 1) % 4 == 0) {
                        p1ComboImageList.add(new P1ComboImage(imageList));
                        imageList = new ArrayList<>();
                    }
                }
            } else {
                List<P1Image> imageList = new ArrayList<>();
                for (int i = 0; i < section.getP1Images().size(); i++) {
                    imageList.add(section.getP1Images().get(i));
                    if ((i + 1) % 3 == 0) {
                        p1ComboImageList.add(new P1ComboImage(imageList));
                        imageList = new ArrayList<>();
                    }
                }
            }
            P1SectionAdapter adapter = new P1SectionAdapter(context, p1ComboImageList, pos);
            rv.setAdapter(adapter);
            rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        }
    }
}
