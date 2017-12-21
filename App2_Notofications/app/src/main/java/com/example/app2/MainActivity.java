package com.example.app2;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    static final String STATE_NUMBER1 = "number1", STATE_NUMBER2 = "number2";
    private int number1 = 0;
    private double number2 = 0;
    private TextView tvResult, tvInput1;
    private EditText etInput2;

    private PendingIntent pendingIntent;

//    public void showNotification() {
//        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
//        Resources r = getResources();
//        Notification notification = new NotificationCompat.Builder(this)
//                .setSmallIcon(android.R.drawable.ic_menu_report_image)
//                .setContentTitle("wef")
//                .setContentText("refww")
//                .setContentIntent(pi)
//                .setAutoCancel(true)
//                .build();
//
//        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        notificationManager.notify(0, notification);
//    }
//    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //button = (Button)findViewById(R.id.button2);
//        button.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(getApplicationContext(), "PARAPRAPRA", Toast.LENGTH_SHORT).show();
//            }
//        });
        tvInput1 = (TextView)findViewById(R.id.ViewInput);
        etInput2 = (EditText)findViewById(R.id.EditInput);
        tvResult = (TextView)findViewById(R.id.ViewResult);
        tvInput1.setText(String.valueOf(number1));
        etInput2.setText(String.valueOf(number2));
        tvResult.setText(Double.toString(number1 * number2));
//        tvInput1.setText(String.valueOf(number1));
//        etInput2.setText(String.valueOf(number2));
//        tvResult.setText(Double.toString(number1 * number2));
        ((EditText)findViewById(R.id.EditInput)).addTextChangedListener(new TextWatcher()
        {

            public void afterTextChanged(Editable s)
            {
                try
                {
                    number2 = Double.valueOf(s.toString());
                    //savedInstanceState.putDouble(STATE_NUMBER2, number2);
                    tvResult.setText(Double.toString(number1 * number2));
                }
                catch (Exception e)
                {
                    etInput2.setText(String.valueOf(number2));
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {}

            public void onTextChanged(CharSequence s, int start, int before, int count)
            {}
        });

        //scheduleNotification(getApplicationContext(), 3000, 1);
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//        if (!prefs.getBoolean("firstTime", false)) {
//
//            Intent alarmIntent = new Intent(this, AlarmReceiver.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
//
//            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTimeInMillis(System.currentTimeMillis());
//            calendar.set(Calendar.HOUR_OF_DAY, 17);
//            calendar.set(Calendar.MINUTE, 49);
//            calendar.set(Calendar.SECOND, 1);
//
//            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                    AlarmManager.INTERVAL_DAY, pendingIntent);
//
//            SharedPreferences.Editor editor = prefs.edit();
//            editor.putBoolean("firstTime", true);
//            editor.apply();
//        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 1);

        Intent intent = new Intent(getApplicationContext(), AlertReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent , 0);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //showNotification();

        //alarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);



        //alarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),10, pendingIntent);

    }

    public void onClick(View view)
    {
        //Toast.makeText(getApplicationContext(), "PARAPRAPRA", Toast.LENGTH_SHORT).show();
        if (view.getId() == R.id.button1)
            tvInput1.setText(String.valueOf(--number1));
        else
            tvInput1.setText(String.valueOf(++number1));
        tvResult.setText(String.valueOf(number1 * number2));
    }

    public void scheduleNotification(Context context, long delay, int notificationId) {//delay is after how much time(in millis) from current time you want to schedule the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentText("wefew");
        builder.setContentTitle("wedwed");

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent activity = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(activity);

        Notification notification = builder.build();

        Intent notificationIntent = new Intent(context, MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, notificationId);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_NUMBER1, number1);
        outState.putDouble(STATE_NUMBER2, number2);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        number1 = savedInstanceState.getInt(STATE_NUMBER1, number1);
        number2 = savedInstanceState.getDouble(STATE_NUMBER2, number2);
        tvInput1.setText(String.valueOf(number1));
        etInput2.setText(String.valueOf(number2));
        tvResult.setText(Double.toString(number1 * number2));
    }



    //    public void onClickCalculate(View view)
//    {
//        tvResult.setText(String.valueOf(number1 * number2));
//    }








}
