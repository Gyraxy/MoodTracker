package com.duboscq.nicolas.moodtracker.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Nicolas DUBOSCQ on 18/06/2018
 */
public class VerticalViewPager extends ViewPager {

    public VerticalViewPager(Context context){
        super(context);
        init();
    }

    public VerticalViewPager(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }
    private void init(){
        setPageTransformer(true, new VerticalViewPager.PageTransformer());
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev){
        boolean interceped = super.onInterceptTouchEvent(swapXY(ev));
        swapXY(ev);
        return interceped;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {return super.onTouchEvent(swapXY(ev));}

    private MotionEvent swapXY(MotionEvent ev){
        float width = getWidth();
        float height = getHeight();
        float newX = (ev.getY() / height)* width;
        float newY = (ev.getX() / width)* height;
        ev.setLocation(newX,newY);
        return ev;
    }

    @Override
    public boolean performClick(){
        super.performClick();
        return true;
    }

    public class PageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(@NonNull View page, float position) {

            if (position < -1) {
                // [-infinity, -1], view page is off-screen to the left

                // hide the page.
                page.setVisibility(View.INVISIBLE);

            } else if (position <= 1) {
                // [-1, 1], page is on screen

                // show the page
                page.setVisibility(View.VISIBLE);

                // get page back to the center of screen since it will get swipe horizontally by default.
                page.setTranslationX(page.getWidth() * -position);

                // set Y position to swipe in vertical direction.
                float y = position * page.getHeight();
                page.setTranslationY(y);

            } else {
                // [1, +infinity], page is off-screen to the right

                // hide the page.
                page.setVisibility(View.INVISIBLE);
            }
        }
    }

}
