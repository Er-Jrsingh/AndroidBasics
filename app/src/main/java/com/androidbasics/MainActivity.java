package com.androidbasics;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText mFname, mLname, mAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFname = findViewById(R.id.et_fname);
        mLname = findViewById(R.id.et_last_name);
        mAge = findViewById(R.id.et_age);
    }

    public void showFragi(View view) {
//        Bundle bundle=new Bundle();
//        bundle.putString("key",name);

        /*  Using Parsable  */

        String name = mFname.getText().toString();
        String last= mLname.getText().toString();
        int age=Integer.valueOf(mAge.getText().toString());

        PersonBean personBean=new PersonBean(name,last,age);
        Bundle bundle=new Bundle();
        bundle.putParcelable("key",personBean);


        Fragi fragi = new Fragi();
        fragi.setArguments(bundle);
//        fragi.setData(name);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragi_container, fragi, "frag_tag")
                .commit();
    }

    public void removeFragi(View view) {
        Fragi frag = (Fragi) getSupportFragmentManager()
                .findFragmentByTag("frag_tag");

        if (frag != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(frag)
                    .commit();
        }
    }
}