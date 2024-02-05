package com.policarp.gyroscopesensor;

public class Point {
    public float X;
    public float Y;

    public Point(float x, float y) {
        X = x;
        Y = y;
    }

    @Override
    public String
    toString() {
        return "{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }
}
