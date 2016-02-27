package com.digitalnatives.tabtest.fragments;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.digitalnatives.tabtest.R;
import com.digitalnatives.tabtest.Response.ResultsEntity;
import com.digitalnatives.tabtest.VolleyController;
import com.digitalnatives.tabtest.adapters.SearchViewAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexmcgonagle on 18/11/2015.
 */
public class SearchFragment extends Fragment {

    private EditText searchTextEdit;
    private Button searchButton;
    private static final String ARG_SECTION_NUMBER = "section_number";
    private String key = "?api_key=72508de530eba41fb571b4a5b10dd99b";
    private static SearchViewAdapter rva = null;
    private Context context;
    private String baseUrl = "http://api.themoviedb.org/3/search/movie";
    private ProgressDialog pDialog;
    private String TAG = "JSON Response = ";
    private Gson gson;
    private List<com.digitalnatives.tabtest.Response.ResultsEntity> movieResults;
    private com.digitalnatives.tabtest.Response responseObject;
    private ConnectivityManager cm;
    private NetworkInfo activeNetwork;
    //temp
    private String txtResponse = "";
    private TextView noResultsTxt;


    public static SearchFragment newInstance(int sectionNumber) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public SearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        List<Integer> idArray = new ArrayList<>();
        idArray.add(1);
        idArray.add(1);
        idArray.add(1);

        movieResults = new ArrayList<>();
        movieResults.add(new ResultsEntity(true, "Path", 1, "Language", "Title of Movie", "This film is about",
                "11-09-19", "path", 1231231231, "Title", true, 123124131, 23, idArray));

        //check network connection
        cm = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork =cm.getActiveNetworkInfo();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_fragment, container, false);


        final RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        rva = new SearchViewAdapter(movieResults, getContext());
        rv.setAdapter(rva);
        rv.setHasFixedSize(true);
        rv.setVisibility(View.GONE);

        searchButton = (Button) rootView.findViewById(R.id.button);
        searchTextEdit = (EditText) rootView.findViewById(R.id.searchText);
        noResultsTxt = (TextView) rootView.findViewById(R.id.noResultsTxt);
        noResultsTxt.setVisibility(View.GONE);
        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Working....");
        pDialog.setCancelable(false);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieResults.clear();
                searchImdb();
                rv.setVisibility(View.VISIBLE);
            }
        });


        return rootView;
    }

    public void searchImdb() {

        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        Log.d(TAG, "isConnected =" + isConnected);
        if(isConnected){

        noResultsTxt.setVisibility(View.GONE);
        showpDialog();
        String query = searchTextEdit.getText().toString();
        Log.d(TAG, "Query =" + query);
        String url = baseUrl + key + "&query=" + query;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Log.d(TAG, response.toString());
                            String responseString = response.toString();
                            gson = new Gson();

                            //map response to java movie object
                            responseObject = gson.fromJson(responseString, com.digitalnatives.tabtest.Response.class);
                            movieResults.clear();
                            Log.d(TAG, "Number of results" + responseObject.getResults().size());

                            //put each movie into movieResults array for RV adapter
                            List<ResultsEntity> newSearchResults = responseObject.getResults();
                            for (int i = 0; i < newSearchResults.size(); i++) {
                                movieResults.add(newSearchResults.get(i));
                            }

                            Log.d(TAG, "Movie results size = " + movieResults.size());
                            //check if results is empty
                            if (movieResults.size() == 0) {
                                noResultsTxt.setVisibility(View.VISIBLE);
                            } else {
                                //Tell RV to update and check movieResults
                                rva.notifyDataSetChanged();
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }

                        //hide progress dialog
                        hidepDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                hidepDialog();
            }
        });

        VolleyController.getInstance().addToRequestQueue(jsonObjReq);
    } else {
            //display toast for user to connect to the internet
            Toast.makeText(getContext(), "You must have an active internet connection to search" +
                            " for a movie",
                    Toast.LENGTH_LONG).show();
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
