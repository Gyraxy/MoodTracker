package com.duboscq.nicolas.moodtracker.controllers.activities;

import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.duboscq.nicolas.moodtracker.R;
import com.duboscq.nicolas.moodtracker.models.SharedPreferencesTool;

public class HistoricActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);

        final String comment_txt = PreferenceManager.getDefaultSharedPreferences(HistoricActivity.this).getString("COMMENT", "");
        final int position = PreferenceManager.getDefaultSharedPreferences(HistoricActivity.this).getInt("POSITION",0);

        ImageView comment_1d_imv = findViewById(R.id.comment_1d_img);
        ImageView comment_2d_imv = findViewById(R.id.comment_2d_img);
        ImageView comment_3d_imv = findViewById(R.id.comment_3d_img);
        ImageView comment_4d_imv = findViewById(R.id.comment_4d_img);
        ImageView comment_5d_imv = findViewById(R.id.comment_5d_img);
        ImageView comment_6d_imv = findViewById(R.id.comment_6d_img);
        ImageView comment_7d_imv = findViewById(R.id.comment_7d_img);

        RelativeLayout historic_1d = findViewById(R.id.historic_layout_1d);
        RelativeLayout historic_2d = findViewById(R.id.historic_layout_2d);
        RelativeLayout historic_3d = findViewById(R.id.historic_layout_3d);
        RelativeLayout historic_4d = findViewById(R.id.historic_layout_4d);
        RelativeLayout historic_5d = findViewById(R.id.historic_layout_5d);
        RelativeLayout historic_6d = findViewById(R.id.historic_layout_6d);
        RelativeLayout historic_7d = findViewById(R.id.historic_layout_7d);

        historic_1d.setBackgroundColor(setBackgroundColor(position));

        checkVisibility(comment_txt, comment_1d_imv);
        checkVisibility("", comment_2d_imv);
        checkVisibility("", comment_3d_imv);
        checkVisibility("", comment_4d_imv);
        checkVisibility("", comment_5d_imv);
        checkVisibility("", comment_6d_imv);
        checkVisibility("", comment_7d_imv);

        comment_1d_imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HistoricActivity.this,comment_txt,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkVisibility (String strg,ImageView imageView){
        if (strg == ""){
            imageView.setVisibility(View.INVISIBLE);
        } else {
            imageView.setVisibility(View.VISIBLE);
        }
    }

    private int setBackgroundColor(int Int) {
        switch (Int) {
            case 0:
                return (ContextCompat.getColor(getApplicationContext(), R.color.faded_red));
            case 1:
                return (ContextCompat.getColor(getApplicationContext(), R.color.warm_grey));
            case 2:
                return (ContextCompat.getColor(getApplicationContext(), R.color.cornflower_blue_65));
            case 3:
                return (ContextCompat.getColor(getApplicationContext(), R.color.light_sage));
            case 4:
                return (ContextCompat.getColor(getApplicationContext(), R.color.banana_yellow));
            default:
                return 0;
        }
    }
}
