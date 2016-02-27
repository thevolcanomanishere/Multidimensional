package com.digitalnatives.tabtest.interfaces;

import com.digitalnatives.tabtest.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by AMcG on 26/11/2015.
 */
public interface TMDbInterface {



    //Define API endpoints
    @GET("/search/movie")
    Call<Movie> getMovieResponse(@Query("query")String name,
                                 @Query("api_key")String key);
}
