package com.example.volodden.myphoneapplication;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class C1Fragment extends Fragment implements View.OnClickListener {

    private Button callButton;
    private Button backButton;

    private static final String CALL_PHONE_PERMISSOON = Manifest.permission.CALL_PHONE;
    private static final int CALL_PHONE_REQUEST = 111;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentC1View = inflater.inflate(R.layout.fragment_c1, container, false);

        callButton = (Button) fragmentC1View.findViewById(R.id.button_call);
        callButton.setOnClickListener(this);

        backButton = (Button) fragmentC1View.findViewById(R.id.buttonBack);
        backButton.setOnClickListener(this);

        if( !PermissionChecker.isPermissionGranted(getActivity(), CALL_PHONE_PERMISSOON) ) {
            ActivityCompat.requestPermissions(getActivity(), new String[] {CALL_PHONE_PERMISSOON}, CALL_PHONE_REQUEST);
        }
        return fragmentC1View;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.button_call)
        {
            OnSelectedButtonListener listener = (OnSelectedButtonListener) getActivity();
            String phoneNumber = listener.getPhoneNumber();
            if( phoneNumber == null ) {
                return;
            }

            if( !PermissionChecker.isPermissionGranted(getActivity(), CALL_PHONE_PERMISSOON) ) {
                PermissionChecker.showPermissionDialog(getActivity());
            }

            if( PermissionChecker.isPermissionGranted(getActivity(), CALL_PHONE_PERMISSOON) ) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        }
        if (view.getId() == R.id.buttonBack)
        {
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        }

    }

    public interface OnSelectedButtonListener {
        public String getPhoneNumber();
    }
}
