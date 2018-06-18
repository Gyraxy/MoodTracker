package com.duboscq.nicolas.moodtracker.controllers.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.duboscq.nicolas.moodtracker.R;
import com.duboscq.nicolas.moodtracker.controllers.activities.HistoricActivity;
import com.duboscq.nicolas.moodtracker.controllers.activities.MainActivity;


public class PageFragment extends Fragment implements View.OnClickListener{

    // 1 - Create keys for our Bundle
    private static final String KEY_COLOR = "color";
    private static final String KEY_SMILEYS = "smileys";


    public PageFragment() {
    }


    // 2 - Method that will create a new instance of PageFragment, and add data to its bundle.
    public static PageFragment newInstance(int color, int smileys) {

        // 2.1 Create new fragment
        PageFragment frag = new PageFragment();

        // 2.2 Create bundle and add it some data
        Bundle args = new Bundle();
        args.putInt(KEY_COLOR, color);
        args.putInt(KEY_SMILEYS, smileys);
        frag.setArguments(args);

        return (frag);
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // 3 - Get layout of PageFragment
        View result = inflater.inflate(R.layout.fragment_page, container, false);

        // 4 - Get widgets from layout and serialise it
        LinearLayout rootView = result.findViewById(R.id.fragment_page_rootview);
        ImageView smiley_img = result.findViewById(R.id.smiley_img);
        final ImageView comment_btn = result.findViewById(R.id.commentary_btn);
        ImageView historic_tbn = rootView.findViewById(R.id.historic_btn);

        // 5 - Get data from Bundle (created in method newInstance)
        int color = getArguments().getInt(KEY_COLOR, -1);
        int smileys = getArguments().getInt(KEY_SMILEYS, -1);

        // 6 - Update widgets with it
        rootView.setBackgroundColor(color);
        smiley_img.setImageResource(smileys);

        // 6-1 - Comment Popup when click on Comment Image View
        comment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder comment_popup = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater1 = getActivity().getLayoutInflater();
                comment_popup.setView(inflater1.inflate(R.layout.comment_popup, null));
                comment_popup.setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                comment_popup.setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                comment_popup.show();
            }
        });

        // 6-2 New Activity when clicked on Historic Button
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