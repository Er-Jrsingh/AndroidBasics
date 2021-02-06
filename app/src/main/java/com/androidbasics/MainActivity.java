package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    GridView gridView;

    String[] cars={"Lamborghini","Ferrari","Jaguar","TATA","Mercedes","Range Rover","Toyota","Lexus","BMW","Lyken","Honda","Audi"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView=findViewById(R.id.grid_vw);

        ArrayAdapter<String> adp= new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,cars);
        gridView.setAdapter(adp);
        }
    }