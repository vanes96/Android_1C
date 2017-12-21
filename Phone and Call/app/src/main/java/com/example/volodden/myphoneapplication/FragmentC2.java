package com.example.volodden.myphoneapplication;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentC2 extends Fragment implements View.OnClickListener {

    private Button smsButton;
    private static final String SEND_SMS_PERMISSOON = Manifest.permission.SEND_SMS;
    private static final int SEND_SMS_REQUEST = 222;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentC2View = inflater.inflate(R.layout.fragment_c2, container, false);
        smsButton = (Button) fragmentC2View.findViewById(R.id.button_send_sms);
        smsButton.setOnClickListener(this);
        if( !PermissionChecker.isPermissionGranted(getActivity(), SEND_SMS_PERMISSOON) ) {
            Log.i("C1Create", "No Permissons");
            ActivityCompat.requestPermissions(getActivity(), new String[] {SEND_SMS_PERMISSOON}, SEND_SMS_REQUEST);
        }
        return fragmentC2View;
    }

    @Override
    public void onClick(View view) {
        FragmentC1.OnSelectedButtonListener listener = (FragmentC1.OnSelectedButtonListener) getActivity();
        String phoneNumber = listener.getPhoneNumber();
        if( phoneNumber == null ) {
            return;
        }

        TextView tw = (TextView) getView().findViewById(R.id.enter_sms_input);
        String msg = tw.getText().toString();
        if( msg.length() == 0 ) {
            return;
        }

        if( !PermissionChecker.isPermissionGranted(getActivity(), SEND_SMS_PERMISSOON) ) {
            PermissionChecker.showPermissionDialog(getActivity());
        }

        if( PermissionChecker.isPermissionGranted(getActivity(), SEND_SMS_PERMISSOON) ) {
            SmsManager smsManager =     SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, msg, null, null);
        }
    }




    public interface OnSelectedButtonListener {
        public String getPhoneNumber();
    }

}
