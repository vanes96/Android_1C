package com.example.app2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Иван on 21.12.2017.
 */

public class WakefulReceiver extends WakefulBroadcastReceiver {
    // provides access to the system alarm services.
    private AlarmManager mAlarmManager;

    public void onReceive(Context context, Intent intent) {
        //// TODO: post notification
        WakefulReceiver.completeWakefulIntent(intent);
    }

    /**
     * Sets the next alarm to run. When the alarm fires,
     * the app broadcasts an Intent to this WakefulBroadcastReceiver.
     *
     * @param context the context of the app's Activity.
     */
    public void setAlarm(Context context) {
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, WakefulReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        //// TODO: use calendar.add(Calendar.SECOND,MINUTE,HOUR, int);
        //calendar.add(Calendar.SECOND, 10);

        //ALWAYS recompute the calendar after using add, set, roll
        Date date = calendar.getTime();

        mAlarmManager.setExact(AlarmManager.RTC_WAKEUP, date.getTime(), alarmIntent);

        // Enable {@code BootReceiver} to automatically restart when the
        // device is rebooted.
        //// TODO: you may need to reference the context by ApplicationActivity.class
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    /**
     * Cancels the next alarm from running. Removes any intents set by this
     * WakefulBroadcastReceiver.
     *
     * @param context the context of the app's Activity
     */
    public void cancelAlarm(Context context) {
        Log.d("WakefulAlarmReceiver", "{cancelAlarm}");

        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, WakefulReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        mAlarmManager.cancel(alarmIntent);

        // Disable {@code BootReceiver} so that it doesn't automatically restart when the device is rebooted.
        //// TODO: you may need to reference the context by ApplicationActivity.class
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
};