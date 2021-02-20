package com.androidbasics;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Size;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DynamicUI extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        Button button = new Button(this);
        TextView textView = new TextView(this);

        textView.setText("Dynamic TextView");
        button.setText("Dynamic Button");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DynamicUI.this, "Button Clicked", Toast.LENGTH_LONG).show();
            }
        });
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER)
        ;
        textView.setTextColor(Color.MAGENTA);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setPadding(50, 50, 50, 100);
        textView.setTextSize(50);

        button.setBackgroundColor(Color.GREEN);
        button.setWidth(20);
        button.setHeight(10);
        button.setTextColor(Color.RED);

        linearLayout.addView(textView);
        linearLayout.addView(button);
        setContentView(linearLayout);

    }
}