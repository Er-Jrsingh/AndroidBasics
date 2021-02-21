package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] name={"Jitu Thakur","Jitesh Singh","Sudhanshu Singh","Utkarsh Tiwari","Durga Shankar","Gitesh Kumar"};
    int []imgi={R.drawable.andro,R.drawable.apple,R.drawable.github,R.drawable.amd,R.drawable.blackberry,R.drawable.overflow};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView=findViewById(R.id.lv);
        CustomAdapter customAdapter=new CustomAdapter(getApplicationContext(),name,imgi);
        listView.setAdapter(customAdapter);


    }
}