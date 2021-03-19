package com.androidbasics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


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

    /*      Code For Reading All Data from Database     */

    public List<StudentModel> getAllItems(String selection ){

        List<StudentModel> studentModels=new ArrayList<>();

        /*      Sorting Code      */

//        Cursor cursor= sqLiteDatabase.query(Schemas.TABLE_NAME,Schemas.ALL_COLUMNS,null,null,null,null,null);              /*   in place of null we write filters & Sorting */
//        Cursor cursor= sqLiteDatabase.query(Schemas.TABLE_NAME,Schemas.ALL_COLUMNS,null,null,null,null,Schemas.COLUMN_NAME);       /*   Sorting By COLUMN_NAME Alphabet Ascending order  */
//        Cursor cursor= sqLiteDatabase.query(Schemas.TABLE_NAME,Schemas.ALL_COLUMNS,null,null,null,null,Schemas.COLUMN_NAME+" DESC");       /*   Sorting By COLUMN_NAME Alphabet Descending order */

        /*      Filtering Code      */

        Cursor cursor;

        if (selection==null){
            cursor = sqLiteDatabase.query(Schemas.TABLE_NAME,Schemas.ALL_COLUMNS,null,null,null,null,Schemas.COLUMN_NAME+" DESC");
        }else {

            /*      Here Define Mapping for Multiple ?    */

            String[] select={selection};

            /*      Be Careful About Sql Injection So Must Use Placeholder(=?)    */
            /*      Query= Select * From Students where studentBranch = selection  */

            cursor = sqLiteDatabase.query(Schemas.TABLE_NAME,Schemas.ALL_COLUMNS,Schemas.COLUMN_BRANCH+" = ?",select,null,null,Schemas.COLUMN_NAME);

        }

        while (cursor.moveToNext()){
            StudentModel item= new StudentModel();
            item.setStudentId(cursor.getString(cursor.getColumnIndex(Schemas.COLUMN_ID)));
            item.setStudentName(cursor.getString(cursor.getColumnIndex(Schemas.COLUMN_NAME)));
            item.setStudentRoll(cursor.getInt(cursor.getColumnIndex(Schemas.COLUMN_ROLL)));
            item.setStudentDegree(cursor.getString(cursor.getColumnIndex(Schemas.COLUMN_DEGREE)));
            item.setStudentBranch(cursor.getString(cursor.getColumnIndex(Schemas.COLUMN_BRANCH)));
            item.setStudentMobile(cursor.getString(cursor.getColumnIndex(Schemas.COLUMN_MOBILE)));
            item.setImages(cursor.getString(cursor.getColumnIndex(Schemas.COLUMN_IMAGE)));

            studentModels.add(item);

        }
        cursor.close();
        return studentModels;
    }

    public void updateName(String name){
        ContentValues values=new ContentValues(1);
        values.put(Schemas.COLUMN_NAME,name);

        String where=Schemas.COLUMN_NAME + " LIKE ?";

        String[] whereArgs={"Jitu Thakur"};

        int update=sqLiteDatabase.update(Schemas.TABLE_NAME,values,where,whereArgs);

        Log.d(TAG,"Updated Name : "+update);
    }

    public void deleteItem(){

        String where = Schemas.COLUMN_NAME+" LIKE ?";

        String[] whereArgs={"Jitesh Thakur"};

        int del = sqLiteDatabase.delete(Schemas.TABLE_NAME,where,whereArgs);

        Log.d(TAG,"Row Deleted : "+del);
    }

    public void doTransaction(){
        List<StudentModel> dataItems=SampleData.studentModelList;

        try {

            sqLiteDatabase.beginTransaction();

            if (this.getItemCount()==0) {                /*  To Removing Unique Constraint Problem  */
                for (StudentModel item:dataItems){
                    try {
                        if (item.getStudentName().equals("Jitesh Singh")){
                            throw new Exception("Custom Exception Generated....");
                        }
                        this.insertData(item);

                    } catch (SQLiteException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Log.d(TAG,"Data Already Inserted.. ");
            }
            sqLiteDatabase.setTransactionSuccessful();      /*  If  setTransactionSuccessful() not call it means Transaction failed & Rolls Back */
            sqLiteDatabase.endTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            sqLiteDatabase.endTransaction();
            Log.d(TAG,"DoTransaction :- "+ e.getMessage());
        }
    }
}
