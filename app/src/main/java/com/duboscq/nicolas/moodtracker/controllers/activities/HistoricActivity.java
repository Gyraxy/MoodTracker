package com.duboscq.nicolas.moodtracker.controllers.activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.duboscq.nicolas.moodtracker.R;
import com.duboscq.nicolas.moodtracker.utils.SharedPreferencesTool;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class HistoricActivity extends AppCompatActivity {

    private static final String KEY_COMMENT = "COMMENT";
    private static final String KEY_POSITION = "POSITION";
    private String comment_7d_txt,comment_6d_txt,comment_5d_txt,comment_4d_txt,comment_3d_txt,comment_2d_txt,comment_1d_txt;
    private final String date_7d=addDayTime(-7),date_6d=addDayTime(-6),date_5d=addDayTime(-5),date_4d=addDayTime(-4),date_3d=addDayTime(-3),date_2d=addDayTime(-2),date_1d=addDayTime(-1);
    private int position_7d,position_6d,position_5d,position_4d,position_3d,position_2d,position_1d;
    private RelativeLayout historic_1d,historic_2d,historic_3d,historic_4d,historic_5d,historic_6d,historic_7d;
    private ImageView comment_1d_imv,comment_2d_imv,comment_3d_imv,comment_4d_imv,comment_5d_imv,comment_6d_imv,comment_7d_imv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);
        comment_7d_txt = SharedPreferencesTool.getString(HistoricActivity.this,KEY_COMMENT+date_7d);
        comment_6d_txt = SharedPreferencesTool.getString(HistoricActivity.this,KEY_COMMENT+date_6d);
        comment_5d_txt = SharedPreferencesTool.getString(HistoricActivity.this,KEY_COMMENT+date_5d);
        comment_4d_txt = SharedPreferencesTool.getString(HistoricActivity.this,KEY_COMMENT+date_4d);
        comment_3d_txt = SharedPreferencesTool.getString(HistoricActivity.this,KEY_COMMENT+date_3d);
        comment_2d_txt = SharedPreferencesTool.getString(HistoricActivity.this,KEY_COMMENT+date_2d);
        comment_1d_txt = SharedPreferencesTool.getString(HistoricActivity.this,KEY_COMMENT+date_1d);
        position_7d = SharedPreferencesTool.getInt(HistoricActivity.this,KEY_POSITION+date_7d,5);
        position_6d = SharedPreferencesTool.getInt(HistoricActivity.this,KEY_POSITION+date_6d,5);
        position_5d = SharedPreferencesTool.getInt(HistoricActivity.this,KEY_POSITION+date_5d,5);
        position_4d = SharedPreferencesTool.getInt(HistoricActivity.this,KEY_POSITION+date_4d,5);
        position_3d = SharedPreferencesTool.getInt(HistoricActivity.this,KEY_POSITION+date_3d,5);
        position_2d = SharedPreferencesTool.getInt(HistoricActivity.this,KEY_POSITION+date_2d,5);
        position_1d = SharedPreferencesTool.getInt(HistoricActivity.this,KEY_POSITION+date_1d,5);
        comment_1d_imv = findViewById(R.id.activity_historic_comment_1d_imv);
        comment_2d_imv = findViewById(R.id.activity_historic_comment_2d_imv);
        comment_3d_imv = findViewById(R.id.activity_historic_comment_3d_imv);
        comment_4d_imv = findViewById(R.id.activity_historic_comment_4d_imv);
        comment_5d_imv = findViewById(R.id.activity_historic_comment_5d_imv);
        comment_6d_imv = findViewById(R.id.activity_historic_comment_6d_imv);
        comment_7d_imv = findViewById(R.id.activity_historic_comment_7d_imv);
        historic_1d = findViewById(R.id.activity_historic_1d_rlayout);
        historic_2d = findViewById(R.id.activity_historic_2d_rlayout);
        historic_3d = findViewById(R.id.activity_historic_3d_rlayout);
        historic_4d = findViewById(R.id.activity_historic_4d_rlayout);
        historic_5d = findViewById(R.id.activity_historic_5d_rlayout);
        historic_6d = findViewById(R.id.activity_historic_6d_rlayout);
        historic_7d = findViewById(R.id.activity_historic_7d_rlayout);
        historic_1d.setBackgroundColor(checkBackgroundcolor(position_1d));
        historic_2d.setBackgroundColor(checkBackgroundcolor(position_2d));
        historic_3d.setBackgroundColor(checkBackgroundcolor(position_3d));
        historic_4d.setBackgroundColor(checkBackgroundcolor(position_4d));
        historic_5d.setBackgroundColor(checkBackgroundcolor(position_5d));
        historic_6d.setBackgroundColor(checkBackgroundcolor(position_6d));
        historic_7d.setBackgroundColor(checkBackgroundcolor(position_7d));
        checkVisibility(comment_1d_txt, comment_1d_imv);
        checkVisibility(comment_2d_txt, comment_2d_imv);
        checkVisibility(comment_3d_txt, comment_3d_imv);
        checkVisibility(comment_4d_txt, comment_4d_imv);
        checkVisibility(comment_5d_txt, comment_5d_imv);
        checkVisibility(comment_6d_txt, comment_6d_imv);
        checkVisibility(comment_7d_txt, comment_7d_imv);
        historic_1d.getLayoutParams().width= setLayoutwidth(position_1d);
        historic_2d.getLayoutParams().width= setLayoutwidth(position_2d);
        historic_3d.getLayoutParams().width= setLayoutwidth(position_3d);
        historic_4d.getLayoutParams().width= setLayoutwidth(position_4d);
        historic_5d.getLayoutParams().width= setLayoutwidth(position_5d);
        historic_6d.getLayoutParams().width= setLayoutwidth(position_6d);
        historic_7d.getLayoutParams().width= setLayoutwidth(position_7d);
        comment_1d_imv.setOnClickListener(new myCommentClickListener(comment_1d_txt));
        comment_2d_imv.setOnClickListener(new myCommentClickListener(comment_2d_txt));
        comment_3d_imv.setOnClickListener(new myCommentClickListener(comment_3d_txt));
        comment_4d_imv.setOnClickListener(new myCommentClickListener(comment_4d_txt));
        comment_5d_imv.setOnClickListener(new myCommentClickListener(comment_5d_txt));
        comment_6d_imv.setOnClickListener(new myCommentClickListener(comment_6d_txt));
        comment_7d_imv.setOnClickListener(new myCommentClickListener(comment_7d_txt));
    }
    public class myCommentClickListener implements View.OnClickListener{
        String commentTxt;
        public myCommentClickListener(String commentTxt){
            this.commentTxt=commentTxt;
        }
        @Override
        public void onClick(View view){
            Toast.makeText(HistoricActivity.this, commentTxt, Toast.LENGTH_SHORT).show();
        }
    }

    private void checkVisibility(String strg, ImageView imageView) {
        int lenght = strg.length();
        if (lenght < 1) {
            imageView.setVisibility(View.INVISIBLE);
        } else {
            imageView.setVisibility(View.VISIBLE);
        }
    }

    private int checkBackgroundcolor(int Int) {
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
            case 5:
                return (ContextCompat.getColor(getApplicationContext(),R.color.light_grey));
            default:
                return 0;
        }
    }

    private String addDayTime(int day){
        DateFormat dateFormat =new SimpleDateFormat("yyyy/MM/dd", Locale.FRANCE);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,day);
        return dateFormat.format(cal.getTime());
    }

    private int setLayoutwidth(int Int){
        int width = getWindowManager().getDefaultDisplay().getWidth();
        switch (Int){
            case 0:
                return (int)Math.round(width*0.4);
            case 1:
                return (int)Math.round(width*0.55);
            case 2:
                return (int)Math.round(width*0.7);
            case 3:
                return (int)Math.round(width*0.85);
            case 4:
                return width;
            case 5:
                return width;
                default:
                    return width;
        }
    }
}
