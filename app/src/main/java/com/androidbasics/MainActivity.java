package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toasty=findViewById(R.id.toasty);
        toasty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*             Method 1         */

                Toast.makeText(getApplicationContext(),"Hola!! Toast",Toast.LENGTH_LONG).show();

            }
        });

    }
}