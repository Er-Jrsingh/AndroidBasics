package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "Demo.txt";
    private Button mBtnCreatePrivate, mBtnReadPrivate;
    private EditText mEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        File file = getExternalFilesDir(null);
        Toast.makeText(this, "Path:- " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();


/*           Create Custom Directory

        File file1=getExternalFilesDir(null);
        Toast.makeText(this,"Path:- "+file1.getAbsolutePath(),Toast.LENGTH_SHORT).show();

*/

/*              Show Files Lists

        String[] ListOFiles=file.list();
*/

/*          Get Reference of Files & Delete

        File[] filesRef =file.listFiles();
        filesRef[1].delete();
*/


        this.mBtnCreatePrivate.setOnClickListener(this::createPrivateFile);
        this.mBtnReadPrivate.setOnClickListener(this::readPrivateFile);
    }

    private void createPrivateFile(View view) {

        File file = new File(getExternalFilesDir(null), FILE_NAME);
        writeToFile(file);


    }

    private void readPrivateFile(View view) {

        File file = new File(getExternalFilesDir(null), FILE_NAME);
        String fromFile = readFromFile(file);


        Toast.makeText(this, "Your Text :- " + fromFile, Toast.LENGTH_SHORT).show();

    }

    private void writeToFile(File file) {

        FileOutputStream fileOutputStream = null;
        String data = mEditText.getText().toString();

        try {

            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data.getBytes());
            Toast.makeText(this, "Data Written To File : " + file.getName() + "Path" + file.getPath(), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readFromFile(File file) {
        FileInputStream fileInputStream = null;
        int read;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            fileInputStream = new FileInputStream(file);
            while ((read = fileInputStream.read()) != -1) {
                stringBuilder.append((char) read);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }

    private void initViews() {
        mBtnCreatePrivate = findViewById(R.id.btn_create_private);
        mBtnReadPrivate = findViewById(R.id.btn_read_private);
        mEditText = findViewById(R.id.edt_txt);
    }
}