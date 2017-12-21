package com.example.file;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String KEY_PREF_NAME = "PreferenceName1";
    private final String ID_PREF_HIGHSCORE = "com.example.file.Highscore";

    NamesReaderContract.FeedReaderDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences(ID_PREF_HIGHSCORE, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_PREF_NAME, "World");

        setHello(name);
        mDbHelper = new NamesReaderContract.FeedReaderDbHelper(this);
    }

    public void onSave(View v)
    {
        EditText text = (EditText)findViewById(R.id.editText);
        String name = text.getText().toString();
        if (name.isEmpty())
            return;

        setHello(name);

        SharedPreferences sharedPreferences = this.getSharedPreferences(ID_PREF_HIGHSCORE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_PREF_NAME, name);
        //editor.remove(KEY_PREF_NAME);
        editor.commit();

        mDbHelper.putName(name);
        String names = mDbHelper.getAllNames();
        Toast.makeText(this, names, Toast.LENGTH_SHORT).show();

    }

    private void setHello(String name)
    {
        String s = "Hello, " + name;
        TextView hello = (TextView)findViewById(R.id.textView);
        hello.setText(s);
    }
}
