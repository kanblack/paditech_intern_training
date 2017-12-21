package com.pesteam.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Created by bangindong on 12/20/2017.
 */

public class EditableImageView extends RelativeLayout {

    private int width;
    private int height;
    private Context context;
    private View imageView;
    View rootView;
    private CanvasView canvasView;

    public void setImage(String url){
        Glide.with(this).load(url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                imageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        imageView.getViewTreeObserver().removeOnPreDrawListener(this);
                        width = imageView.getMeasuredWidth();
                        height = imageView.getMeasuredHeight();
                        requestLayout();
                        Log.e( "onPreDraw: ", imageView.getMeasuredWidth()+"" );
                        return false;
                    }
                });
                return false;
            }
        })
                .into((ImageView) imageView);

    }

    private void innit(Context context){
            rootView = inflate(context, R.layout.test, this);
            imageView = (ImageView) rootView.findViewById(R.id.img);
            canvasView = (CanvasView) rootView.findViewById(R.id.canvas);

    }

    public EditableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        innit(context);
    }

    public EditableImageView(Context context) {
        super(context);
        this.context = context;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasView.invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width,height);
        Log.e( "onMeasure: ", width +"    "+height );
        if(width>0) {
            canvasView.innit(width, height);
        }
    }
}
