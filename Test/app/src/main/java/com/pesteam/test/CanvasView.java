package com.pesteam.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by bangindong on 12/19/2017.
 */

public class CanvasView extends View {


    public int width;
    public int height;
    private Bitmap mbitmap;
    private Canvas mcanvas;
    private ArrayList<Path> paths = new ArrayList<>();
    private Path mpath;
    private Paint mPaint;
    private ArrayList<Integer> color = new ArrayList<>();
    private float mX, mY;
    private static final float TOLERANCE = 5;
    private Paint mBitmapPaint = new Paint(Paint.DITHER_FLAG);
    Context context;

    public void ChangePaint(int colors) {

        mpath = new Path();
        paths.add(mpath);
        color.add(colors);

    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        mpath = new Path();
        paths.add(mpath);
        mPaint = new Paint();
        mPaint.setColor(context.getResources().getColor(R.color.material_amber200));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);
        color.add(R.color.material_amber200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        for (int i = 0; i < paths.size(); i++) {
            mPaint.setColor(context.getResources().getColor(color.get(i)));
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            mPaint.setStrokeWidth(4f);
            mcanvas.drawPath(paths.get(i),mPaint);
        }
        canvas.drawBitmap(mbitmap, 0, 0, mBitmapPaint);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width,height);
    }

    public void innit(int w, int h) {
        width = w;
        height =h;
        mbitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mcanvas = new Canvas(mbitmap);
        requestLayout();
    }

    public Bitmap scaleBitmap(int width, int height){
        return Bitmap.createScaledBitmap(mbitmap, width, height,true);
    }

    private void onStartTouch(float x, float y) {
        mpath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void moveTouch(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            mpath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void clearCanvas() {
        paths.clear();
        invalidate();
    }

    private void upTouch() {
        mpath.lineTo(mX, mY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                onStartTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                upTouch();
                invalidate();
                break;
        }
        return true;
    }
}
