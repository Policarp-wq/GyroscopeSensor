package com.policarp.gyroscopesensor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

public class BallsField extends View {
    public Ball Ball;
    public Paint Paint;
    public float k = 1;
    public BallsField(Context context, Ball ball, Paint paint) {
        super(context);
        Ball = ball;
        Paint = paint;
        setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        Ball.OnFrameChanged();
        canvas.drawCircle(Ball.Center.X, Ball.Center.Y, Ball.Radius, Paint);
        //k += 10;
        invalidate();
    }
}
