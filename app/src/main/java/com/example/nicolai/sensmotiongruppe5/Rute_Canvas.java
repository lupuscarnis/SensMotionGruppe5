package com.example.nicolai.sensmotiongruppe5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Rute_Canvas extends View {

    int width;

    int hight;
    Context context;
    private Bitmap bitmap;
    private Canvas canvas;
    private Path path;
    private Paint mPaint;
    private float mx, my;
    private Paint mBitmapPaint;

    // https://varun.ca/polar-coords/       good for something like this
    public Rute_Canvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        path = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(10f);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        PathEffect effect = new PathDashPathEffect(
                makeConcaveArrow(24.0f, 14.0f),    // "stamp"
                36.0f,                            // advance, or distance between two stamps
                0.0f,                             // phase, or offset before the first stamp
                PathDashPathEffect.Style.ROTATE); //
        mPaint.setPathEffect(effect);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fossball);
        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        canvas = new Canvas(mutableBitmap);


    }

    private static Path makeConcaveArrow(float length, float height) {
        Path p = new Path();
        p.moveTo(-2.0f, -height / 2.0f);
        p.lineTo(length - height / 4.0f, -height / 2.0f);
        p.lineTo(length, 0.0f);
        p.lineTo(length - height / 4.0f, height / 2.0f);
        p.lineTo(-2.0f, height / 2.0f);
        p.lineTo(-2.0f + height / 4.0f, 0.0f);
        p.close();
        return p;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fossball);
        bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        canvas = new Canvas(bitmap);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, mBitmapPaint);
        canvas.drawPath(path, mPaint);
    }

    public void clearCanvas() {
        path.reset();
        invalidate();
    }

    public void Draw(float startX, float startY, float endX, float endY) {
        path.moveTo(startX, startY);
        path.lineTo(endX, endY);


    }

    public void DrawCircle(int h, int k, int r) {


        path.addCircle(h, k, r, Path.Direction.CW);


    }

}
