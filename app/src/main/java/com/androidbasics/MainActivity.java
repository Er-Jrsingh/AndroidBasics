package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button rect,round;
    Animation anim1,anim2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rect=findViewById(R.id.rect);
        round=findViewById(R.id.round);
        anim1 =  AnimationUtils.loadAnimation(getApplicationContext(),R.anim.up);
        anim2 =  AnimationUtils.loadAnimation(getApplicationContext(),R.anim.down);

        rect.setOnClickListener(v -> rect.startAnimation(anim1));

        round.setOnClickListener(v -> round.startAnimation(anim2));
    }
}