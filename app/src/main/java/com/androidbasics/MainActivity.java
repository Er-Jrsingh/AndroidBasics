package com.androidbasics;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText mFileContent;
    private Button mBtnCreateFile;
    public  static final String FILE_NAME="txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String path=getFilesDir().getAbsolutePath();
        Toast.makeText(this,"Absolute Path : "+path,Toast.LENGTH_LONG).show();

        mFileContent=findViewById(R.id.edt_txt);
        mBtnCreateFile=findViewById(R.id.btn_create);

        this.mBtnCreateFile.setOnClickListener(this::createFile);
    }

    private void createFile(View view) {

        String data=mFileContent.getText().toString();

        FileOutputStream outputStream=null;
        try {
//            outputStream = openFileOutput(FILE_NAME,MODE_PRIVATE);
            outputStream = openFileOutput(FILE_NAME,MODE_APPEND);
            outputStream.write(data.getBytes());
            outputStream.flush();
            mFileContent.setText("");
            Toast.makeText(this,"Write Successfully",Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}