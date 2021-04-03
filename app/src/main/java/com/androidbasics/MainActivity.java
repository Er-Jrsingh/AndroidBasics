package com.androidbasics;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

//      Creating Notification Channel in Android Oreo(Styles-1)
//      Notifications with Action Buttons (Styles-2)
//       Start Broadcast from Notification with Channels
//       Start Service  from Notification with Channels

public class MainActivity extends AppCompatActivity {

    public static final String MESSAGE_KEY = "message_key";
    private EditText mTitle, mMessage;
    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public void btnChannelOne(View view) {
        String title = mTitle.getText().toString();
        String message = mMessage.getText().toString();

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentPendingIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

        Intent actionActivityIntent = new Intent(this, SecondActivity.class);
        actionActivityIntent.putExtra(MESSAGE_KEY, message);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(this, 0, actionActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, App.CHANNEL_ONE_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_emoji_emotions)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(contentPendingIntent)
                .addAction(R.mipmap.ic_launcher, "Action 1", actionPendingIntent)
                .addAction(R.mipmap.ic_launcher, "Action 2", null)
                .addAction(R.mipmap.ic_launcher, "Action 3", null)
                .addAction(R.mipmap.ic_launcher, "Action 4", null)      //only three action allowed so it is not showing in device
                .setColor(Color.RED)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)       //Show When Device In Do Not Disturb Mode
                .setPriority(NotificationCompat.PRIORITY_HIGH)                  // Used By Less the 26 api Level Devices
                .setDefaults(NotificationCompat.DEFAULT_ALL);                  // Used By Less the 26 api Level Devices

        manager.notify(1, builder.build());
    }

    public void btnChannelTwo(View view) {
        String title = mTitle.getText().toString();
        String message = mMessage.getText().toString();

//        Start Broadcast from Notification with Channels
        Intent broadcastIntent = new Intent(this, NotificationBroadcastReceiver.class);
        broadcastIntent.putExtra(MESSAGE_KEY, message);
        PendingIntent broadcastPendingIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

//        Start Service from Notification with Channels
        Intent serviceIntent = new Intent(this, MyIntentService.class);
        serviceIntent.putExtra(MESSAGE_KEY, message);
        PendingIntent servicePendingIntent = PendingIntent.getService(this, 0, serviceIntent, PendingIntent.FLAG_UPDATE_CURRENT);

//        Migrate To Notification Directly, Works similar as NotificationCompat.Builder
        Notification
                notification = new NotificationCompat.Builder(this, App.CHANNEL_TWO_ID)
                .setSmallIcon(R.drawable.ic_baseline_local_fire_department_24)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW) // Used By Less the 26 api Level Devices
                .addAction(R.mipmap.ic_launcher, "Show Toast", broadcastPendingIntent)
                .addAction(R.mipmap.ic_launcher, "Start Service", servicePendingIntent)
                .setColor(Color.MAGENTA)
                .build();


        manager.notify(2, notification);
    }

    private void initViews() {
        mTitle = findViewById(R.id.edt_title);
        mMessage = findViewById(R.id.edt_message);
    }

}