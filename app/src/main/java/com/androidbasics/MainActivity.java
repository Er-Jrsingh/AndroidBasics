package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    String[] cars={"Lamborghini","Ferrari","Jaguar","TATA","Mercedes","Range Rover","Toyota","Lexus","BMW","Lyken","Honda","Audi"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView= findViewById(R.id.list_vw);

        ArrayAdapter<String> adt=new ArrayAdapter<String>(MainActivity.this, R.layout.ls_tv,cars);
        listView.setAdapter(adt);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),position+" Position Is Clicked :- "+cars[position],Toast.LENGTH_LONG).show();
            }
        });


    }
}