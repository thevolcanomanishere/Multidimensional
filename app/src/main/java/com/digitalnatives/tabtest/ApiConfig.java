package com.digitalnatives.tabtest;

/**
 * Created by ChickenNugget on 14/04/2016.
 */
public class ApiConfig {


    private static boolean devMode = false;

    public static String BASE_URL = "https://pacific-escarpment-20911.herokuapp.com/";
    public static String DEV_URL = "https://nodejsandroidserver-thevolcanomanishere.c9users.io/";
    public static String LOGIN = "login";
    public static String REGISTER = "register";
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

    public static String getBaseTmdbMovieId() {
        return BASE_TMDB_MOVIE_ID;
    }

    public static void setBaseTmdbMovieId(String baseTmdbMovieId) {
        BASE_TMDB_MOVIE_ID = baseTmdbMovieId;
    }

    private static String BASE_TMDB_MOVIE_ID = "http://api.themoviedb.org/3/movie/";
    private static String TMDB_KEY = "?api_key=72508de530eba41fb571b4a5b10dd99b";


    public static String getLOGIN() {
        if(devMode){
            String DevModeUrl = DEV_URL + LOGIN;
            return DevModeUrl;
        } else {
            String URL = BASE_URL + LOGIN;
            return URL;
        }
    }

    public static String getREGISTER() {
        if(devMode){
            String DevModeUrl = DEV_URL + REGISTER;
            return DevModeUrl;
        } else{
            String ProdMode = BASE_URL + REGISTER;
            return ProdMode;
        }
    }

    public static String getGET_MOVIES() {
        if(devMode){
            String DevMode = DEV_URL + GET_MOVIES;
            return DevMode;
        } else {
            String ProdMode = BASE_URL + GET_MOVIES;
            return ProdMode;
        }
    }

    public static String getADD_MOVIES() {
        if(devMode){
            String DevMode = DEV_URL + ADD_MOVIES;
            return DevMode;
        } else {
            String ProdMode = BASE_URL + ADD_MOVIES;
            return ProdMode;
        }
    }

    public static String getLOGOUT() {
        if(devMode){
            String DevModeUrl = DEV_URL + LOGOUT;
            return DevModeUrl;
        } else {
            String URL = BASE_URL + LOGOUT;
            return URL;
        }
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
