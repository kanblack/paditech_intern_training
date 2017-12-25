package com.toring.myapplication.customvie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import com.toring.myapplication.R;

public class DrawingView extends ImageView {
    Paint mPaint;
    //MaskFilter  mEmboss;
    //MaskFilter  mBlur;
    Bitmap bitmapAll;
    Canvas mCanvas, m;
    Path mPath;

    private Context context;

    private Bitmap bitmap;
    private boolean canDrawLine = false, canDrawImage = false;

    public void setCanDrawLine(boolean canDrawLine) {
        this.canDrawLine = canDrawLine;
    }

    public void setCanDrawImage(boolean canDrawImage) {
        this.canDrawImage = canDrawImage;
    }

    public void setBitmap(int res) {
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), res);
    }

    public void setColor(int color) {
        mPaint.setColor(context.getResources().getColor(color));
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(context.getResources().getColor(R.color.background_color));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(10);

        mPath = new Path();
        this.context = context;

    }

    public void reset() {
        mPaint.setColor(Color.BLACK);
//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mCanvas.drawPath(mPath, mPaint);
//        mPaint.setXfermode(null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmapAll = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(bitmapAll);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        m = canvas;
        canvas.drawBitmap(bitmapAll, 0, 0, mPaint);
        canvas.drawPath(mPath, mPaint);

        if (canDrawImage && bitmap != null)
            canvas.drawBitmap(bitmap, mX, mY, mPaint);
    }

    private float mX, mY;

    private void touch_start(float x, float y) {
        mPath = new Path();
        if (canDrawLine) {
            mPath.moveTo(x, y);
        }
        mX = x;
        mY = y;
    }

    private void touch_move(float x, float y) {
        if (canDrawLine) {
            mPath.quadTo(mX, mY, x, y);
        }
        mX = x;
        mY = y;
    }

    private void touch_up() {
        if (canDrawLine) {
            mCanvas.drawPath(mPath, mPaint);
            mPath.reset();
        }
        if (canDrawImage && bitmap != null) {
            mCanvas.drawBitmap(bitmap, mX, mY, mPaint);
            mPath.reset();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
        }
        return true;
    }
}