package com.example.volodden.myphoneapplication;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class C2Fragment extends Fragment implements View.OnClickListener {

    private Button smsButton;
    private Button backButton;

    private static final String SEND_SMS_PERMISSION = Manifest.permission.SEND_SMS;
    private static final int SEND_SMS_REQUEST = 222;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentC2View = inflater.inflate(R.layout.fragment_c2, container, false);

        smsButton = (Button) fragmentC2View.findViewById(R.id.button_send_sms);
        smsButton.setOnClickListener(this);

        backButton = (Button) fragmentC2View.findViewById(R.id.buttonBack);
        backButton.setOnClickListener(this);

        if( !PermissionChecker.isPermissionGranted(getActivity(), SEND_SMS_PERMISSION) ) {
            ActivityCompat.requestPermissions(getActivity(), new String[] {SEND_SMS_PERMISSION}, SEND_SMS_REQUEST);
        }

        return fragmentC2View;
    }

    @Override
    public void onClick(View view) {
        C1Fragment.OnSelectedButtonListener listener = (C1Fragment.OnSelectedButtonListener) getActivity();

        if (view.getId() == R.id.button_send_sms)
        {
            String phoneNumber = listener.getPhoneNumber();
            if( phoneNumber == null ) {
                return;
            }

            TextView tw = (TextView) getView().findViewById(R.id.enter_sms_input);
            String msg = tw.getText().toString();
            if( msg.length() == 0 ) {
                return;
            }

            if( !PermissionChecker.isPermissionGranted(getActivity(), SEND_SMS_PERMISSION) ) {
                PermissionChecker.showPermissionDialog(getActivity());
            }

            if( PermissionChecker.isPermissionGranted(getActivity(), SEND_SMS_PERMISSION) ) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
                intent.putExtra("sms_body", msg);
                startActivity(intent);

                SmsManager smsManager =     SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumber, null, msg, null, null);
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
