package com.example.sensoresproyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    Button sensorl, sensorp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorl=findViewById(R.id.sensorl);
        sensorp=findViewById(R.id.sensorp);

        sensorp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(getApplicationContext(), Sensor_aproximacion.class);
                startActivity(intent);

            }
        } );

        sensorl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(getApplicationContext(), Acelerometro.class);
                startActivity(intent);

            }
        } );
    }


}