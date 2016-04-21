package com.digitalnatives.tabtest.fragments;

import android.os.Bundle;
import android.provider.Telephony;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalnatives.tabtest.ApiConfig;
import com.digitalnatives.tabtest.MainActivity;
import com.digitalnatives.tabtest.Movie;
import com.digitalnatives.tabtest.R;
import com.digitalnatives.tabtest.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alexmcgonagle on 18/11/2015.
 */
public class RateFragment extends Fragment{

        private Button startButton;
        private Button saveButton;
        private static String TAG = "RateFragTAG";
        private static View rootView;
        private Date startTime;
        private Date endTime;



        static boolean HasLoaded = false;

        public static void setHasLoaded(boolean hasLoaded) {
            HasLoaded = hasLoaded;
        }

        public static boolean isHasLoaded() {
            return HasLoaded;
        }

        public static void refreshFragment(){
            rootView.invalidate();
            Log.d(TAG, "View invalidated");
        }


    private static final String ARG_SECTION_NUMBER = "section_number";


        public static RateFragment newInstance(int sectionNumber) {
            RateFragment fragment = new RateFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public RateFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);

        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.rate_fragment, container, false);

            if(!HasLoaded){
//                rootView.findViewById(R.id.RateFragLinear).setVisibility(View.GONE);
                rootView.findViewById(R.id.linear1).setVisibility(View.GONE);
                rootView.findViewById(R.id.linear2).setVisibility(View.GONE);


            } else {
                rootView.findViewById(R.id.linear1).setVisibility(View.VISIBLE);
                rootView.findViewById(R.id.linear2).setVisibility(View.VISIBLE);
                rootView.findViewById(R.id.warning).setVisibility(View.GONE);
            }
            if(Movie.getName() != null) {
                TextView movieTitle = (TextView) rootView.findViewById(R.id.movieTitle);
                movieTitle.setText(Movie.getName());
                TextView releaseDate = (TextView) rootView.findViewById(R.id.releaseDate);
                releaseDate.setText(Movie.getReleaseDate());
                TextView description = (TextView) rootView.findViewById(R.id.description);
                description.setText(Movie.getDescription());

                ImageView poster = (ImageView) rootView.findViewById(R.id.imageView);
                Picasso.with(getContext()).load(ApiConfig.getBaseTmdbImageUrl() + Movie.getImagePath() + ApiConfig.getTmdbKey()).into(poster);
            }

            startButton = (Button) rootView.findViewById(R.id.startButton);
            saveButton = (Button) rootView.findViewById(R.id.saveButton);

            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startTime = new Date();
                    startTime.getTime();
                    Log.d(TAG, "Date" + startTime.toString());
                    startButton.setText(R.string.timerButtonStop);
//                    ArrayList<Integer> ints = new ArrayList<>();
//
//                    User.getInstance().addMovieToUser(getContext(), "TestMovie1", 12345, "A very very long movie", "11-90-12", "/dI2oQoJJ1QxDd5D1UiHfLvkoLnp.jpg", ints);
             }
            });

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startButton.setText(R.string.timerButtonStart);
                    endTime = new Date();
                    endTime.getTime();

                    long diff = endTime.getTime() - startTime.getTime();
                    long diffSeconds = diff / 1000 % 60;
                    Log.d(TAG, "Time diff = " + diffSeconds);

                    ArrayList<Integer> heartRates = new ArrayList<Integer>();
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));
                    heartRates.add(makeRandomNum(65, 90));


                    Log.d("RATEADAPT", "id = " + Movie.getId());
                    User.getInstance().addMovieToUser(getContext(),
                            Movie.getName(),
                            Movie.getId(),
                            Movie.getDescription(),
                            Movie.getReleaseDate(),
                            Movie.getImagePath(),
                            heartRates);


                    LibraryFragment.setFirstLoad(true);

                }
            });






            return rootView;
        }

    public int makeRandomNum(int low, int high){
        int r = (int) (Math.random() * (high - low)) + low;
        return r;
    }



    }

