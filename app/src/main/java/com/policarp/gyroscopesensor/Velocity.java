package com.policarp.gyroscopesensor;

public class Velocity {
    public float VX(){
        return Vector.X * Default;
    }
    public float VY(){
        return Vector.Y * Default;
    }

    public Velocity(float x, float y) {
        Vector = new Point(x, y);
    }

    public Point Vector;
    public float Default = 0.1f;
}
