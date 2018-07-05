package com.duboscq.nicolas.moodtracker.controllers.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.TextView;
import android.widget.Toast;
import com.duboscq.nicolas.moodtracker.R;
import com.duboscq.nicolas.moodtracker.controllers.activities.HistoricActivity;
import com.duboscq.nicolas.moodtracker.utils.SharedPreferencesTool;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PageFragment extends Fragment implements View.OnClickListener{

    private static final String KEY_COLOR = "color",KEY_SMILEYS = "smileys",KEY_COMMENT = "COMMENT",KEY_POSITION = "POSITION";
    private String strg_popup_comment, todayDate, phone_number,sms_message, comment_today_txt, sms_txt;
    private EditText edittxt_comment_popup,sms_edt_txt,phone_number_edt_txt;
    private ImageView comment_btn,smiley_img,historic_btn,sms_btn,send_btn;
    private View result;
    private View sms_popup_view;
    private int color;
    private int smileys;
    private int position;
    private LinearLayout rootView;
    private LayoutInflater inflater1;
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
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Get layout of PageFragment
        result = inflater.inflate(R.layout.fragment_page, container, false);

        // Get widgets from layout and serialise it
        findViewbyId();
        todayDate = getDateTime();

        // Get data from Bundle (created in method newInstance)
        color = getArguments().getInt(KEY_COLOR, -1);
        smileys = getArguments().getInt(KEY_SMILEYS, -1);

        // Update widgets with it
        rootView.setBackgroundColor(color);
        smiley_img.setImageResource(smileys);

        // Comment Popup when click on Comment Image View
        comment_btn.setOnClickListener(this);

        // New Activity when clicked on Historic Button
        historic_btn.setOnClickListener(this);

        // SMS Popup when click on SMS Image View
        sms_btn.setOnClickListener(this);

        return result;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_page_historic_imv:
                Intent myIntent = new Intent(getActivity(), HistoricActivity.class);
                startActivity(myIntent);
                break;
            case R.id.fragment_page_sms_imv:
                final AlertDialog.Builder sms_popup_diag = new AlertDialog.Builder(getActivity());
                inflater1 = getLayoutInflater();
                sms_popup_view = inflater1.inflate(R.layout.sms_popup,null);
                sms_edt_txt = sms_popup_view.findViewById(R.id.sms_popup_sms_edt);
                send_btn = sms_popup_view.findViewById(R.id.sms_popup_send_imv);
                phone_number_edt_txt=sms_popup_view.findViewById(R.id.sms_popup_phone_number_edt);
                comment_today_txt = SharedPreferencesTool.getString(getActivity(),KEY_COMMENT+todayDate);
                position = SharedPreferencesTool.getInt(getActivity(),KEY_POSITION+todayDate,5);
                sms_txt = getTxtSmiley(position)+" - "+comment_today_txt;
                sms_edt_txt.setText(sms_txt, TextView.BufferType.EDITABLE);
                send_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        phone_number = phone_number_edt_txt.getText().toString();
                        sms_message = sms_edt_txt.getText().toString();
                        if (phone_number.length()== 10) {sendSMSMessage();
                        }else Toast.makeText(getActivity(), "Veuillez entrer un numéro de téléphone valide", Toast.LENGTH_SHORT).show();}
                });
                sms_popup_diag.setView(sms_popup_view);
                sms_popup_diag.show();
                break;
            case R.id.fragment_page_commentary_imv:
                final AlertDialog.Builder comment_popup_diag = new AlertDialog.Builder(getActivity());
                comment_popup_diag.setTitle("Commentaire");
                edittxt_comment_popup = new EditText(getActivity());
                comment_popup_diag.setView(edittxt_comment_popup);
                comment_popup_diag.setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        strg_popup_comment = edittxt_comment_popup.getText().toString();
                        SharedPreferencesTool.putString(getActivity(),KEY_COMMENT+todayDate,strg_popup_comment);
                        Toast.makeText(getActivity(),"Message enregistré",Toast.LENGTH_SHORT).show();
                    }
                });
                comment_popup_diag.setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                comment_popup_diag.show();
                break;
        }
    }

    private void findViewbyId(){
        rootView = result.findViewById(R.id.fragment_page_rootview);
        smiley_img = result.findViewById(R.id.fragment_page_smiley_imv);
        comment_btn = result.findViewById(R.id.fragment_page_commentary_imv);
        historic_btn = result.findViewById(R.id.fragment_page_historic_imv);
        sms_btn = result.findViewById(R.id.fragment_page_sms_imv);
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.FRANCE);
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

    private void sendSMSMessage() {

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        } else {
            sendTextMsg();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String permissions[],@NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phone_number, null, sms_message, null, null);
                    Toast.makeText(getActivity(), "SMS envoyé",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(),
                            "Echec envoi SMS, veuillez réessayer", Toast.LENGTH_LONG).show();
                    //return;
                }
            }
        }

    }

    private void sendTextMsg() {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone_number, null, sms_message, null, null);

        Toast.makeText(getActivity(), "SMS envoyé",
                Toast.LENGTH_LONG).show();
    }
}
