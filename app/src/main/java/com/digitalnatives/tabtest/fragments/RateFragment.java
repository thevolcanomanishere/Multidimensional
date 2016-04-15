package com.digitalnatives.tabtest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.digitalnatives.tabtest.R;
import com.digitalnatives.tabtest.User;

import java.util.ArrayList;

/**
 * Created by alexmcgonagle on 18/11/2015.
 */
public class RateFragment extends Fragment{

        private Button startButton;
        private String TAG = "RateFragTAG";

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
            View rootView = inflater.inflate(R.layout.rate_fragment, container, false);

            Log.d(TAG, "RATE FRAGMENT FIRING!!!!0");

//            final Stopwatch stopwatch = new Stopwatch();
            Bundle bundle = new Bundle();
            bundle = getArguments();
            String posterUrl = bundle.getString("movieName");
            Log.d(TAG, "poster url = " + posterUrl);


            startButton = (Button) rootView.findViewById(R.id.button4);

            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<Integer> ints = new ArrayList<>();
                    ints.add(1);
                    ints.add(2);
                    ints.add(3);
                    ints.add(4);
                    ints.add(5);
                    ints.add(6);

                    User.getInstance().addMovieToUser(getContext(), "TestMovie1", 12345, "A very very long movie", "11-90-12", "/dI2oQoJJ1QxDd5D1UiHfLvkoLnp.jpg", ints);
                }
            });

            //old pause button implementation
//            pauseButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    stopwatch.stop();
//                    Log.d(tag, "Timer count:" + stopwatch.toString(5));
//                    stopwatch.reset();
//                }
//            });

            return rootView;
        }



    }

