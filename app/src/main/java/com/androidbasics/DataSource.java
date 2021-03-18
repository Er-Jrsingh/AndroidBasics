package com.androidbasics;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataSource {

    private Context mContext;

    private MyDbOpenHelper myDbOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public DataSource(Context mContext) {
        this.mContext = mContext;
        this.myDbOpenHelper=new MyDbOpenHelper(mContext);
        this.sqLiteDatabase=myDbOpenHelper.getWritableDatabase();
    }

    /*  Below Method is help to Hold Reference when configuration change(when Orientation Change db reference may destroy )   */

    public  void open(){

        /*  getWritableDatabase() is redounded because we want to valid reference */

        sqLiteDatabase=myDbOpenHelper.getWritableDatabase();

    }



    public void close(){

        /*  When We called close method All Active Db Connection is Closed & no need to  Close Individually( if not close may occur  Resource leak )  */

        sqLiteDatabase.close();
    }
}
