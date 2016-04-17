package com.digitalnatives.tabtest;

/**
 * Created by ChickenNugget on 14/04/2016.
 */
public class ApiConfig {


    public static String BASE_URL = "https://pacific-escarpment-20911.herokuapp.com/";
    public static String LOGIN = BASE_URL + "login";
    public static String REGISTER = BASE_URL + "register";
    public static String GET_MOVIES = "user/movieswatched";
    public static String ADD_MOVIES = "user/movieswatched";
    public static String LOGOUT = "logout";


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


}
