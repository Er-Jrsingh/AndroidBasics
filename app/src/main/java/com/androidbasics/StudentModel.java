package com.androidbasics;

import java.util.UUID;

public class StudentModel {


    /*  Data Type Must Be Same As Column Data Type  */

    private String studentId;
    private String studentName;
    private int StudentRoll;
    private String studentDegree;
    private String studentBranch;
    private String studentMobile;
    private String images;

    public StudentModel(){

    }

    public StudentModel(String studentId, String studentName, int studentRoll, String studentDegree, String studentBranch, long studentMobile, String images ){
        if (studentId==null){

            /*  UUID=Universal Unique Identification , Generate Global Unique Random Id*/

            studentId= UUID.randomUUID().toString();
        }
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getStudentRoll() {
        return StudentRoll;
    }

    public String getStudentDegree() {
        return studentDegree;
    }

    public String getStudentBranch() {
        return studentBranch;
    }

    public String getStudentMobile() {
        return studentMobile;
    }

    public String getImages() {
        return images;
    }

    /*  Print Values if We print Object's Reference Variable */

    @Override
    public String toString() {
        return "StudentModel{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", StudentRoll=" + StudentRoll +
                ", studentDegree='" + studentDegree + '\'' +
                ", studentBranch='" + studentBranch + '\'' +
                ", studentMobile=" + studentMobile +
                ", images='" + images + '\'' +
                '}';
    }

}
