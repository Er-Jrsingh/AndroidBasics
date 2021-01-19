package com.androidbasics;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=findViewById(R.id.tab1);
      btn2=  findViewById(R.id.tab2);

      btn1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              if (getSupportFragmentManager().getFragments() !=null &&getSupportFragmentManager().getFragments().size()>0){
                  for (int i=0; i<getSupportFragmentManager().getFragments().size();i++){
                      Fragment fragment=getSupportFragmentManager().getFragments().get((i));
                      if (fragment!=null){
                          getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                      }
                  }
              }

              FragmentManager fragmentManager=  getSupportFragmentManager();
              FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
              fragmentTransaction.add(R.id.wrapper,new Tab1());
              fragmentTransaction.addToBackStack(null);
              fragmentTransaction.commit();
          }
      });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getSupportFragmentManager().getFragments() !=null &&getSupportFragmentManager().getFragments().size()>0){
                    for (int i=0; i<getSupportFragmentManager().getFragments().size();i++){
                        Fragment fragment=getSupportFragmentManager().getFragments().get((i));
                        if (fragment!=null){
                            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                        }
                    }
                }

                FragmentManager fragmentManager=  getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.wrapper,new Tab2());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
}