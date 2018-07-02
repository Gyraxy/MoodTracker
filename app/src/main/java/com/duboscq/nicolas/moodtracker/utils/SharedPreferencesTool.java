package com.duboscq.nicolas.moodtracker.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Nicolas DUBOSCQ on 20/06/2018
 */
public class SharedPreferencesTool {

    public static String getString(Context context, String key){
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return mPreferences.getString(key,"");
    }

    public static int getInt(Context context, String key, int defaultvalue){
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return mPreferences.getInt(key,defaultvalue);
    }

    public static void putString(Context context, String key, String value){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value).apply();
    }

    public static void putInt(Context context, String key, int value){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(key, value).apply();
    }

}
