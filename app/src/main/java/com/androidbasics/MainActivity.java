package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void Snacky(View view) {
       Snackbar snackbar= Snackbar.make(view,"Hola !! Snacky",5000);

       snackbar.setAction("undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Successfully Undo",Toast.LENGTH_LONG).show();
            }
        });

        snackbar.setTextColor(Color.GREEN);
        snackbar.setBackgroundTint(Color.BLACK);
        snackbar.show();
    }
}