package com.duboscq.nicolas.moodtracker.controllers.activities;


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.duboscq.nicolas.moodtracker.adapters.PageAdapter;
import com.duboscq.nicolas.moodtracker.R;
import com.duboscq.nicolas.moodtracker.controllers.SharedPreferencesTool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private int mPosition;
    private int mPage;
    private final String todayDate = getDateTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPosition = SharedPreferencesTool.getInt(MainActivity.this, "POSITION"+todayDate, 5);

        if (mPosition == 5){
            mPage = 2;
        } else {
            mPage = mPosition;
        }

        this.configureViewPager();
    }

    private void configureViewPager(){
        ViewPager pager = findViewById(R.id.activity_main_viewpager);
        pager.setAdapter(new PageAdapter(getSupportFragmentManager(), getResources().getIntArray(R.array.colorPagesViewPager)){
        });
        pager.setCurrentItem(mPage);

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

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
