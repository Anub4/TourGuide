package com.example.tourguide;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnGetStarted;
    private Boolean status = true;

    private SensorManager mSensorManager;

    //private ShakeEventListener mSensorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetStarted = findViewById(R.id.btnGetStarted);
        btnGetStarted.setOnClickListener(this);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        mSensorListener = new ShakeEventListener();
//
//        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {
//
//            public void onShake() {
//                Toast.makeText(MainActivity.this, "Shake!", Toast.LENGTH_SHORT).show();
//                showHelpPage();
//            }
//        });
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        mSensorManager.registerListener(mSensorListener,
//                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
//                SensorManager.SENSOR_DELAY_UI);
    //}

//    @Override
//    protected void onPause() {
//        mSensorManager.unregisterListener(mSensorListener);
//        super.onPause();
//    }
    public void showHelpPage() {
        Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent1);
    }
}


