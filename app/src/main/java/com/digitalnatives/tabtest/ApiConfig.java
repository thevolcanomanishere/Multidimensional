package com.digitalnatives.tabtest;

/**
 * Created by ChickenNugget on 14/04/2016.
 */
public class ApiConfig {


    //server api
    public static String BASE_URL = "https://pacific-escarpment-20911.herokuapp.com/";
    public static String LOGIN = BASE_URL + "login";
    public static String REGISTER = BASE_URL + "register";
    public static String GET_MOVIES = "user/movieswatched";
    public static String ADD_MOVIES = "user/movieswatched";
    public static String LOGOUT = "logout";

    public static String getBaseTmdbImageUrl() {
        return BASE_TMDB_IMAGE_URL;
    }

    public static void setBaseTmdbImageUrl(String baseTmdbImageUrl) {
        BASE_TMDB_IMAGE_URL = baseTmdbImageUrl;
    }

    public static String getTmdbKey() {
        return TMDB_KEY;
    }

    public static void setTmdbKey(String tmdbKey) {
        TMDB_KEY = tmdbKey;
    }

    //tmdb api
    private static String BASE_TMDB_IMAGE_URL
            = "http://image.tmdb.org/t/p/w185";


    private static String BASE_TMDB_INFO_URL = "http://api.themoviedb.org/3/search/movie";
    private static String TMDB_KEY = "?api_key=72508de530eba41fb571b4a5b10dd99b";


    public static String getLOGIN() {
        return LOGIN;
    }

    public static String getREGISTER() {
        return REGISTER;
    }

    public static String getGET_MOVIES() {
        return GET_MOVIES;
    }

    public static String getADD_MOVIES() {
        return ADD_MOVIES;
    }

    public static String getLOGOUT() {
        return LOGOUT;
    }


    public static String getBASE_URL() {
        return BASE_URL;
    }

    public static String getBaseTmdbInfoUrl() {
        return BASE_TMDB_INFO_URL;
    }

    public static void setBaseTmdbInfoUrl(String baseTmdbInfoUrl) {
        BASE_TMDB_INFO_URL = baseTmdbInfoUrl;
    }



}
