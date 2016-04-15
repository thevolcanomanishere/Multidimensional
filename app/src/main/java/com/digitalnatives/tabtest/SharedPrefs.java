package com.digitalnatives.tabtest;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;

/**
 * Created by ChickenNugget on 14/04/2016.
 * Helper class used to store user details for check on login
 */
public class SharedPrefs {

    static final String UserToken = "user";

    static SharedPreferences getSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setUserToken(Context context, String userToken){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(UserToken, userToken);
        editor.apply();
    }

    public static String getUserToken(Context context){
        return getSharedPreferences(context).getString(UserToken, "");
    }

    public static void clearUserName(Context context)
    {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.clear(); //clear all stored data
        editor.apply();
    }

}
