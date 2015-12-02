package com.digitalnatives.tabtest.services;

import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by AMcG on 26/11/2015.
 */
public class TMDbService {

    public static final String API_URL = "http://api.themoviedb.org/3/";
    private static final String api_key = "72508de530eba41fb571b4a5b10dd99b";

    private static OkHttpClient httpClient = new OkHttpClient();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }
}
