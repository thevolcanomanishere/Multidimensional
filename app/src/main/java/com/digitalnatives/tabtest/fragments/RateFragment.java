package com.digitalnatives.tabtest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitalnatives.tabtest.R;

/**
 * Created by alexmcgonagle on 18/11/2015.
 */
public class RateFragment extends Fragment{

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
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

            return rootView;
        }



    }

