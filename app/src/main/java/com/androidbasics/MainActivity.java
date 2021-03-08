package com.androidbasics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private EditText mFileContent;
    private Button mBtnCreateFile,mBtnImg,mBtnReadFile,mBtnReadImg;
    public  static final String FILE_NAME="txt";
    public  static final String IMG_NAME="assetjitu";
    private ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String path=getFilesDir().getAbsolutePath();
        Toast.makeText(this,"Absolute Path : "+path,Toast.LENGTH_LONG).show();

        mFileContent=findViewById(R.id.edt_txt);
        mBtnCreateFile=findViewById(R.id.btn_create);
        mBtnImg=findViewById(R.id.btn_get_img);
        mBtnReadFile=findViewById(R.id.btn_read_file);
        mBtnReadImg=findViewById(R.id.btn_read_img);
        imageView=findViewById(R.id.imgView);

        this.mBtnCreateFile.setOnClickListener(this::createFile);
        this.mBtnImg.setOnClickListener(this::writeImg);
        this.mBtnReadFile.setOnClickListener(this::readFile);
        this.mBtnReadImg.setOnClickListener(this::readImg);
    }

    private void readImg(View view) {

        Bitmap bitmap =null;
        InputStream inputStream=null;

        try {
            inputStream=openFileInput(IMG_NAME+".jpg");
            bitmap=BitmapFactory.decodeStream(inputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(bitmap);
    }

    private void readFile(View view) {
        StringBuilder stringBuilder=new StringBuilder();
        InputStream inputStream=null;

        try {
            inputStream=openFileInput(FILE_NAME);

            int read;
            while ((read=inputStream.read())!=-1){
                stringBuilder.append((char) read);
            }

            Toast.makeText(this,stringBuilder.toString(),Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void writeImg(View view) {

        Bitmap data=getImage();

//        BitmapDrawable bitmapDrawable= (BitmapDrawable)getDrawable(R.drawable.assetjitu);

        FileOutputStream outputStream=null;
        try {
//            outputStream = openFileOutput(IMG_NAME+".jpg",MODE_PRIVATE);
            outputStream = openFileOutput(IMG_NAME+".jpg",MODE_APPEND);
            data.compress(Bitmap.CompressFormat.JPEG,50,outputStream);
            Toast.makeText(this,"Write Image Successfully",Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }



    private Bitmap getImage() {

        Bitmap img=null;

        try {
            InputStream inputStream=getAssets().open("assetjitu.jpg");
            img= BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;

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