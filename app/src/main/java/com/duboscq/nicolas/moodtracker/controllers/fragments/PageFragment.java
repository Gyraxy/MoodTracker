package com.duboscq.nicolas.moodtracker.controllers.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.duboscq.nicolas.moodtracker.R;
import com.duboscq.nicolas.moodtracker.controllers.activities.HistoricActivity;
import com.duboscq.nicolas.moodtracker.controllers.activities.MainActivity;
import com.duboscq.nicolas.moodtracker.views.VerticalViewPager;


public class PageFragment extends Fragment implements View.OnClickListener{

    private static final String KEY_COLOR = "color";
    private static final String KEY_SMILEYS = "smileys";

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
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View popup_view = inflater.inflate(R.layout.comment_popup, null);
                comment_popup_diag.setView(inflater.inflate(R.layout.comment_popup, null));
                EditText edit_txt_comment = popup_view.findViewById(R.id.comments_txt);
                final String strg_comment = edit_txt_comment.getText().toString();
                comment_popup_diag.setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),"HALLO" ,Toast.LENGTH_LONG).show();
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
}
