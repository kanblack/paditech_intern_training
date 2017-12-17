package com.toring.paditechproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.toring.paditechproject.R;
import com.toring.paditechproject.network.model.ComboImage;
import com.toring.paditechproject.network.model.P1Image;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ToRing on 12/17/2017.
 */

public class P1SectionAdapter extends RecyclerView.Adapter<P1SectionAdapter.SectionVH> {
    private Context context;
    private List<ComboImage> comboImages;
    private int type;

    public P1SectionAdapter(Context context, List<ComboImage> comboImages) {
        this.context = context;
        this.comboImages = comboImages;
    }

    public P1SectionAdapter(Context context, List<ComboImage> comboImages, int type) {
        this.context = context;
        this.comboImages = comboImages;
        this.type = type;
    }

    @Override
    public SectionVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        if (viewType == 0) {
            View view = inflater.inflate(R.layout.item_p1_1, parent, false);
            return new SectionVH1(view);
        }
        else if (viewType == 1) {
            View view = inflater.inflate(R.layout.item_p1_2, parent, false);
            return new SectionVH2(view);
        } else {
            View view = inflater.inflate(R.layout.item_p1_3, parent, false);
            return new SectionVH3(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }

    @Override
    public void onBindViewHolder(SectionVH holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return comboImages.size();
    }

    public class SectionVH1 extends SectionVH {
        TextView tvDes1;

        private RoundedImageView iv1, iv2, iv3;
        private TextView tvTitle1, tvTiltle2, tvTile3;

        public SectionVH1(View itemView) {
            super(itemView);

            tvDes1 = itemView.findViewById(R.id.tv_des_1);


            iv1 = itemView.findViewById(R.id.iv_1);
            iv2 = itemView.findViewById(R.id.iv_2);
            iv3 = itemView.findViewById(R.id.iv_3);


            tvTitle1 = itemView.findViewById(R.id.tv_title_1);
            tvTiltle2 = itemView.findViewById(R.id.tv_title_2);
            tvTile3 = itemView.findViewById(R.id.tv_title_3);
        }

        @Override
        public void onBindViewHolder(int position) {

            ComboImage comboImage = comboImages.get(position);

            P1Image image = comboImage.getP1Images().get(0);
            Picasso.with(context).load(image.getUrl()).into(iv1);
            tvTitle1.setText(image.getTitle());
            tvDes1.setText(image.getShortDescription());

            image = comboImage.getP1Images().get(1);
            Picasso.with(context).load(image.getUrl()).into(iv2);
            tvTiltle2.setText(image.getTitle());

            image = comboImage.getP1Images().get(2);
            Picasso.with(context).load(image.getUrl()).into(iv3);
            tvTile3.setText(image.getTitle());
        }
    }

    public class SectionVH2 extends SectionVH {
        private RoundedImageView iv4;
        private TextView tvTitle4;

        private RoundedImageView iv1, iv2, iv3;
        private TextView tvTitle1, tvTiltle2, tvTile3;

        public SectionVH2(View itemView) {
            super(itemView);

            iv1 = itemView.findViewById(R.id.iv_1);
            iv2 = itemView.findViewById(R.id.iv_2);
            iv3 = itemView.findViewById(R.id.iv_3);
            iv4 = itemView.findViewById(R.id.iv_4);


            tvTitle1 = itemView.findViewById(R.id.tv_title_1);
            tvTiltle2 = itemView.findViewById(R.id.tv_title_2);
            tvTile3 = itemView.findViewById(R.id.tv_title_3);
            tvTitle4 = itemView.findViewById(R.id.tv_title_4);
        }

        @Override
        public void onBindViewHolder(int position) {

            ComboImage comboImage = comboImages.get(position);

            P1Image image = comboImage.getP1Images().get(0);
            Picasso.with(context).load(image.getUrl()).into(iv1);
            tvTitle1.setText(image.getTitle());

            image = comboImage.getP1Images().get(1);
            Picasso.with(context).load(image.getUrl()).into(iv2);
            tvTiltle2.setText(image.getTitle());

            image = comboImage.getP1Images().get(2);
            Picasso.with(context).load(image.getUrl()).into(iv3);
            tvTile3.setText(image.getTitle());

            image = comboImage.getP1Images().get(3);
            Picasso.with(context).load(image.getUrl()).into(iv4);
            tvTitle4.setText(image.getTitle());
        }
    }

    public class SectionVH3 extends SectionVH {

        private CircleImageView iv1, iv2, iv3;
        private TextView tvTitle1, tvTiltle2, tvTile3;

        public SectionVH3(View itemView) {
            super(itemView);

            iv1 = itemView.findViewById(R.id.iv_1);
            iv2 = itemView.findViewById(R.id.iv_2);
            iv3 = itemView.findViewById(R.id.iv_3);


            tvTitle1 = itemView.findViewById(R.id.tv_title_1);
            tvTiltle2 = itemView.findViewById(R.id.tv_title_2);
            tvTile3 = itemView.findViewById(R.id.tv_title_3);
        }

        @Override
        public void onBindViewHolder(int position) {

            ComboImage comboImage = comboImages.get(position);

            P1Image image = comboImage.getP1Images().get(0);
            Picasso.with(context).load(image.getUrl()).into(iv1);
            tvTitle1.setText(image.getTitle());

            image = comboImage.getP1Images().get(1);
            Picasso.with(context).load(image.getUrl()).into(iv2);
            tvTiltle2.setText(image.getTitle());

            image = comboImage.getP1Images().get(2);
            Picasso.with(context).load(image.getUrl()).into(iv3);
            tvTile3.setText(image.getTitle());
        }
    }

    public abstract class SectionVH extends RecyclerView.ViewHolder {


        public SectionVH(View itemView) {
            super(itemView);
        }

        public void bindView(int position) {
            onBindViewHolder(position);
        }

        public abstract void onBindViewHolder(int position);
    }
}
