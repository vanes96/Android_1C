package com.example.volodden.myphoneapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CFragment extends Fragment {
    private Button sendButton;
    private Button backButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        sendButton = (Button) getView().findViewById(R.id.button_call);
//        backButton.setOnClickListener(this);
//
//        backButton = (Button) getView().findViewById(R.id.buttonBack);
//        backButton.setOnClickListener(this);
////            if getView().getId() ==
        return inflater.inflate(R.layout.fragment_c, container, false);
    }

    public void onClick(View view) {

        if (view.getId() == R.id.buttonBack)
        {
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        }
//        if (view.getId() == R.id.buttonSms)
//        {
//            // TODO
//            Intent intent = new Intent(getContext(), MainActivity.class);
//            startActivity(intent);
//        }

    }

}
