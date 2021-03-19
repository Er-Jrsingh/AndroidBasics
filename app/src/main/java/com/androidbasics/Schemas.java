package com.androidbasics;

public class Schemas {
    public static final String TABLE_NAME="Students";
    public static final String COLUMN_ID ="studentId";
    public static final String COLUMN_NAME ="studentName";
    public static final String COLUMN_ROLL ="StudentRoll";
    public static final String COLUMN_DEGREE ="studentDegree";
    public static final String COLUMN_BRANCH ="studentBranch";
    public static final String COLUMN_MOBILE ="studentMobile";
    public static final String COLUMN_IMAGE ="images";

    /*  Code To Define Schema.
        Data Type Must Be Same As Model Data Type .
        Professional Approach:- Primary Key Data Type Should be String  .
    */

    /*      Return Column In Same Order     */

    public static final String[] ALL_COLUMNS=new String[]{COLUMN_ID,COLUMN_NAME,COLUMN_ROLL,COLUMN_DEGREE,COLUMN_BRANCH,COLUMN_MOBILE,COLUMN_IMAGE};


    public static final String SQL_CREATE="CREATE TABLE "+TABLE_NAME+"(" +
            COLUMN_ID+" TEXT PRIMARY KEY ,"+
            COLUMN_NAME+" TEXT ,"+
            COLUMN_ROLL+ " INTEGER ,"+
            COLUMN_DEGREE+" TEXT ,"+
            COLUMN_BRANCH+" TEXT ,"+
            COLUMN_MOBILE+" TEXT ,"+
            COLUMN_IMAGE+" TEXT);";

    public static final String SQL_DELETE="DROP TABLE IF EXISTS "+TABLE_NAME;
}
