package com.androidbasics;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private  DataSource mDataSource;
    private List<StudentModel> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        MyDbOpenHelper myDbOpenHelper=new MyDbOpenHelper(this);
//        SQLiteDatabase db=myDbOpenHelper.getWritableDatabase();
//        Toast.makeText(getApplicationContext(),db.toString(),Toast.LENGTH_SHORT).show();

        /*      Inserting Data To Database     */

        mDataSource=new DataSource(this);
        mDataSource.open();
        Toast.makeText(getApplicationContext(),"Database Opened...",Toast.LENGTH_SHORT).show();

        mDataList=SampleData.studentModelList;

        if (mDataSource.getItemCount()==0) {                /*  To Removing Unique Constraint Problem  */
            for (StudentModel item:mDataList){
                try {

                    mDataSource.insertData(item);
                    Toast.makeText(getApplicationContext(),"Success... "+item,Toast.LENGTH_SHORT).show();

                } catch (SQLiteException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Failed...",Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(this,"Data Already Inserted...",Toast.LENGTH_SHORT).show();
        }

        /*      Read Data From Database      */

        mDataList=mDataSource.getAllItems();
        Toast.makeText(this, mDataList.toString(),Toast.LENGTH_SHORT).show();

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