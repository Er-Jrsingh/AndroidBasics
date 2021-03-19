package com.androidbasics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleData {

        public static List<StudentModel> studentModelList;
        public static Map<String, StudentModel> studentModelMap;

        static {
            studentModelList = new ArrayList<>();
            studentModelMap = new HashMap<>();

            addItem(new StudentModel(null,"Jitu Thakur",195917,"Btech","CSE","76911746","jitu.jpg"));
            addItem(new StudentModel(null,"Jitesh Thakur",295917,"Btech","CSE","896911746","jitesh.jpg"));
            addItem(new StudentModel(null,"Jitesh Singh",395917,"Btech","CSE","96911746","js.jpg"));
            addItem(new StudentModel(null,"Jitesh Singh Thakur",295917,"Btech","CSE","262911746","jst.jpg"));
            addItem(new StudentModel(null,"Sudhanshu Singh",165917,"Btech","CSE","6294511746","ss.jpg"));
            addItem(new StudentModel(null,"Ujjawal Kashyap",225917,"Btech","CSE","9294511746","uj.jpg"));
            addItem(new StudentModel(null,"Geetesh Kumar",625917,"Btech","CSE","444511746","gk.jpg"));
            addItem(new StudentModel(null,"Utkarsh Tiwari",895917,"Btech","CIVIL","5562911746","uttu.jpg"));
            addItem(new StudentModel(null,"Kunal Panday",665917,"Btech","CIVIL","6662911746","kunal.jpg"));
            addItem(new StudentModel(null,"Md Farhan",1165917,"Btech","CIVIL","1162911746","md.jpg"));
            addItem(new StudentModel(null,"Aman Xalxo",595917,"Btech","ELECTRICAL","662911556","aman.jpg"));
            addItem(new StudentModel(null,"Kishan Kanwar",7795917,"Btech","ELECTRICAL","682911556","kishu.jpg"));
            addItem(new StudentModel(null,"Set Kumar",795917,"Btech","MECHANICAL","562911556","set.jpg"));
            addItem(new StudentModel(null,"Sushank Barik",5955617,"Btech","MECHANICAL","92911556","sus.jpg"));
            addItem(new StudentModel(null,"Anuj Dwivedi",855617,"Btech","MINING","5892911556","anuj.jpg"));
            addItem(new StudentModel(null,"Anupam Tripathi",7555617,"Btech","MINING","65561556","anu.jpg"));
            addItem(new StudentModel(null,"Vishal Rajput",64955617,"Btech","MINING","772911556","vish.jpg"));
            addItem(new StudentModel(null,"Himanshu Kanwar",24955617,"Btech","MINING","352911556","jadu.jpg"));
        }

        private static void addItem(StudentModel studentModel) {
            studentModelList.add(studentModel);
            studentModelMap.put(studentModel.getStudentId(), studentModel);
        }

    }

