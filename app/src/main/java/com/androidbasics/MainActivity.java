package com.androidbasics;

import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

//      Creating Notification Channel in Android Oreo(Styles-1)

public class MainActivity extends AppCompatActivity {

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

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, App.CHANNEL_ONE_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_emoji_emotions)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH); // Used By Less the 26 api Level Devices
        manager.notify(1, builder.build());
    }

    public void btnChannelTwo(View view) {

        String title = mTitle.getText().toString();
        String message = mMessage.getText().toString();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, App.CHANNEL_TWO_ID)
                .setSmallIcon(R.drawable.ic_baseline_local_fire_department_24)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW); // Used By Less the 26 api Level Devices

        manager.notify(2, builder.build());
    }

    private void initViews() {
        mTitle = findViewById(R.id.edt_title);
        mMessage = findViewById(R.id.edt_message);
    }

}