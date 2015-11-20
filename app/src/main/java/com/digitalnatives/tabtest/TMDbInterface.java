package com.digitalnatives.tabtest;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by alexmcgonagle on 18/11/2015.
 */
public interface TMDbInterface {
    String API_KEY = "72508de530eba41fb571b4a5b10dd99b";
    @Headers({"api_key=" + API_KEY})
    @GET("/search/movie/{id}")
    Call<List<Movie>> groupMovie(@Path("id")@Query("name") String name);




}

