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
            addItem(new StudentModel(null,"Jitesh Singh Thakur",495917,"Btech","CSE","62911746","jst.jpg"));
        }

        private static void addItem(StudentModel studentModel) {
            studentModelList.add(studentModel);
            studentModelMap.put(studentModel.getStudentId(), studentModel);
        }

    }

