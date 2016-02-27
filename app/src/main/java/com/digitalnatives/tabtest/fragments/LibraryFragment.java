package com.digitalnatives.tabtest.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.digitalnatives.tabtest.LibraryItem;
import com.digitalnatives.tabtest.MainActivity;
import com.digitalnatives.tabtest.R;
import com.digitalnatives.tabtest.VolleyController;
import com.digitalnatives.tabtest.adapters.LibraryViewAdapter;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alexmcgonagle on 18/11/2015.
 */
public class LibraryFragment extends Fragment{

    private List<LibraryItem> libraryList;
    private Context context;
    private static final String ARG_SECTION_NUMBER = "section_number";
    private String TAG = "LibFragmentTag";
    private LibraryViewAdapter rva;
    private ConnectivityManager cm;
    private NetworkInfo activeNetwork;
    private boolean isConnected;
    private ProgressDialog pDialog;


    public static LibraryFragment newInstance(int sectionNumber) {
        LibraryFragment fragment = new LibraryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public LibraryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
//
//        libraryList = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.library_fragment, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.libRv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        //setup progress dialog
        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Downloading your library");
        pDialog.setCancelable(false);

        //getContext() needed for Picasso
        rva = new LibraryViewAdapter(libraryList, getContext());
        Log.d(TAG, libraryList.toString());
        updateLibraryAdapter();
        rv.setAdapter(rva);
        rv.setHasFixedSize(true);

        return rootView;
    }

    public double calculateAverage(List<Integer> heartRate){
        int sum = 0;
        for(int i = 0; i < heartRate.size(); i++){
            sum += heartRate.get(i);
        }
        return(double)sum / heartRate.size();
    }

    public void checkConnection(){
        cm = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public void updateLibraryAdapter() {

        showpDialog();
        Log.d(TAG, "isConnected = " + isConnected);
        checkConnection();
        if (isConnected) {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("UserMovieItem");
            query.whereEqualTo("owner", ParseUser.getCurrentUser().getUsername());
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, com.parse.ParseException e) {
                    if (e == null) {
                        Log.d(TAG, "Number of objects = " + objects.size());
                        //clear library list
                        libraryList.clear();

                        for (int i = 0; i < objects.size(); i++) {
                            String objectId = objects.get(i).getString("objectId");
                            String posterPath = objects.get(i).getString("posterPath");
                            String releaseDate = objects.get(i).getString("release");
                            String overview = objects.get(i).getString("overview");
                            String movieName = objects.get(i).getString("movieName");
                            JSONArray heartRateArrayJson = objects.get(i).getJSONArray("ratings");
                            ArrayList<Integer> heartRateList = new ArrayList<>();
                            for(int z = 0; z < heartRateArrayJson.length(); z++){
                                try {
                                    heartRateList.add(heartRateArrayJson.getInt(z));
                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }
                            }
                            double heartRateAverageDouble = calculateAverage(heartRateList);
                            int heartRateInt = (int) (heartRateAverageDouble + 0.5);

                            //add this later for more info on each title
                            //String id = objects.get(i).getString("id");

                            //add each move to the list of movies
                            libraryList.add(new LibraryItem(overview, posterPath, movieName, releaseDate, heartRateInt));
                        }
                        hidepDialog();
                        rva.notifyDataSetChanged();

                    } else {
                        hidepDialog();
                        Log.d("score", "Error: " + e.getMessage());
                    }
                }
            });

        } else {
            hidepDialog();
            Toast.makeText(getContext(), "No internet connection detected", Toast.LENGTH_LONG).show();
        }

    }

    //set up progress dialog
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
