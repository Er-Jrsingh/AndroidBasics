package com.androidbasics;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class OrderedBReceiver1 extends BroadcastReceiver {
    //    Called When This App In Background
    @Override
    public void onReceive(Context context, Intent intent) {

        if (isOrderedBroadcast()) {
            Toast.makeText(context, "BroadcastPracticalScenario Notification", Toast.LENGTH_SHORT).show();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "ChannelId")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentText("Notification From BroadcastPracticalScenario")
                    .setContentTitle("Hola ! Broadcast");

            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(1, builder.build());
        }
    }
}