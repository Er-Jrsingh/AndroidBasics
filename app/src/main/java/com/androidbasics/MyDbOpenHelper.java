package com.androidbasics;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDbOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="stu_details.db";
    public static final int DATABASE_VERSION=8;                                             /*  Always Integer  */
    private static final String TAG = "db_created";

    public MyDbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);                       /*   factory null(Mostly null) Because We don't want to store at Custom Factory  */
    }

    @Override
    public void onCreate(SQLiteDatabase db) {                                               /*  Execute Only When DataBase not Exists   */
        db.execSQL(Schemas.SQL_CREATE);
        Log.w(TAG,"onCreate : DataBase Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {              /*  Execute Only When DATABASE_VERSION Changed    */
        db.execSQL(Schemas.SQL_DELETE);
        Log.w(TAG,"onCreate : DataBase Updated");
        onCreate(db);                                                                       /*  Calling onCreate Manually because it cannot call automatically   */
    }
}
