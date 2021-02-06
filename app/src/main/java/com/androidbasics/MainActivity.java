package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    String[] cars={"Lamborghini","Ferrari","Jaguar","TATA","Mercedes","Range Rover","Toyota","Lexus","BMW","Lyken","Honda","Audi"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView= findViewById(R.id.list_vw);

        ArrayAdapter<String> adt=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1,cars);
        listView.setAdapter(adt);


    }
}