package com.androidbasics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button captImage;
    private boolean mPermission;
    private int REQ_CODE=101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        captImage =findViewById(R.id.captureImg);
        captImage.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { captImage(); }});

    }
    private void captImage() {
        if (!mPermission){
            requestPermissions();
            return;
        }
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

    private void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.CAMERA},REQ_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
            mPermission=true;
            Toast.makeText(MainActivity.this,"Hola! Camera Permission Granted",Toast.LENGTH_SHORT).show();
        }
    }
}