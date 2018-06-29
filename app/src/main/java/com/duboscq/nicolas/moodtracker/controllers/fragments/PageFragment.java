package com.duboscq.nicolas.moodtracker.controllers.fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.duboscq.nicolas.moodtracker.R;
import com.duboscq.nicolas.moodtracker.controllers.activities.HistoricActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PageFragment extends Fragment implements View.OnClickListener{

    private static final String KEY_COLOR = "color";
    private static final String KEY_SMILEYS = "smileys";
    private static final String KEY_COMMENT = "COMMENT";
    private static final String KEY_POSITION = "POSITION";
    String strg_popup_comment;
    String todayDate;
    EditText edittxt_comment_popup;
    ImageView comment_btn;
    ImageView smiley_img;
    ImageView historic_btn;
    ImageView sms_btn;
    int color;
    int smileys;
    int position;
    LinearLayout rootView;
    View result;
    String phone_number;
    String sms_message;
    String comment_today_txt;
    String sms_txt;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0;

    public PageFragment() {
    }


    public static PageFragment newInstance(int color, int smileys) {

        // Create new fragment
        PageFragment frag = new PageFragment();

        // Create bundle and add it some data
        Bundle args = new Bundle();
        args.putInt(KEY_COLOR, color);
        args.putInt(KEY_SMILEYS, smileys);
        frag.setArguments(args);

        return (frag);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Get layout of PageFragment
        result = inflater.inflate(R.layout.fragment_page, container, false);

        // Get widgets from layout and serialise it
        rootView = result.findViewById(R.id.fragment_page_rootview);
        smiley_img = result.findViewById(R.id.layout_fragment_smiley_imv);
        comment_btn = result.findViewById(R.id.layout_fragment_commentary_imv);
        historic_btn = result.findViewById(R.id.layout_fragment_historic_imv);
        sms_btn = result.findViewById(R.id.layout_fragment_sms_imv);
        todayDate = getDateTime();


        // Get data from Bundle (created in method newInstance)
        color = getArguments().getInt(KEY_COLOR, -1);
        smileys = getArguments().getInt(KEY_SMILEYS, -1);

        // Update widgets with it
        rootView.setBackgroundColor(color);
        smiley_img.setImageResource(smileys);

        // Comment Popup when click on Comment Image View
        comment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder comment_popup_diag = new AlertDialog.Builder(getActivity());
                comment_popup_diag.setTitle("Commentaire");
                edittxt_comment_popup = new EditText(getActivity());
                comment_popup_diag.setView(edittxt_comment_popup);
                comment_popup_diag.setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        strg_popup_comment = edittxt_comment_popup.getText().toString();
                        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString(KEY_COMMENT+todayDate,strg_popup_comment).apply();
                    }
                });
                comment_popup_diag.setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                comment_popup_diag.show();
            }
        });

        // New Activity when clicked on Historic Button
        historic_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), HistoricActivity.class);
                startActivity(myIntent);
            }
        });

        // SMS Popup when click on SMS Image View
        sms_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            final AlertDialog.Builder sms_popup_diag = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater1 = getLayoutInflater();
            View sms_popup_view = inflater1.inflate(R.layout.sms_popup,null);
            final EditText sms_edt_txt = sms_popup_view.findViewById(R.id.layout_sms_edt);
            ImageView send_btn = sms_popup_view.findViewById(R.id.layout_send_imv);
            final EditText phone_number_edt_txt=sms_popup_view.findViewById(R.id.layout_phone_number_edt);
            comment_today_txt = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(KEY_COMMENT+todayDate, "");
            position = PreferenceManager.getDefaultSharedPreferences(getActivity()).getInt(KEY_POSITION+todayDate,5);
            sms_txt = getTxtSmiley(position)+" - "+comment_today_txt;
            sms_edt_txt.setText(sms_txt, TextView.BufferType.EDITABLE);
            send_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    phone_number = phone_number_edt_txt.getText().toString();
                    sms_message=sms_edt_txt.getText().toString();
                    sendSMSMessage();
                }
            });
            sms_popup_diag.setView(sms_popup_view);

            sms_popup_diag.show();
            }
        });

        return result;
    }

    @Override
    public void onClick(View v) {
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private String getTxtSmiley(int position){
        switch (position){
            case 0:
                return ":(";
            case 1:
                return ":/";
            case 2:
                return ":|";
            case 3:
                return ":)";
            case 4:
                return ":D";
            case 5:
                return ":|";
                default:
                    return ":|";
        }
    }

    protected void sendSMSMessage() {

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        } else {
            SendTextMsg();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phone_number, null, sms_message, null, null);
                    Toast.makeText(getActivity(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }

    private void SendTextMsg() {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone_number, null, sms_message, null, null);

        Toast.makeText(getActivity(), "SMS sent.",
                Toast.LENGTH_LONG).show();
    }
}
