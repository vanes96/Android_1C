package com.example.volodden.myphoneapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BFragment extends Fragment {

    private Button enterButton;
    private Button backButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //smsButton.setOnClickListener(this);
        //backButton = (Button) getView().findViewById(R.id.buttonBack);
        //smsButton.setOnClickListener(this);
//        Button button = (Button) getView().findViewById(R.id.buttonBack);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivity(new Intent(getContext(), MainActivity.class));
//            }
//        });

       return inflater.inflate(R.layout.fragment_b, container, false);
    }
//    public void onClick(View view)
//    {
//        BFragment.OnSelectedButtonListener listener = (BFragment.OnSelectedButtonListener) getActivity();
//        if (view.getId() == R.id.buttonBack)
//        {
//            Intent intent = new Intent(getContext(), MainActivity.class);
//            startActivity(intent);
//        }
//        if (view.getId() == R.id.button_to_c2)
//        {
//            Intent intent = new Intent(getContext(), C2Fragment.class);
//            startActivity(intent);
//        }
//    }
    public interface OnSelectedButtonListener {
        public String getPhoneNumber();
    }
}



