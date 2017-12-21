package com.example.app2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

/**
 * Created by Иван on 21.12.2017.
 */
public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        createNotification(context, "Hi", "titl", "Alarm");


    }

    public void createNotification(Context context, String msg, String msgText, String msgAlert) {

        PendingIntent notificIntent = PendingIntent.getActivities(context, 0,
                new Intent[]{new Intent(context, MainActivity.class)}, 0);

//        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle(msg)
//                .setTicker(msgAlert)
//                .setContentText(msgText);

        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setContentTitle("wef")
                .setContentText("refww")
                .setContentIntent(notificIntent)
                .setAutoCancel(true)
                .build();

//        notification.setContentIntent(notificIntent);
//        notification.setDefaults(NotificationCompat.DEFAULT_SOUND);
//        notification.setAutoCancel(true);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1, notification);


    }
}