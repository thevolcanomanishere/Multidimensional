package com.digitalnatives.tabtest;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;

/**
 * Created by alexmcgonagle on 20/11/2015.
 */
public class EnableParse extends Application {

    public String tag = "EnableParse extend";

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(tag, "The class is run first");

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "CqyuqfAKTmdVZ5EwzsFTTWfs4xjVz2UjGrNcCNTj", "8gEZBpBpGN0FJT0E3A5foYusxoiPucfdmZRhIzQ9");
    }
}
