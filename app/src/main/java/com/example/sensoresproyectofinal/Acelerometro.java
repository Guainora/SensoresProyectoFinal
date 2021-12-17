package com.example.sensoresproyectofinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

public class Acelerometro extends Activity implements SensorEventListener {

    private float lastX, lastY, lastZ;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private float deltaX = 0;
    private float deltaY = 0;
    private float deltaZ = 0;
    Intent intent;
    Button regresar;

    ConstraintLayout ln;

    private TextView currentX, currentY, currentZ;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acelerometro);
        initializeViews();
        regresar=findViewById(R.id.regresar);
        regresar.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){

            intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        }
        } );

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION/*TYPE_ACCELEROMETER*/) != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION/*TYPE_ACCELEROMETER*/);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        } else {

        }


    }

    public void initializeViews() {
        currentX = (TextView) findViewById(R.id.CurrentX);
        currentY = (TextView) findViewById(R.id.CurrentY);
        currentZ = (TextView) findViewById(R.id.CurrentZ);
        ln=(ConstraintLayout) findViewById(R.id.cnt);

    }


    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }


    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        Toast mensaje = Toast.makeText(getApplicationContext(),"Has movido el celular hacia arriba",Toast.LENGTH_SHORT);

        valores_actuales();



        deltaX = Math.abs(lastX - event.values[0]);
        deltaY = Math.abs(lastY - event.values[1]);
        deltaZ = Math.abs(lastZ - event.values[2]);

        if(deltaX >1){
            sonido();
            }

        if(deltaY >1){
            ln.setBackgroundColor(Color.BLUE);
        }else{
            ln.setBackgroundColor(Color.WHITE);
        }

        if(deltaZ >1){
            mensaje.show();
        }


        if (deltaX < 2)
            deltaX = 0;
        if (deltaY < 2)
            deltaY = 0;
        if (deltaZ < 2)
            deltaZ = 0;

    }


    public void valores_actuales() {
        currentX.setText(Float.toString(deltaX)+"m/s2");
        currentY.setText(Float.toString(deltaY)+"m/s2");
        currentZ.setText(Float.toString(deltaZ)+"m/s2");


    }
    private void sonido()
    {
        MediaPlayer mediaPlayer= MediaPlayer.create(this,R.raw.song);
        mediaPlayer.start();

    }



}