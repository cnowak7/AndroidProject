package com.example.cnowak_rperez.randomknowledgequiz;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class SpinningTextView extends View {
    private int rotation = 0, dr = 10;
    private int width, height;
    private boolean paused;

    private Paint paint = new Paint();
    private int color = Color.GREEN;
    private Typeface typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC);

    private Handler mHandler = new Handler();

    public SpinningTextView(Context context) {
        super(context);
    }

    public SpinningTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void startAnimation() {
        paused = false;
//        frameCount = 0;
//        timeStart = System.currentTimeMillis();
        mHandler.post(update);
    }

    public void stopAnimation() {
        paused = true;
    }

    private Runnable update = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(getResources().getColor(R.color.colorPrimary));

        rotation += dr;
        if (rotation >= 360) {
            rotation %= 360;
        }

        paint.setColor(color);
        paint.setTextSize(50);
        paint.setTypeface(typeface);
        paint.setTextAlign(Paint.Align.CENTER);

        canvas.translate(width/2, height/2);
        canvas.rotate(rotation);
        canvas.drawText("QUIZ COMPLETED", 0, 0, paint);

        if (!paused) mHandler.postDelayed(update, 15);
    }
}
