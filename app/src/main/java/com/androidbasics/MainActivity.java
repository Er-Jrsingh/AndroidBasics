package com.androidbasics;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int REQ_CODE = 1000;
    Button btn;
    EditText editText;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.nxty);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                editText = findViewById(R.id.edtHome);
                String txt = editText.getText().toString();
                intent.putExtra("from home", txt);
                startActivityForResult(intent, REQ_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE && resultCode == RESULT_OK) {
            String sec_data = data.getStringExtra("from sec").toString();
            textView = findViewById(R.id.rtnSec);
            textView.setText(sec_data);
        }
    }
}