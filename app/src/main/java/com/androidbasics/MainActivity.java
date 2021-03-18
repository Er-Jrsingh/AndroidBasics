package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private  DataSource mDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        MyDbOpenHelper myDbOpenHelper=new MyDbOpenHelper(this);
//        SQLiteDatabase db=myDbOpenHelper.getWritableDatabase();
//        Toast.makeText(getApplicationContext(),db.toString(),Toast.LENGTH_SHORT).show();

        mDataSource=new DataSource(this);
        mDataSource.open();
        Toast.makeText(getApplicationContext(),"Database Opened...",Toast.LENGTH_SHORT).show();


    }

    /* when orientation Change db connection may interrupt so below method help us */

    @Override
    protected void onResume() {
        super.onResume();
        mDataSource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDataSource.close();
    }
}