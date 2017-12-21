package com.example.volodden.myphoneapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;

public class MainActivity extends FragmentActivity implements FragmentA.OnSelectedButtonListener,
        FragmentC1.OnSelectedButtonListener, FragmentC2.OnSelectedButtonListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ) {
            Display display = getWindowManager().getDefaultDisplay();
            DisplayMetrics metricsB = new DisplayMetrics();
            display.getMetrics(metricsB);

            View fragA = findViewById(R.id.fragmentA);
            fragA.getLayoutParams().width = metricsB.widthPixels;
            View fragmentsBC = findViewById(R.id.fragments_B_and_C);
            fragmentsBC.getLayoutParams().width = metricsB.widthPixels;
        }
    }

    @Override
    public void onButtonSelected(int buttonId) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        if( buttonId == R.id.button_to_c1 ) {
            FragmentC1 fragmentC1 = new FragmentC1();
            transaction.replace(R.id.containerForC, fragmentC1, "fragmentC1");
        }
// if (buttonId == R.id.buttonBack)
//        {
//            Intent intent = new Intent(MainActivity.this, MainActivity.class);
//            startActivity(intent);
//
//        }
        else {
            FragmentC2 fragmentC2 = new FragmentC2();
            transaction.replace(R.id.containerForC, fragmentC2, "fragmentC2");
        }
        transaction.commitAllowingStateLoss();
        if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ) {
            HorizontalScrollView Horizsv = (HorizontalScrollView) findViewById(R.id.scrollView);
            Horizsv.scrollTo(Horizsv.getMaxScrollAmount()*2, 0);
        }
    }

    @Override
    public String getPhoneNumber() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentB fragmentB = (FragmentB) manager.findFragmentById(R.id.fragmentB);
        if( fragmentB == null ) {
            return null; // Возваращем пустую строку
        }
        EditText et = (EditText) fragmentB.getView().findViewById(R.id.editTextPloneNumber);
        return et.getText().toString();
    }
}
