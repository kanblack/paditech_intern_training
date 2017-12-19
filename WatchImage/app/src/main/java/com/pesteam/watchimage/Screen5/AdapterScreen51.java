package com.pesteam.watchimage.Screen5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pesteam.watchimage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bangindong on 12/18/2017.
 */

public class AdapterScreen51 extends RecyclerView.Adapter<AdapterScreen51.BaseHolder> {


    private String img_url;
    private FragmentScreen51 fragmentScreen51;
    private int[] frame_horizontal = new int[]{
            R.drawable.frame_1,
            R.drawable.frame_2,
            R.drawable.frame_3,
            R.drawable.frame_4,
            R.drawable.frame_5,
            R.drawable.frame_6
    };
    private int [] frame_vertical = new int[]{

    };
    private Bitmap bitmap;

    public AdapterScreen51(FragmentScreen51 fragmentScreen51) {
        this.fragmentScreen51 = fragmentScreen51;
    }

    void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChildHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.child_rcv_bottom_fragment_screen51,parent,false));
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return frame_horizontal.length;
    }

    abstract class BaseHolder extends RecyclerView.ViewHolder {
        BaseHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(int position){
            onBinding(position);
        }

        abstract void onBinding(int position);
    }

    class ChildHolder extends BaseHolder implements View.OnClickListener{

        @BindView(R.id.img_rcv_screen51)
        ImageView img;

        ChildHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        void onBinding(int position) {
            img.setImageBitmap(putFrameOnImg(bitmap, frame_horizontal[position],200));
        }

        private Bitmap putFrameOnImg(Bitmap a, int res,int b){
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            int i = a.getWidth()/b;
            if(i == 0){
                i = 1;
            }
            Bitmap temp = Bitmap.createBitmap(a.getWidth()/i, a.getHeight()/i, Bitmap.Config.ARGB_8888);
            Bitmap img_2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(itemView.getResources(),res,options),a.getWidth()/i, a.getHeight()/i,true);
            Bitmap img_3 = Bitmap.createScaledBitmap(a,a.getWidth()/i, a.getHeight()/i,true);
            Canvas canvas = new Canvas(temp);
            canvas.drawBitmap(img_3,0,0,null);
            canvas.drawBitmap(img_2,0,0,null);
            return temp;
        }


        @Override
        public void onClick(View view) {
            fragmentScreen51.img_big.setImageBitmap(putFrameOnImg(bitmap, frame_horizontal[getLayoutPosition()],600));
        }
    }

}
