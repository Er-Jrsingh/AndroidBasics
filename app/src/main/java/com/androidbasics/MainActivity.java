package com.androidbasics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static  final  int NOTIFY_ID=202521232;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showNotification(View view) {
        NotificationCompat.Builder builder=new  NotificationCompat.Builder(this);

        //must For Any Notification
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle("Hola !!");
        builder.setContentText("To Kaise Hai Aap Log");

        Intent intent=new Intent(this,TargetActivity.class);
        intent.putExtra("key",NOTIFY_ID);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,2589,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        Notification notification=builder.build();
        NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(NOTIFY_ID,notification);

    }
}