package com.androidbasics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private EditText mFileContent;
    private Button mBtnCreateFile, mBtnImg, mBtnReadFile, mBtnReadImg, mBtnShowFile, mBtnDeleteText,mBtnCreateDir,mBtnShowDir;
    public static final String FILE_NAME = "txt";
    public static final String IMG_NAME = "assetjitu";
    private ImageView mImageView;
    private static final String DIR_NAME ="my_custom_dir";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String path = getFilesDir().getAbsolutePath();
        Toast.makeText(this, "Absolute Path : " + path, Toast.LENGTH_LONG).show();

        mFileContent = findViewById(R.id.edt_txt);
        mBtnCreateFile = findViewById(R.id.btn_create);
        mBtnImg = findViewById(R.id.btn_get_img);
        mBtnReadFile = findViewById(R.id.btn_read_file);
        mBtnReadImg = findViewById(R.id.btn_read_img);
        mImageView = findViewById(R.id.imgView);
        mBtnShowFile = findViewById(R.id.btn_list_file);
        mBtnDeleteText = findViewById(R.id.btn_del_list);
        mBtnCreateDir = findViewById(R.id.btn_create_dir);
        mBtnShowDir = findViewById(R.id.btn_show_dir_file);

        this.mBtnCreateFile.setOnClickListener(this::createFile);
        this.mBtnImg.setOnClickListener(this::writeImg);
        this.mBtnReadFile.setOnClickListener(this::readFile);
        this.mBtnReadImg.setOnClickListener(this::readImg);
        this.mBtnShowFile.setOnClickListener(this::showFiles);
        this.mBtnDeleteText.setOnClickListener(this::deleteText);
        this.mBtnCreateDir.setOnClickListener(this::createDir);
        this.mBtnShowDir.setOnClickListener(this::showDirFiles);

    }

    private void createDir(View view) {

        File path=getDir(DIR_NAME,MODE_PRIVATE);
        File file=new File(path,"hola.txt");
        String message="Hola!! World";

        try {
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            fileOutputStream.write(message.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(this,"Directory Created Successfully",Toast.LENGTH_LONG).show();
    }

    private void showDirFiles(View view) {
        File path=getDir(DIR_NAME,MODE_PRIVATE);
        File file=new File(path,"hola.txt");

        if (file.exists()){
            Toast.makeText(this,"File Available",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"Fatal : File Not Available",Toast.LENGTH_LONG).show();
        }

    }

    private void showFiles(View view) {
        String[] fileList = fileList();
        for (String fileName : fileList) {
            Toast.makeText(this, fileName, Toast.LENGTH_LONG).show();
        }

    }

    private void deleteText(View view) {
        boolean deleteFile = deleteFile(FILE_NAME);
        Toast.makeText(this, "File Deleted " + deleteFile, Toast.LENGTH_LONG).show();
    }

    private void readImg(View view) {

        Bitmap bitmap = null;
        InputStream inputStream = null;

        try {
            inputStream = openFileInput(IMG_NAME + ".jpg");
            bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
        mImageView.setImageBitmap(bitmap);
    }

    private void readFile(View view) {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = null;

        try {
            inputStream = openFileInput(FILE_NAME);

            int read;
            while ((read = inputStream.read()) != -1) {
                stringBuilder.append((char) read);
            }

            Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void writeImg(View view) {

        Bitmap data = getImage();

//        BitmapDrawable bitmapDrawable= (BitmapDrawable)getDrawable(R.drawable.assetjitu);

        FileOutputStream outputStream = null;
        try {
//            outputStream = openFileOutput(IMG_NAME+".jpg",MODE_PRIVATE);
            outputStream = openFileOutput(IMG_NAME + ".jpg", MODE_APPEND);
            data.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
            Toast.makeText(this, "Write Image Successfully", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    private Bitmap getImage() {

        Bitmap img = null;

        try {
            InputStream inputStream = getAssets().open("assetjitu.jpg");
            img = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;

    }

    private void createFile(View view) {

        String data = mFileContent.getText().toString();

        FileOutputStream outputStream = null;
        try {
//            outputStream = openFileOutput(FILE_NAME,MODE_PRIVATE);
            outputStream = openFileOutput(FILE_NAME, MODE_APPEND);
            outputStream.write(data.getBytes());
            outputStream.flush();
            mFileContent.setText("");
            Toast.makeText(this, "Write Successfully", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}