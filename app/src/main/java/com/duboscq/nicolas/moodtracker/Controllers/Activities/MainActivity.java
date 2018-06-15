package com.duboscq.nicolas.moodtracker.Controllers.Activities;

import android.app.Dialog;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.duboscq.nicolas.moodtracker.Adapters.PageAdapter;
import com.duboscq.nicolas.moodtracker.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.configureViewPager();
    }

    private void configureViewPager(){
        ViewPager pager = findViewById(R.id.activity_main_viewpager);
        pager.setAdapter(new PageAdapter(getSupportFragmentManager(), getResources().getIntArray(R.array.colorPagesViewPager), getResources().getIntArray(R.array.smileyImage)){
        });
        pager.setCurrentItem(2);
    }
}
