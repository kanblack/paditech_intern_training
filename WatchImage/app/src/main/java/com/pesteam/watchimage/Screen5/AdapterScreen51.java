package com.pesteam.watchimage.Screen5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pesteam.watchimage.Canvas.CanvasView;
import com.pesteam.watchimage.Canvas.EditableImageView;
import com.pesteam.watchimage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bangindong on 12/18/2017.
 */

public class AdapterScreen51 extends RecyclerView.Adapter<AdapterScreen51.BaseHolder> {


    private FragmentScreen51 fragmentScreen51;
    private int[] frame_horizontal = new int[]{
            R.drawable.frame_1,
            R.drawable.frame_2,
            R.drawable.frame_3,
            R.drawable.frame_4,
            R.drawable.frame_5,
            R.drawable.frame_6
    };


    AdapterScreen51(FragmentScreen51 fragmentScreen51) {
        this.fragmentScreen51 = fragmentScreen51;
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

        @BindView(R.id.frame_bottom_frag_scr51)
        ImageView frame_bottom;

        ChildHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        void onBinding(int position) {
            frame_bottom.setImageBitmap(getFrame(frame_horizontal[position]));
        }

        private Bitmap getFrame(int res){
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            return BitmapFactory.decodeResource(itemView.getResources(),res,options);
        }

        @Override
        public void onClick(View view) {
            Bitmap image = getFrame(frame_horizontal[getLayoutPosition()]);
            Log.e( "onClick: ",  fragmentScreen51.editable_img_screen51.getMeasuredWidth() + "   "+ fragmentScreen51.editable_img_screen51.getMeasuredHeight());
            fragmentScreen51.editable_img_screen51.canvasView.screen51DrawFrame(image);
        }
    }

}
