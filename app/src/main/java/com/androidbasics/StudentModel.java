package com.androidbasics;

import android.content.ContentValues;

import java.util.UUID;

public class StudentModel {


    /*  Data Type Must Be Same As Column Data Type  */

    private String studentId;
    private String studentName;
    private int studentRoll;
    private String studentDegree;
    private String studentBranch;
    private String studentMobile;
    private String images;

    public StudentModel(){

    }

    public StudentModel(String studentId, String studentName, int studentRoll, String studentDegree, String studentBranch, String studentMobile, String images) {

        if (studentId==null){

            /*  UUID=Universal Unique Identification , Generate Global Unique Random Id*/

            this.studentId= UUID.randomUUID().toString();

        }else{
            this.studentId=studentId;
        }
        this.studentName = studentName;
        this.studentRoll = studentRoll;
        this.studentDegree = studentDegree;
        this.studentBranch = studentBranch;
        this.studentMobile = studentMobile;
        this.images = images;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentRoll() {
        return studentRoll;
    }

    public void setStudentRoll(int studentRoll) {
        this.studentRoll = studentRoll;
    }

    public String getStudentDegree() {
        return studentDegree;
    }

    public void setStudentDegree(String studentDegree) {
        this.studentDegree = studentDegree;
    }

    public String getStudentBranch() {
        return studentBranch;
    }

    public void setStudentBranch(String studentBranch) {
        this.studentBranch = studentBranch;
    }

    public String getStudentMobile() {
        return studentMobile;
    }

    public void setStudentMobile(String studentMobile) {
        this.studentMobile = studentMobile;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }


    public ContentValues getValues(){
        ContentValues values=new ContentValues(7);
        values.put(Schemas.COLUMN_ID,this.studentId);
        values.put(Schemas.COLUMN_NAME,this.studentName);
        values.put(Schemas.COLUMN_ROLL,this.studentRoll);
        values.put(Schemas.COLUMN_DEGREE,this.studentDegree);
        values.put(Schemas.COLUMN_BRANCH,this.studentBranch);
        values.put(Schemas.COLUMN_MOBILE,this.studentMobile);
        values.put(Schemas.COLUMN_IMAGE,this.images);

        return values;
    }

    /*  Print Values if We print Object's Reference Variable */

    @Override
    public String toString() {
        return "StudentModel{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", StudentRoll=" + studentRoll +
                ", studentDegree='" + studentDegree + '\'' +
                ", studentBranch='" + studentBranch + '\'' +
                ", studentMobile=" + studentMobile +
                ", images='" + images + '\'' +
                '}';
    }

}
