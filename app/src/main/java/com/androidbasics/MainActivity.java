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

    private static final String FILE_NAME_EXTERN = "myCacheFile";
    private static final String FILE_NAME_INTERN = "myInternCache";

    private Button mBtnCreateCache, mBtnReadCache;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        /*              Internal Cache              */

//        String path = getCacheDir().getAbsolutePath();
//        Toast.makeText(this, "Internal Cache Path:- " + path, Toast.LENGTH_SHORT).show();

        /*              External Cache              */

        String path1 = getExternalCacheDir().getAbsolutePath();
        Toast.makeText(this, "External Cache Path :- " + path1, Toast.LENGTH_SHORT).show();

        this.mBtnCreateCache.setOnClickListener(this::createCache);
        this.mBtnReadCache.setOnClickListener(this::readCache);

    }

    private void createCache(View view) {

        /*              Internal Cache              */

//        File internalFile = new File(getCacheDir(), FILE_NAME_INTERN);
//        writeToFile(internalFile);

        /*              External Cache              */

        File externalFile = new File(getExternalCacheDir(), FILE_NAME_EXTERN);
        writeToFile(externalFile);

    }

    private void readCache(View view) {

        /*              Internal Cache              */


//        File internalFile = new File(getCacheDir(), FILE_NAME_INTERN);
//        Toast.makeText(this,readFromFile(internalFile),Toast.LENGTH_SHORT).show();

        /*              External Cache              */

        File externalFile = new File(getExternalCacheDir(), FILE_NAME_EXTERN);
        Toast.makeText(this, readFromFile(externalFile), Toast.LENGTH_SHORT).show();

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


    private void initView() {
        mBtnCreateCache = findViewById(R.id.btn_create_file);
        mBtnReadCache = findViewById(R.id.btn_read_file);
        mEditText = findViewById(R.id.edt_txt);
    }

}