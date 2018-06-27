package com.duboscq.nicolas.moodtracker.controllers.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.duboscq.nicolas.moodtracker.R;
import com.duboscq.nicolas.moodtracker.controllers.activities.HistoricActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PageFragment extends Fragment implements View.OnClickListener{

    private static final String KEY_COLOR = "color";
    private static final String KEY_SMILEYS = "smileys";
    String strg_popup_comment;
    EditText edittxt_comment_popup;

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
        final View result = inflater.inflate(R.layout.fragment_page, container, false);

        // Get widgets from layout and serialise it
        final LinearLayout rootView = result.findViewById(R.id.fragment_page_rootview);
        ImageView smiley_img = result.findViewById(R.id.smiley_img);
        final ImageView comment_btn = result.findViewById(R.id.commentary_btn);
        ImageView historic_tbn = rootView.findViewById(R.id.historic_btn);
        final String todayDate = getDateTime();


        // Get data from Bundle (created in method newInstance)
        int color = getArguments().getInt(KEY_COLOR, -1);
        int smileys = getArguments().getInt(KEY_SMILEYS, -1);

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
                        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("COMMENT"+todayDate,strg_popup_comment).apply();
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
        historic_tbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), HistoricActivity.class);
                startActivity(myIntent);
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
}
