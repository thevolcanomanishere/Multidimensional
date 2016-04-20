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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.digitalnatives.tabtest.ApiConfig;
import com.digitalnatives.tabtest.LibraryItem;

import com.digitalnatives.tabtest.R;
import com.digitalnatives.tabtest.SharedPrefs;

import com.digitalnatives.tabtest.User;
import com.digitalnatives.tabtest.VolleyController;
import com.digitalnatives.tabtest.adapters.LibraryViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.Collections;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

/**
 * Created by alexmcgonagle on 18/11/2015.
 */
public class LibraryFragment extends Fragment{

    private List<LibraryItem> libraryList;
    private static final String ARG_SECTION_NUMBER = "section_number";
    private String TAG = "LibFragmentTag";
    private LibraryViewAdapter rva;
    private ConnectivityManager cm;
    private NetworkInfo activeNetwork;
    private boolean isConnected;
    private ProgressDialog pDialog;

    //used to stop library updating on every page tab
    private static boolean firstLoad = true;

    public static void setFirstLoad(boolean firstLoad) {
        LibraryFragment.firstLoad = firstLoad;
    }


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
        libraryList = new ArrayList<>();
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
//        llm.setReverseLayout(true);
        rv.setLayoutManager(llm);


        
        //setup progress dialog
        pDialog = new ProgressDialog(getContext());


        //getContext() needed for Picasso
        rva = new LibraryViewAdapter(libraryList, getContext());

        //check if info is already downloaded
        if(firstLoad){
            updateLibraryNode();
        } else {
            libraryList.addAll(User.getInstance().getLibraryItemList());
        }

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


    private void updateLibraryNode(){

        showpDialog();
        checkConnection();
        pDialog.setMessage("Downloading your library");
        pDialog.setCancelable(false);

        if(isConnected){

            libraryList.clear();

            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    ApiConfig.getGET_MOVIES(),
                    new Response.Listener<String>() {
                        //
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject responseObject = new JSONObject(response);
                                Boolean error = responseObject.getBoolean("error");

                                if(!error){

                                    //get message array in JSON object
                                    JSONObject responseTest = new JSONObject(response);
                                    JSONArray moviesArray = responseTest.getJSONArray("message");

                                    //check array size
                                    if(moviesArray.length() == 0){
                                        Toast.makeText(getContext(), "You have no movies saved online", Toast.LENGTH_SHORT).show();
                                    } else {
                                        //cycle through array and add each element to the libraryAdapter
                                        for (int i = 0; i < moviesArray.length(); i++) {
                                            JSONObject childObject = moviesArray.getJSONObject(i);
                                            String movieName = childObject.getString("movieName");
//                                            int movieId = childObject.getInt("id");
//                                            Log.d(TAG, "Movie id = " + movieId);
                                            String overview = childObject.getString("overview");
                                            String releaseDate = childObject.getString("releaseDate");
                                            String posterPath = childObject.getString("posterPath");
                                            JSONArray heartRateArray = childObject.getJSONArray("heartRates");

                                            ArrayList<Integer> heartRateList = new ArrayList<>();
                                            for (int z = 0; z < heartRateArray.length(); z++) {
                                                try {
                                                    Log.d(TAG, "Number = " + heartRateArray.getInt(z));
                                                    heartRateList.add(heartRateArray.getInt(z));
                                                } catch (JSONException e1) {
                                                    e1.printStackTrace();
                                                    hidepDialog();
                                                }
                                            }

                                            double heartRateAverageDouble = calculateAverage(heartRateList);
                                            int heartRateInt = (int) (heartRateAverageDouble + 0.5);

                                            libraryList.add(new LibraryItem(overview,
                                                    posterPath,
                                                    movieName,
                                                    releaseDate,
                                                    heartRateInt,
                                                    heartRateList));
                                        }
                                    }
                                    User.getInstance().setLibraryItemList(libraryList);
                                    hidepDialog();
                                    rva.notifyDataSetChanged();
                                    firstLoad = false;

                                } else {
                                    hidepDialog();
                                    Toast.makeText(getContext(), responseObject.get("message").toString(), Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                hidepDialog();
                            }
                        }
                    }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    hidepDialog();
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError{
                    Map<String, String> headers = super.getHeaders();
                    if(headers == null || headers.equals(Collections.emptyMap())){
                        headers = new HashMap<String, String>();
                    }
                    String token = SharedPrefs.getUserToken(getContext());
                    headers.put("token", token);
                    return headers;
                }
            };

            VolleyController.getInstance().addToRequestQueue(stringRequest);
        }
        else {
            hidepDialog();
        Toast.makeText(getContext(), "No internet connection", Toast.LENGTH_SHORT).show();
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
