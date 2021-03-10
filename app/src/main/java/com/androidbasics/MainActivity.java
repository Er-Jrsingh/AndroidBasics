package com.androidbasics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.os.Environment.getExternalStorageDirectory;
import static android.os.Environment.getExternalStoragePublicDirectory;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "Demo.txt";
    private static final int REQUEST_PERMISSION_WRITE = 1001;
    private Button mBtnCreatePrivate, mBtnReadPrivate, mBtnCreatePublic, mBtnReadPublic;
    private EditText mEditText;
    private boolean permissionGranted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        /*              Private External File           */

        File file = getExternalFilesDir(null);
        Toast.makeText(this, "Path:- " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();


        /*            Public  External File            */

        File externalFile = getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        Toast.makeText(this, "Path:- " + externalFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();

//        File externalFile1 = getExternalStorageDirectory();
//        Toast.makeText(this, "Path:- " + externalFile1.getAbsolutePath(), Toast.LENGTH_SHORT).show();

//        File externalFile2 = getExternalStoragePublicDirectory("MyDir");
//        Toast.makeText(this, "Path:- " + externalFile2.getAbsolutePath(), Toast.LENGTH_SHORT).show();


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
        this.mBtnCreatePublic.setOnClickListener(this::createPublicFile);
        this.mBtnReadPublic.setOnClickListener(this::readPublicFile);

    }

    private void createPublicFile(View view) {
        if (!permissionGranted) {
            checkPermissions();
        }

        /* At Home Of FileManager */

//        File externalFilePath= new File(getExternalStorageDirectory(),FILE_NAME);
//        writeToFile(externalFilePath);

        /* At Predefined Directory Of FileManager */

        File externalFilePath = new File(getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), FILE_NAME);
        writeToFile(externalFilePath);

        /* Create Custom Directory  */

//        File externalFilePath= new File(getExternalStoragePublicDirectory("MyDir"),FILE_NAME);
//        writeToFile(externalFilePath);
//        Toast.makeText(this,"Dir Creating Success",Toast.LENGTH_SHORT).show();

    }

    private void readPublicFile(View view) {
        if (!permissionGranted) {
            checkPermissions();
        }

        /* At Home Of FileManager */

//        File externalFile= new File(getExternalStorageDirectory(),FILE_NAME);
//        String fromFile=readFromFile(externalFile);
//        Toast.makeText(this,fromFile,Toast.LENGTH_SHORT).show();

        /* At Predefined Directory Of FileManager */

        File externalFile = new File(getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), FILE_NAME);
        String fromFile = readFromFile(externalFile);
        Toast.makeText(this, fromFile, Toast.LENGTH_SHORT).show();

        /* Read Custom Directory  */

//        File externalFile= new File(getExternalStoragePublicDirectory("MyDir"),FILE_NAME);
//        String fromFile=readFromFile(externalFile);
//        Toast.makeText(this,fromFile,Toast.LENGTH_SHORT).show();


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

    /*     Check If External Storage is available for Read & Write    */

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /*     Check If External Storage is available at Least to Read    */

    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state));
    }

    /*         Initiate Request For Permission      */
    private boolean checkPermissions() {
        if (!isExternalStorageReadable() || !isExternalStorageWritable()) {
            Toast.makeText(this, "This App Only Works On Devices With Usable External Storage", Toast.LENGTH_SHORT).show();
            return false;
        }
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_WRITE);
            return false;
        } else {
            return true;
        }
    }

    /*           Handle Permission Result       */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_WRITE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permissionGranted = true;
                Toast.makeText(this, "External Storage Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "You Must Grant Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initViews() {
        mBtnCreatePrivate = findViewById(R.id.btn_create_private);
        mBtnReadPrivate = findViewById(R.id.btn_read_private);
        mEditText = findViewById(R.id.edt_txt);
        mBtnCreatePublic = findViewById(R.id.btn_create_public);
        mBtnReadPublic = findViewById(R.id.btn_read_public);

    }

}
