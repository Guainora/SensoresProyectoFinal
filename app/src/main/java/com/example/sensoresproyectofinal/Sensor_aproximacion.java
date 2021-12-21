package com.example.sensoresproyectofinal;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
        import android.hardware.Sensor;
        import android.hardware.SensorEvent;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorManager;
        import android.os.Bundle;
        import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
        import android.widget.TextView;
public class Sensor_aproximacion extends AppCompatActivity implements SensorEventListener {

    TextView tv2;
    ImageView img1;
    SensorManager sm;
    Sensor sensor;
    Intent intent;
    Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_aproximacion);
        tv2=(TextView)findViewById(R.id.muestra);
        img1= findViewById(R.id.img1);
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sm.registerListener(this, sensor,SensorManager.SENSOR_DELAY_NORMAL);

        volver=findViewById(R.id.volver);

        volver.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){

                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        } );

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {



        String sensor1 = String.valueOf(sensorEvent.values[0]);

        if (Double.parseDouble(sensor1)>0){
            tv2.setText("FUERA DE RANGO");
            img1.setImageResource(R.drawable.prox_sensor);
            tv2.setTextColor(Color.RED);
        }else{
            tv2.setText("DETECTADO");
            img1.setImageResource(R.drawable.acelerometro2);
            tv2.setTextColor(Color.GREEN);
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) { }
}