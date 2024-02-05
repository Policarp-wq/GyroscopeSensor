package com.policarp.gyroscopesensor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayDeque;
import java.util.Queue;

public class BallsField extends View {
    public Ball Ball;
    public Paint Paint;
    public ArrayDeque<Ball> Trace;
    public final int TraceCnt = 120;
    public Rect Border;
    public BallsField(Context context, Ball ball, Rect border) {
        super(context);
        Ball = ball;
        Border = border;
        setBackgroundColor(Color.WHITE);
        Trace = new ArrayDeque<>();
        Paint = new Paint();
        Paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        if(!inBounds(Ball)){
            canvas.drawRect(Border, Paint);
            Ball.Center = new Point(Border.centerX(), Border.centerY());
            Trace.clear();
            invalidate();
            return;
        }
        Trace.add(new Ball(Ball));
        if(Trace.size() > TraceCnt)
            Trace.poll();
        Ball.OnFrameChanged();
        int c = 0;
        for(Ball ball : Trace){
            Paint.setAlpha(c);
            draw(canvas, ball);
            ++c;
        }
        invalidate();
    }
    public void draw(Canvas canvas, Ball ball){
        canvas.drawCircle(ball.Center.X, ball.Center.Y, ball.Radius, Paint);
    }
    public boolean inBounds(Ball ball){
        Point cent = ball.Center;
        float r = ball.Radius;
        return cent.X + r < Border.right && cent.X - r > Border.left
                && cent.Y + r > Border.top && cent.Y - r < Border.bottom;
    }
}
