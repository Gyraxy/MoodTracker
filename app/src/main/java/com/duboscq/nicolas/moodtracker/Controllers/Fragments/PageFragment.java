package com.duboscq.nicolas.moodtracker.Controllers.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duboscq.nicolas.moodtracker.R;


public class PageFragment extends Fragment {

    // 1 - Create keys for our Bundle
    private static final String KEY_COLOR="color";
    private static final String KEY_SMILEYS="smileys";


    public PageFragment() { }


    // 2 - Method that will create a new instance of PageFragment, and add data to its bundle.
    public static PageFragment newInstance(int color, int smileys) {

        // 2.1 Create new fragment
        PageFragment frag = new PageFragment();

        // 2.2 Create bundle and add it some data
        Bundle args = new Bundle();
        args.putInt(KEY_COLOR, color);
        args.putInt(KEY_SMILEYS, smileys);
        frag.setArguments(args);

        return(frag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // 3 - Get layout of PageFragment
        View result = inflater.inflate(R.layout.fragment_page, container, false);

        // 4 - Get widgets from layout and serialise it
        LinearLayout rootView = result.findViewById(R.id.fragment_page_rootview);
        ImageView smiley_img = result.findViewById(R.id.smiley_img);

        // 5 - Get data from Bundle (created in method newInstance)
        int color = getArguments().getInt(KEY_COLOR, -1);
        int smileys = getArguments().getInt(KEY_SMILEYS,-1);

        // 6 - Update widgets with it
        rootView.setBackgroundColor(color);
        smiley_img.setImageResource(smileys);

        return result;
    }

}
