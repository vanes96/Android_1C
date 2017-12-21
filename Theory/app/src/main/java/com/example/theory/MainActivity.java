package com.example.theory;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    static final String intKey = "INT_KEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null)
        {
            int a  = savedInstanceState.getInt(intKey); // !!!!!!!!!
        }
        Log.i("", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("", "onDestroy");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putInt(intKey, 5);
    }
}
