package com.toring.myapplication.customvie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.toring.myapplication.R;

import java.util.ArrayList;
import java.util.List;

class Point {
    float x, y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }
}

public class DrawingView extends ImageView {
    private List<Path> paths = new ArrayList<>();
    private List<Integer> colorList = new ArrayList<>();

    int color = R.color.color_1;
    int countPaths = 0;

    Paint mPaint;
    Path mPath;

    private Context context;

    private Bitmap bitmap, old, image;

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void saveImage(){
        old = bitmap;
    }

    private boolean canDrawLine = false, canDrawImage = false;

    public void setCanDrawLine(boolean canDrawLine) {
        this.canDrawLine = canDrawLine;
    }

    public void setCanDrawImage(boolean canDrawImage) {
        this.canDrawImage = canDrawImage;
    }

    public void setCountPaths(int countPaths) {
        this.countPaths = countPaths;
    }

    public void setBitmap(int res) {
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), res);
        this.bitmap = Bitmap.createScaledBitmap(this.bitmap, this.getWidth(), this.getHeight(), false);
        invalidate();
    }

    public void setColor(int color) {
        mPaint.setColor(context.getResources().getColor(color));
        this.color = color;
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
        mPaint.setStrokeWidth(3);

        mPath = new Path();
        this.context = context;

    }

    public void reset() {
        if (countPaths > 0) {
            for (int i = 1; i <= countPaths; i++) {
                paths.remove(paths.size() - 1);
                colorList.remove(colorList.size() - 1);
            }
        }
        postInvalidate();
        countPaths = 0;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        for (int i = 0; i < paths.size(); i++) {
            mPaint.setColor(context.getResources().getColor(colorList.get(i)));
            canvas.drawPath(paths.get(i), mPaint);
        }
        mPaint.setColor(context.getResources().getColor(color));
        if (canDrawLine)
            canvas.drawPath(mPath, mPaint);

        if (old != null)
            canvas.drawBitmap(old, 0, 0, mPaint);
        if (canDrawImage && bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();
        // Checks for the event that occurs
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(pointX, pointY);
                return true;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(pointX, pointY);
                break;
            case MotionEvent.ACTION_UP:
                if (canDrawLine) {
                    paths.add(mPath);
                    colorList.add(color);
                    countPaths++;
                }
                mPath = new Path();
                break;
            default:
                return false;
        }
        // Force a view to draw again
        postInvalidate();
        return true;
    }


    public Bitmap get (Bitmap bitmap){
        Bitmap mbitmap = Bitmap.createScaledBitmap(image, bitmap.getWidth(), bitmap.getHeight(), false);
        Canvas canvas = new Canvas(mbitmap);
        Bitmap bitmap1 = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas1 = new Canvas(bitmap1);
        for (int i = 0; i < paths.size(); i++) {
            mPaint.setColor(context.getResources().getColor(colorList.get(i)));
            canvas1.drawPath(paths.get(i), mPaint);
        }
        mPaint.setColor(context.getResources().getColor(color));
        if (old != null)
            canvas1.drawBitmap(old, 0, 0, mPaint);

        bitmap1 = Bitmap.createScaledBitmap(bitmap1, bitmap.getWidth(), bitmap.getHeight(), false);
        canvas.drawBitmap(bitmap1, 0, 0, mPaint);

        return mbitmap;
    }

    public Bitmap get (){
        Bitmap mbitmap = Bitmap.createScaledBitmap(image, image.getWidth(), image.getHeight(), false);
        Canvas canvas = new Canvas(mbitmap);
        Bitmap bitmap1 = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas1 = new Canvas(bitmap1);
        for (int i = 0; i < paths.size(); i++) {
            mPaint.setColor(context.getResources().getColor(colorList.get(i)));
            canvas1.drawPath(paths.get(i), mPaint);
        }
        mPaint.setColor(context.getResources().getColor(color));
        if (old != null)
            canvas1.drawBitmap(old, 0, 0, mPaint);

        bitmap1 = Bitmap.createScaledBitmap(bitmap1, image.getWidth(), image.getHeight(), false);
        canvas.drawBitmap(bitmap1, 0, 0, mPaint);

        return mbitmap;
    }
}