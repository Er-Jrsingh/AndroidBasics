package com.androidbasics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button add=findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater =getLayoutInflater();       //gives current context(main activity) associated Inflater
                LinearLayout linearLayout_main=findViewById(R.id.linearLayout_main);
                View view =inflater.inflate(R.layout.new_layout,linearLayout_main); //Here We add  linearLayout_main as parent

//                TextView textView=view.findViewById(R.id.newLayoutTv);  //Extract Text View From Newly Created View(new_layout)
//                linearLayout.addView(textView);



            }
        });
    }
}