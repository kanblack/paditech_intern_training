package com.pesteam.watchimage.Canvas;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.pesteam.watchimage.R;
import com.pesteam.watchimage.Screen5.FragmentScreen5;

/**
 * Created by bangindong on 12/20/2017.
 */

public class EditableImageView extends RelativeLayout {

    private int width;
    private int height;
    private Context context;
    View imageView;
    View rootView;
    private Bitmap frame;

    public CanvasView canvasView;

    public void setFrame(Bitmap frame) {
        this.frame = frame;
    }


    public void setImage(String url){
        Glide.with(this).load(url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(final Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                imageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        imageView.getViewTreeObserver().removeOnPreDrawListener(this);
                        width = imageView.getMeasuredWidth();
                        height = imageView.getMeasuredHeight();
//                        resize();
                        requestLayout();
                        return false;
                    }
                });
                return false;
            }
        })
                .into((ImageView) imageView);

    }

    private void innit(Context context){
        rootView = inflate(context, R.layout.layout_editable_image, this);
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
        if(width>0) {
            setMeasuredDimension(width,height);
            canvasView.innit(width, height);
            if(frame!= null){
                canvasView.screen51DrawFrame(frame);
            }
            width = 0;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);

    }

    public void resize(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight =  (displayMetrics.heightPixels -  ((int)(TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 160, ((Activity) context).getResources().getDisplayMetrics()))));
        if(width  <  height){
            width = (int) ( width * ((float)deviceHeight/height));
            height = deviceHeight;
        } else if(width > height){
            height = (int) ( height * ((float) deviceWidth/width ));
            width = deviceWidth;
        }
    }
}
