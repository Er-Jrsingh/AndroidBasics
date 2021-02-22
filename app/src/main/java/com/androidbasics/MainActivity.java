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

    private static  final  int NOTIFY_ID=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showNotification(View view) {
        //create notification builder
        NotificationCompat.Builder builder=new  NotificationCompat.Builder(this);

        //must For Any Notification
        builder.setSmallIcon(R.drawable.construction_lady);
        builder.setContentTitle("Hola ! !");
        builder.setContentText("To Kaise Hai Aap Log");

        //create pending intent to launch target activity(no need for artificial backspace)
        Intent intent=new Intent(this,TargetActivity.class);
        intent.putExtra("key",NOTIFY_ID);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,2589,intent,PendingIntent.FLAG_CANCEL_CURRENT);

        //set the pending intent in builder
        builder.setContentIntent(pendingIntent);

        //build notification from the builder
        Notification notification=builder.build();

        //Setup Notification Action(Method 1 Deprecated API 23)
//        builder.addAction(R.mipmap.icon_one,"reply",pendingIntent);
//        builder.addAction(R.mipmap.icon_two,"Mark as Read",pendingIntent);

        //Setup Notification Action(Method 2)

        NotificationCompat.Action.Builder actionBuilder=new NotificationCompat.Action.Builder(R.mipmap.icon_one,"reply",pendingIntent);
//        android.support.v4.app.NotificationCompat.Action action=actionBuilder.build();
        androidx.core.app.NotificationCompat.Action action=actionBuilder.build();
        builder.addAction(action);

        // Notification BigText Style
        NotificationCompat.BigTextStyle bigTextStyle=new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("BigTexty");
        bigTextStyle.bigText("Hello Ji Kaise Ho Ji  Saare Ke Saare ");

        //pass BigTextStyle To Notification Builder
        builder.setStyle(bigTextStyle);

        //pass notification to the notification manager with its id to show the notification
        NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(NOTIFY_ID,notification);
    }
}