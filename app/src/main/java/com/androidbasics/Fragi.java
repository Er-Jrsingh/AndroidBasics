package com.androidbasics;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragi extends Fragment {
//    private String name;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragi_1, container, false);
        TextView textView=view.findViewById(R.id.fragi_tv);

//        Bundle bundle=getArguments();
//        if (bundle!= null){
//            String name= bundle.getString("key","name missing");
//            TextView textView=view.findViewById(R.id.fragi_tv);
//            textView.setText("Hola ! ! "+ name+" I am Fragment");
//        }

//        TextView textView=view.findViewById(R.id.fragi_tv);
//        textView.setText("Hola ! ! "+ name+" I am Fragment");

        Bundle bundle=getArguments();
        if (bundle!= null){
            PersonBean personBean= bundle.getParcelable("key");
            textView.setText(personBean.getF_name()+" ");
            textView.append(personBean.getL_name()+"\n");
            textView.append(String.valueOf(personBean.getAge()));
        }
        return view;
    }

//    public  void  setData(String name){
//        this.name=name;
//    }
}