package com.policarp.gyroscopesensor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.policarp.gyroscopesensor.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    Sensor mAccelerometer;
    Sensor mMagnetic;
    SensorManager mSensorManager;
    TextView xy;
    TextView xz;
    TextView yz;
    public Ball Ball;
    Rect bounds;
    BallsField field;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        bounds = getWindowManager().getCurrentWindowMetrics().getBounds();
        float Radius = 30;
        Ball = new Ball(new Point(bounds.centerX(), bounds.centerY()), Radius);
        xy = findViewById(R.id.XY);
        xz = findViewById(R.id.XZ);
        yz = findViewById(R.id.YZ);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        field = new BallsField(this, Ball, paint);
        setContentView(field);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(this, mMagnetic, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    float[] accel = new float[3];
    float[] magnetic = new float[3];
    float[] rotationMatrix = new float[16];
    float[] orientation = new float[3];

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            accel = event.values.clone();
        }
        if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            magnetic = event.values.clone();
        }
        SensorManager.getRotationMatrix(rotationMatrix, null, accel, magnetic);
        SensorManager.getOrientation(rotationMatrix, orientation);
        Ball.Velocity.Vector.X = Math.round(Math.toDegrees(orientation[2]));
        Ball.Velocity.Vector.Y = -Math.round(Math.toDegrees(orientation[1]));
        //Toast.makeText(this, "dadsafafaf", Toast.LENGTH_SHORT).show();
        //xy.setText("xy : " + String.valueOf(Math.round(Math.toDegrees(orientation[0])))); // На пользователя / от
        //xz.setText("xz : " + String.valueOf(Math.round(Math.toDegrees(orientation[1])))); // вверх вниз
        //yz.setText("yz : " + String.valueOf(Math.round(Math.toDegrees(orientation[2])))); // влево вправо
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}