package com.androidbasics;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.et_name);
    }

    public void showFragi(View view) {
        String name = editText.getText().toString();

//        Bundle bundle=new Bundle();
//        bundle.putString("key",name);

        Fragi fragi = new Fragi();
//        fragi.setArguments(bundle);
        fragi.setData(name);
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