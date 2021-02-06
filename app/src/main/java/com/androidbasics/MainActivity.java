package com.androidbasics;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView lambo;
    Button up,down;
    Animation upToDown,downToUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide Status Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Hide Action Bar
        getSupportActionBar().hide();

        up=findViewById(R.id.up);
        down=findViewById(R.id.down);
        lambo=findViewById(R.id.lambo);

        upToDown= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.u2d);
        downToUp=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.d2u);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lambo.startAnimation(downToUp);
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lambo.startAnimation(upToDown);
            }
        });

    }
}