package com.digitalnatives.tabtest;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.digitalnatives.tabtest.Models.MovieIdResponse;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ChickenNugget on 10/04/2016.
 */
public class User extends VolleyController {

    private static User mUser = new User();
    private String token;
    private static List<LibraryItem> libraryItemList;

    public MovieIdResponse getMovie() {
        return movie;
    }

    public void setMovie(MovieIdResponse movie) {
        this.movie = movie;
    }

    private MovieIdResponse movie;


    public List<LibraryItem> getLibraryItemList() {
        return libraryItemList;
    }

    public void setLibraryItemList(List<LibraryItem> libraryItemList) {
        this.libraryItemList = libraryItemList;
    }




    public static synchronized User getInstance() {
        return mUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void getMoreInfoOnMovie(final Context context, int id){



        String url = ApiConfig.getBaseTmdbInfoUrl() + "/" + id + ApiConfig.getTmdbKey();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String string = response.toString();
                        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
                        //TODO: Handle Error here
                        Gson gson = new Gson();
                        String responseString = response.toString();
                        movie = gson.fromJson(responseString, MovieIdResponse.class);
                        User.getInstance().setMovie(movie);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        VolleyController.getInstance().addToRequestQueue(jsonObjReq);
    }

    public void addMovieToUser(final Context context, final String movieName, final int movieId, final String overview,
                               final String releaseDate, final String posterPath, final ArrayList heartRates){

        //create JSON object
        JSONObject js = new JSONObject();
        try {
            js.put("movieName", movieName);
            js.put("movieId", movieId);
            js.put("overview", overview);
            js.put("releaseDate", releaseDate);
            js.put("posterPath", posterPath);
            JSONArray arr = new JSONArray(heartRates);
            js.put("heartRates", arr);

        } catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,
                ApiConfig.getADD_MOVIES(), js,
                    new com.android.volley.Response.Listener<JSONObject>() {
                    //
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Boolean error = response.getBoolean("error");
                            String test = response.toString();
                            Log.d("JSOn String : ", test);
                            if(!error){
                                Toast.makeText(context, "Movie added", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(context, response.get("message").toString(), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG, "Error in sending######");
                        }
                    }
                }, new com.android.volley.Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Error thrown here");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String, String> headers = super.getHeaders();
                if(headers == null || headers.equals(Collections.emptyMap())){
                    headers = new HashMap<String, String>();
                }
                String token = SharedPrefs.getUserToken(context);
                headers.put("token", token);
                return headers;
            }
        };

        VolleyController.getInstance().addToRequestQueue(stringRequest);
    }


}
