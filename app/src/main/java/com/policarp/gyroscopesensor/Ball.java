package com.policarp.gyroscopesensor;

public class Ball {
    public Point Center;
    public float Radius;

    public Ball(Point center, float radius) {
        Center = center;
        Radius = radius;
        Velocity = new Velocity(0, 0);
    }

    public Velocity Velocity;
    public void OnFrameChanged(){
        Center.X += Velocity.VX();
        Center.Y += Velocity.VY();
    }
}
