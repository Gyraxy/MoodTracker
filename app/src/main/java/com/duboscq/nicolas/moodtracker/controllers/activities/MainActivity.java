package com.duboscq.nicolas.moodtracker.controllers.activities;

// Android Libraries
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.duboscq.nicolas.moodtracker.adapters.PageAdapter;
import com.duboscq.nicolas.moodtracker.R;
import com.duboscq.nicolas.moodtracker.utils.SharedPreferencesTool;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int mPosition;
    private int mPage;
    private final String todayDate = getDateTime();
    private DateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("MainActivity:onCreate()");

        mPosition = SharedPreferencesTool.getInt(MainActivity.this, "POSITION"+todayDate, -1);

        //Check if application already launched with a current page saved
        if (mPosition == -1){
            mPage = 2;
        } else {
            mPage = mPosition;
        }
        this.configureViewPager();
    }

    //ViewPager configuration
    private void configureViewPager(){
        ViewPager pager = findViewById(R.id.activity_main_viewpager);
        pager.setAdapter(new PageAdapter(getSupportFragmentManager(), getResources().getIntArray(R.array.colorPagesViewPager)){
        });
        pager.setCurrentItem(mPage);

        //PageChangeListener to save current page position in SharedPreferences
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                SharedPreferencesTool.putInt(MainActivity.this,"POSITION"+todayDate,position);
            }
            @Override
            public void onPageSelected(int position) {}
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    //Date Method to get Today Date with yyyy/MM/dd format
    private String getDateTime() {
        dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.FRANCE);
        Date date = new Date();
        return dateFormat.format(date);
    }

    // OnStart/OnResume/OnPause/OnStop/OnDestroy method for application life cycle overview
    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("MainActivity:onStart()");
    }

    @Override
    protected void onResume(){
        super.onResume();
        System.out.println("MainActivity:onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        System.out.println("MainActivity:onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        System.out.println("MainActivity:onStop()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        System.out.println("MainActivity:onDestroy()");
    }
}
