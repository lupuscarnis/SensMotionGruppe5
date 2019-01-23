package com.example.nicolai.sensmotiongruppe5.Rute;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.nicolai.sensmotiongruppe5.R;

public class Rute_Canvas extends View {


    Context context;
    private Bitmap bitmap;
    private Bitmap bitmap1;
    private Canvas canvas;
    private Path path;
    private Paint mPaint;
    private float mx = 50, my = 60;
    private Paint mBitmapPaint;

    public Rute_Canvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        path = new Path();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5f);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.massivemap);
        bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        canvas = new Canvas(bitmap);
        bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.icons8running480);
        bitmap1 = bitmap1.copy(Bitmap.Config.ARGB_8888, true);

    }


    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.massivemap);
        bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        canvas = new Canvas(bitmap);

    }


    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        Rect src = new Rect(0, 0, bitmap.getWidth() - 1, bitmap.getHeight() - 1);
        Rect dest = new Rect(0, 0, width - 1, height - 1);
        canvas.drawBitmap(bitmap, src, dest, null);
        canvas.drawPath(path, mPaint);

        canvas.drawBitmap(bitmap1, mx ,my,null);

        

    }
    public void drawMan(float endX, float endY) {
        mx = endX - 20;
        my = endY - 20;
        invalidate();


    }


    public void drawRute(float startX, float startY,float endX,float endY) {
        canvas.drawLine(startX, startY,endX,endY,mPaint);
        invalidate();
    }

    public void drawHighLight(float endX, float endY, float radius) {
        mBitmapPaint.setColor(Color.RED);
        canvas.drawCircle(endX, endY, radius, mBitmapPaint);
        invalidate();
    }



}