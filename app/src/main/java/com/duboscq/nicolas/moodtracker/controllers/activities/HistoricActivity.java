package com.duboscq.nicolas.moodtracker.controllers.activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
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
    private RelativeLayout historic_1d,historic_2d,historic_3d,historic_4d,historic_5d,historic_6d,historic_7d;
    private ImageView comment_1d_imv,comment_2d_imv,comment_3d_imv,comment_4d_imv,comment_5d_imv,comment_6d_imv,comment_7d_imv;
    private final String[] commentTxt={"","","","","","",""};
    private final Integer[] positionInt={5,5,5,5,5,5,5};
    private final String[] date={addDayTime(-1),addDayTime(-2),addDayTime(-3),addDayTime(-4),addDayTime(-5),addDayTime(-6),addDayTime(-7)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);

        System.out.println("HistoricActivity:onCreate()");

        //Gather Commentaries and ViewPager position saved during last seven days
        for (int i=0;i<7;i++){
            commentTxt[i]=SharedPreferencesTool.getString(HistoricActivity.this,KEY_COMMENT+date[i]);
            positionInt[i]=SharedPreferencesTool.getInt(HistoricActivity.this,KEY_POSITION+date[i],5);
        }


        //Find View by iD in Historic Activity
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

        //Place all ImageView and Relative layout in table
        ImageView commentImv[] = {comment_1d_imv,comment_2d_imv,comment_3d_imv,comment_4d_imv,comment_5d_imv,comment_6d_imv,comment_7d_imv};
        RelativeLayout historic[]={historic_1d,historic_2d,historic_3d,historic_4d,historic_5d,historic_6d,historic_7d};

        //Apply background color, check if commentary saved on each seven days recorded mood
        for (int i=0;i<7;i++){
            checkVisibility(commentTxt[i],commentImv[i]);
            historic[i].setBackgroundColor(checkBackgroundcolor(positionInt[i]));
            historic[i].getLayoutParams().width=setLayoutwidth(positionInt[i]);
            commentImv[i].setOnClickListener(new myCommentClickListener(commentTxt[i]));
        }
    }

    class myCommentClickListener implements View.OnClickListener{
        final String commentTxt;
        myCommentClickListener(String commentTxt){
            this.commentTxt=commentTxt;
        }
        @Override
        public void onClick(View view){
            Toast.makeText(HistoricActivity.this, commentTxt, Toast.LENGTH_SHORT).show();
        }
    }

    //Method to check if commentary was recorded. If yes then show commentary icon
    private void checkVisibility(String strg, ImageView imageView) {
        int lenght = strg.length();
        if (lenght < 1) {
            imageView.setVisibility(View.INVISIBLE);
        } else {
            imageView.setVisibility(View.VISIBLE);
        }
    }

    //Method to apply background color depending of ViewPager position saved
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

    //Method to search Date by adding day to today Date. yyyy/MM/dd format date
    private String addDayTime(int day){
        DateFormat dateFormat =new SimpleDateFormat("yyyy/MM/dd", Locale.FRANCE);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,day);
        return dateFormat.format(cal.getTime());
    }

    //Depending of mood change layout width
    private int setLayoutwidth(int Int){
        DisplayMetrics display = this.getResources().getDisplayMetrics();
        int width = display.widthPixels;
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


    // OnStart/OnResume/OnPause/OnStop/OnDestroy method for application life cycle overview
    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("HistoricActivity:onStart()");
    }

    @Override
    protected void onResume(){
        super.onResume();
        System.out.println("HistoricActivity:onResume()");
    }
    @Override
    protected void onPause(){
        super.onPause();
        System.out.println("HistoricActivity:onPause()");
    }
    @Override
    protected void onStop(){
        super.onStop();
        System.out.println("HistoricActivity:onStop()");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        System.out.println("HistoricActivity:onDestroy()");
    }
}
