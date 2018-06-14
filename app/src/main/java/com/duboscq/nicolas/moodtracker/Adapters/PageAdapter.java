package com.duboscq.nicolas.moodtracker.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.duboscq.nicolas.moodtracker.Controllers.Fragments.PageFragment;

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
        return(PageFragment.newInstance(position, this.colors[position]));
    }

}
