package com.digitalnatives.tabtest.interfaces;

import com.digitalnatives.tabtest.Movie;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by AMcG on 26/11/2015.
 */
public interface TMDbInterface {

    String key = "72508de530eba41fb571b4a5b10dd99b";

    //Define API endpoints
    @GET("/search/movie/")
    Call<Movie> getMovieResponse(@Query("query")String name,
                                 @Query("api_key")String key);
}
