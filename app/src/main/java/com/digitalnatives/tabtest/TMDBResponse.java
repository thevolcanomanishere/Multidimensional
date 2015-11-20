package com.digitalnatives.tabtest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by alexmcgonagle on 18/11/2015.
 */
public class TMDBResponse {

    List<Movie> movies;
    public static Movie movie;

    // public constructor is necessary for collections
    public TMDBResponse() {
        movies = new ArrayList<Movie>();
    }

    public static TMDBResponse parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        TMDBResponse tmdbResponse = gson.fromJson(response, (Type) movie);
        return tmdbResponse;
    }
}
