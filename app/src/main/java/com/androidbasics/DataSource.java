package com.androidbasics;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/*      This Class Interact With Physical Database    */

public class DataSource {

    private static final String TAG ="my_tag";
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

    /* This Method Help To Insert Data */

    public void insertData(StudentModel studentModel){
        ContentValues values=studentModel.getValues();

        /*  nullColumnHack: Help To Insert Blank Row(give any name in place of null)  */
        long inserted = sqLiteDatabase.insert(Schemas.TABLE_NAME,null,values);

        Log.w(TAG,"InsertedItem "+inserted);
    }

    public long getItemCount(){
        return DatabaseUtils.queryNumEntries(sqLiteDatabase,Schemas.TABLE_NAME);
    }
}
