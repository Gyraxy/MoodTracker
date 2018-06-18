package com.duboscq.nicolas.moodtracker.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.duboscq.nicolas.moodtracker.controllers.fragments.PageFragment;
import com.duboscq.nicolas.moodtracker.R;

/**
 * Created by Nicolas DUBOSCQ on 14/06/2018
 */
public class PageAdapter extends FragmentPagerAdapter{

    private int[] colors;

    public PageAdapter(FragmentManager mgr, int[] colors){
        super(mgr);
        this.colors = colors;
    }

    @Override
    public int getCount(){
        return(5);
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                return(PageFragment.newInstance(this.colors[position], R.drawable.smiley_sad));
            case 1:
                return(PageFragment.newInstance(this.colors[position], R.drawable.smiley_disappointed));
            case 2:
                return(PageFragment.newInstance(this.colors[position], R.drawable.smiley_normal));
            case 3:
                return(PageFragment.newInstance(this.colors[position], R.drawable.smiley_happy));
            case 4:
                return(PageFragment.newInstance(this.colors[position], R.drawable.smiley_super_happy));
            default : return null;
        }
    }
}
