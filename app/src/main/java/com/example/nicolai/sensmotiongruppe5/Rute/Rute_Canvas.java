package com.example.nicolai.sensmotiongruppe5.Rute;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.util.AttributeSet;
import android.view.View;

import com.example.nicolai.sensmotiongruppe5.R;

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
    private Drawable man;

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
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fossball);
        bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        canvas = new Canvas(bitmap);


    }


    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fossball);
        bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        canvas = new Canvas(bitmap);

    }


    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, mBitmapPaint);

    }


    public void clearCanvas() {

    }


    public void Draw(float startX, float startY, float endX, float endY) {
        path.moveTo(startX, startY);
        path.lineTo(endX, endY);


    }


    public void DrawCircle(int h, int k, int r) {


        path.addCircle(h, k, r, Path.Direction.CW);


    }

    public void drawBitmap(Bitmap bitmap, float x, float y) {
  /*   Bitmap bitty;
        bitty = BitmapFactory.decodeResource(getResources(), R.drawable.fossball);
        bitty = bitty.copy(Bitmap.Config.ARGB_8888, true);
        canvas.setBitmap(bitty);
*/
        man = VectorDrawableCompat.create(getContext().getResources(), R.drawable.ic_image2vector, null);

        man.setBounds(0, 0, man.getIntrinsicWidth(), man.getIntrinsicHeight());
        man.draw(canvas);
        invalidate();

    }

    public void saveCanvas() {
        canvas.save();

    }

    public void restoreCanvas() {
        canvas.restore();
    }

    public void restoreToCount(int x) {
        canvas.restoreToCount(x);
    }


}