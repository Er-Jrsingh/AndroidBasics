package com.androidbasics;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "my_tag";
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

        mDataList=mDataSource.getAllItems(null);
        Toast.makeText(this, mDataList.toString(),Toast.LENGTH_SHORT).show();
        Log.w(TAG,mDataList.toString());

        /*      Update Values        */

        mDataSource.updateName("Durga Shankar");
        Toast.makeText(this,"Name Updated",Toast.LENGTH_LONG).show();
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

    /*     Create Menu Bar       */

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.filter,menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.show_cse: {
                getDatabaseList("CSE");
                break;
            }
                case R.id.show_civil: {
                    getDatabaseList("CIVIL");
                    break;
                }
                case R.id.show_electrical: {
                    getDatabaseList("ELECTRICAL");
                    break;
                }
            case R.id.show_mining: {
                    getDatabaseList("MINING");
                    break;
                }
            case R.id.show_mech: {
                getDatabaseList("MECHANICAL");
                break;
            }
            case R.id.show_all: {
                    getDatabaseList(null);
                    break;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    private void getDatabaseList(String filterName) {
        mDataList=mDataSource.getAllItems(filterName);
        Toast.makeText(this, mDataList.toString(),Toast.LENGTH_LONG).show();
        Log.w(TAG,mDataList.toString());
    }
}