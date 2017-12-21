package com.example.services;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.widget.Toast;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.example.services.action.FOO";
    //private static final String ACTION_BAZ = "com.example.services.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_DELTA = "delta";
    //private static final String EXTRA_PARAM2 = "com.example.services.extra.PARAM2";

    public static final String STOP_COUNTING_KEY = "com.example.services.STOPCOUNT";

    private boolean isCounting = false;
    private boolean isStopCounting = false;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            isStopCounting = true;
        }
    };

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver(receiver, new IntentFilter(STOP_COUNTING_KEY));

    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, long delta) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_FOO);
        //intent.putExtra(EXTRA_PARAM1, param1);
        //intent.putExtra(EXTRA_PARAM2, param2);
        intent.putExtra(EXTRA_DELTA, delta);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action))
            {
                final long param1 = intent.getLongExtra(EXTRA_DELTA, 0);
                handleActionFoo(param1);

            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(long delta) {
        // TODO: Handle action Foo
        if (isCounting)
            return;

        int count = 0;
        while(!isStopCounting)
        {
            SystemClock.sleep(500);
            count++;
            publishProgress(count);
        }
        isCounting = false;
        isStopCounting = false;
        //Toast.makeText(this,"", Toast.LENGTH_SHORT).show();
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void publishProgress(long count)
    {
        Intent intent = new Intent(MainActivity.UPDATE_COUNTING_KEY);

        intent.putExtra("COUNT", "" + count);

        sendBroadcast(intent);

    }
}
