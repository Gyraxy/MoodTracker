package com.duboscq.nicolas.moodtracker.controllers.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.duboscq.nicolas.moodtracker.adapters.PageAdapter;
import com.duboscq.nicolas.moodtracker.R;

public class MainActivity extends AppCompatActivity {

    private int mPage;
    public static final String CURRENT_PAGE = "CURRENT_PAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mPage = savedInstanceState.getInt(CURRENT_PAGE);
        } else {
            mPage = 2;
        }

        this.configureViewPager();
    }

    private void configureViewPager(){
        ViewPager pager = findViewById(R.id.activity_main_viewpager);
        pager.setAdapter(new PageAdapter(getSupportFragmentManager(), getResources().getIntArray(R.array.colorPagesViewPager)){
        });
        pager.setCurrentItem(mPage);
    }
}
