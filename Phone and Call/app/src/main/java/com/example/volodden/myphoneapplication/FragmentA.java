package com.example.volodden.myphoneapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentA extends Fragment implements View.OnClickListener {

    Button buttonC1;
    Button buttonC2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentAView = inflater.inflate(R.layout.fragment_a, container, false);
        buttonC1 = (Button) fragmentAView.findViewById(R.id.button_to_c1);
        buttonC2 = (Button) fragmentAView.findViewById(R.id.button_to_c2);
        buttonC1.setOnClickListener(this);
        buttonC2.setOnClickListener(this);
        return fragmentAView;
    }

    public void onClickButtonToC2(View view) {;
    }

    @Override
    public void onClick(View view) {

        int idButtonClickable = view.getId();
        if( idButtonClickable == R.id.button_to_c1 ) {
            buttonC1.setClickable(false);
            buttonC2.setClickable(true);
        } else {
            buttonC2.setClickable(false);
            buttonC1.setClickable(true);
        }

        OnSelectedButtonListener listener = (OnSelectedButtonListener) getActivity();
        listener.onButtonSelected(idButtonClickable);
    }

    public interface OnSelectedButtonListener {
        void onButtonSelected(int buttonId);
    }
}
