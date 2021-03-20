package com.androidbasics.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.androidbasics.R;
import com.androidbasics.adapter.MyDataAdapter;
import com.androidbasics.model.CityDataItem;
import com.androidbasics.sample.SampleDataProvider;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "my_tag";
    private List<CityDataItem> mDataList;
    private RecyclerView mRecyclerView;
    private MyDataAdapter mDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataList = SampleDataProvider.cityDataItemList;
        mRecyclerView = findViewById(R.id.list_city);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);

        MyDataAdapter myDataAdapter=new MyDataAdapter(this,mDataList);
        mRecyclerView.setAdapter(myDataAdapter);


    }
}