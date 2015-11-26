package com.digitalnatives.tabtest.services;

import com.squareup.okhttp.OkHttpClient;

import retrofit.Retrofit;

/**
 * Created by AMcG on 26/11/2015.
 */
public class TMDbService {

    public static final String API_URL = "http://api.themoviedb.org/3/";

    private static OkHttpClient httpClient = new OkHttpClient();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_URL);
     //               .
   // .addConverterFactory(GsonConverterFactory.create());
}
