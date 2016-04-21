package com.digitalnatives.tabtest.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.digitalnatives.tabtest.ApiConfig;
import com.digitalnatives.tabtest.Models.MovieIdResponse;
import com.digitalnatives.tabtest.R;
import com.digitalnatives.tabtest.User;
import com.digitalnatives.tabtest.VolleyController;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class LibraryViewActivity extends AppCompatActivity {

    final private String TAG = "LibViewAct";
    private ConnectivityManager cm;
    private NetworkInfo activeNetwork;
    private MovieIdResponse info;

    private TextView imdbRateing;
    private TextView movieNameText;
    private TextView movieBudget;
    private ImageView poster;
    private TextView description;
    private TextView releaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //check network connection
        cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork =cm.getActiveNetworkInfo();

        poster = (ImageView) findViewById(R.id.libraryViewPoster);
        movieNameText = (TextView) findViewById(R.id.libViewMovieName);
        movieBudget = (TextView) findViewById(R.id.movieBudget);
        releaseDate = (TextView) findViewById(R.id.releaseDate);
        imdbRateing = (TextView) findViewById(R.id.imdbRating);
        description = (TextView) findViewById(R.id.descriptionText);
        Button loadChartBtn = (Button) findViewById(R.id.loadChartBtn);


        //get intent extras with movie info
        Intent intent = getIntent();
        String movieName = intent.getStringExtra("movieName");
        this.setTitle(movieName);
        int id = intent.getIntExtra("id", 0);
        if(id < 1){
            Log.d(TAG, "id is defaulting to 0 or? " + id);
        }
        String descriptionString = intent.getStringExtra("description");
        String releaseDateString = intent.getStringExtra("releaseDate");
        String posterPathString = intent.getStringExtra("posterPath");
        final ArrayList<Integer> heartRates = intent.getIntegerArrayListExtra("heartRates");

        //make request to get more info
        getMoreMovieInfo(id);









        loadChartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChartView.class);
                Bundle bundle = new Bundle();
                bundle.putIntegerArrayList("heartRates", heartRates);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }

    public void getMoreMovieInfo(int id){


            boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
            Log.d(TAG, "isConnected =" + isConnected);
            if(isConnected){

                String idString = Integer.toString(id);
                String url = ApiConfig.getBaseTmdbMovieId() + idString + ApiConfig.getTmdbKey();
                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    Log.d(TAG, response.toString());
                                    String responseString = response.toString();
                                    Gson gson = new Gson();

                                    //map response to java movie object
                                    info = gson.fromJson(responseString, MovieIdResponse.class);
                                    Double vote = info.getVoteAverage();
                                    String voteString = Double.toString(vote);
                                    imdbRateing.setText("IMDB: " + voteString);
                                    movieBudget.setText("Budget: $" +info.getBudget().toString());
                                    Picasso.with(LibraryViewActivity.this).load(ApiConfig.getBaseTmdbImageUrl()
                                            + info.getPosterPath() + ApiConfig.getTmdbKey())
                                    .into(poster);
                                    movieNameText.setText(info.getOriginalTitle());
                                    releaseDate.setText("Released:" + info.getReleaseDate());
                                    description.setText(info.getOverview());



                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        Toast.makeText(LibraryViewActivity.this, "Volley error", Toast.LENGTH_SHORT).show();
                    }
                });

                VolleyController.getInstance().addToRequestQueue(jsonObjReq);
            } else {
                //display toast for user to connect to the internet
                Toast.makeText(this, "You must have an active internet connection to search" +
                                " for a movie",
                        Toast.LENGTH_LONG).show();
            }



        }



}
