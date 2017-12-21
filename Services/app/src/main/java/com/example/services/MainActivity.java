package com.example.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    public static final String UPDATE_COUNTING_KEY = "com.example.services.receiver.UPDATECOUNT";
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String count = intent.getStringExtra("COUNT");

            TextView tv = (TextView)findViewById(R.id.TV);
            tv.setText(count);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(receiver, new IntentFilter(UPDATE_COUNTING_KEY));
    }

    @Override
    protected void onStop() {
        unregisterReceiver(receiver);
        super.onStop();
    }

    public void onClick(View v)
    {
        if (v.getId() == R.id.buttonStart)
            MyIntentService.startActionFoo(this, 5);
        else if (v.getId() == R.id.buttonStop)
        {
            Intent intent = new Intent(MyIntentService.STOP_COUNTING_KEY);
            sendBroadcast(intent);
        }
        else
        {
            throw new UnsupportedOperationException ("wefwefw");
        }
    }
}
