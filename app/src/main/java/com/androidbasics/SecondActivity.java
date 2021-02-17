package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String from_home= getIntent().getStringExtra("from home").toString();
        TextView textView = findViewById(R.id.dataHome);
        textView.setText(from_home);

        editText=findViewById(R.id.edtSec);
        Button btn = findViewById(R.id.btnHome);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,MainActivity.class);
                String txt=editText.getText().toString();
                intent.putExtra("from sec",txt);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}