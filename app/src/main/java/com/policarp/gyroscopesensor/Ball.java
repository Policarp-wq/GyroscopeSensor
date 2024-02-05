package com.policarp.gyroscopesensor;

import androidx.annotation.NonNull;

public class Ball {
    public Point Center;
    public float Radius;

    public Ball(Point center, float radius) {
        Center = center;
        Radius = radius;
        Velocity = new Velocity(0, 0);
    }

    public Ball(Ball ball) {
        Center = new Point(ball.Center.X, ball.Center.Y);
        Radius = ball.Radius;
        Velocity = ball.Velocity;
    }

    public Velocity Velocity;
    public void OnFrameChanged(){
        Center.X += Velocity.VX();
        Center.Y += Velocity.VY();
    }

    @NonNull
    @Override
    public String toString() {
        return Center.toString();
    }
}
