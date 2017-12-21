package com.example.app2;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;

public class MainActivity extends AppCompatActivity {

    static final String STATE_NUMBER1 = "number1", STATE_NUMBER2 = "number2";
    private int number1 = 0;
    private double number2 = 0;
    private TextView tvResult, tvInput1;
    private EditText etInput2;
    @Override
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
