package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private  String[]  name= {"Drupal","Flutter","StarBucks","Kotlin","Ruby"};
    private  int[] images={R.drawable.l2,R.drawable.l3,R.drawable.l4,R.drawable.l5,R.drawable.l1};
    private  List<Images> imagesList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rvw);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        prepareTheList();

        RecyAdapter adapter=new RecyAdapter(imagesList);
        recyclerView.setAdapter(adapter);
    }

    private void prepareTheList() {
        int count=0;
        for(String name:name){
            Images persons=new Images(name,images[count]);
            imagesList.add(persons);
            count++;
        }
    }
}