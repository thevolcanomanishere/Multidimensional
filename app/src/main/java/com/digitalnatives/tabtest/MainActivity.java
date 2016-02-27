package com.digitalnatives.tabtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.digitalnatives.tabtest.activities.LoginActivity;
import com.digitalnatives.tabtest.classes.CustomViewPager;
import com.digitalnatives.tabtest.fragments.LibraryFragment;
import com.digitalnatives.tabtest.fragments.RateFragment;
import com.digitalnatives.tabtest.fragments.SearchFragment;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    public static CustomViewPager mViewPager;
    public final String BASE_URL = "http://api.themoviedb.org/3/";
    private ParseUser user;
    private Intent loadLauncher;

    public static String tag = "TabTestLog";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        //Parse Setup
        ParseUser user = ParseUser.getCurrentUser();

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
                user.logOut();
                Log.d(tag, "Current user = " + ParseUser.getCurrentUser());

                startActivity(loadLauncher);
            case R.id.action_TestParse:
                Log.d(tag, "Running Test for Parse");

                String description = "This is just a test";

//                ParseObject parseLibraryItem = new ParseObject("ItemTest3");
//                parseLibraryItem.add("movieId", 121);
//                parseLibraryItem.add("runtime", 134);
//                parseLibraryItem.add("status", "not working");
//                parseLibraryItem.add("name", "TestParseMovie");
//                parseLibraryItem.add("releaseDate", "2014-11-06");
//                parseLibraryItem.add("imagePath", "/7k9db7pJyTaVbz3G4eshGltivR1.jpg");
//                parseLibraryItem.add("description", description);
//                parseLibraryItem.add("tagline", "The craziest movie ever");
//                parseLibraryItem.add("createdBy", ParseUser.getCurrentUser());
//                parseLibraryItem.setACL(new ParseACL(ParseUser.getCurrentUser()));
//                parseLibraryItem.saveInBackground();


            default:
                return super.onOptionsItemSelected(item);

        }

    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if(position == 0) {
                return LibraryFragment.newInstance(position + 1);
            }
            else if (position == 1){
                return RateFragment.newInstance(position + 1);
            }
            else{
                return SearchFragment.newInstance(position + 1);
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
                    return getString(R.string.tabRate);
                case 2:
                    return getString(R.string.tabSearch);
            }
            return null;
        }
    }




}
