package com.digitalnatives.tabtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.digitalnatives.tabtest.activities.LoginActivity;
import com.digitalnatives.tabtest.classes.CustomViewPager;
import com.digitalnatives.tabtest.fragments.LibraryFragment;
import com.digitalnatives.tabtest.fragments.RateFragment;
import com.digitalnatives.tabtest.fragments.SearchFragment;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private static SectionsPagerAdapter mSectionsPagerAdapter;
    public static CustomViewPager mViewPager;
    public final String BASE_URL = "http://api.themoviedb.org/3/";
    private Intent loadLauncher;

    public static String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());



        // Set up the ViewPager with the sections adapter.
        mViewPager = (CustomViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setPagingEnabled(false);



        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
          //      Intent startSettings = new Intent(this, Settings.class);
          //      startActivity(startSettings);
                return true;
            case R.id.action_logout:
                loadLauncher = new Intent(this, LoginActivity.class);
                logOut();
                SharedPrefs.clearUserName(this);
                Toast.makeText(MainActivity.this, "You have been logged out", Toast.LENGTH_SHORT).show();
                startActivity(loadLauncher);
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void logOut(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiConfig.getBASE_URL() + ApiConfig.getLOGOUT(),
                new com.android.volley.Response.Listener<String>() {
                    //
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject responseObject = new JSONObject(response);
                            Boolean error = responseObject.getBoolean("error");
                            if(!error){

                            } else {
                                Toast.makeText(MainActivity.this, responseObject.get("message").toString(), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String, String> headers = super.getHeaders();
                if(headers == null || headers.equals(Collections.emptyMap())){
                    headers = new HashMap<String, String>();
                }
                String token = SharedPrefs.getUserToken(MainActivity.this);
                headers.put("token", token);
                return headers;
            }
        };

        VolleyController.getInstance().addToRequestQueue(stringRequest);
    }




    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm)  {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if(position == 0) {
                return LibraryFragment.newInstance(position + 1);
            }
            else if (position == 1){
                return SearchFragment.newInstance(position + 1);
            }
            else{
                return RateFragment.newInstance(position + 1);
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.tablibrary);
                case 1:
                    return getString(R.string.tabSearch);
                case 2:
                    return getString(R.string.tabRate);
            }
            return null;
        }
    }




}

