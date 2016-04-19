package com.digitalnatives.tabtest;

import java.util.List;

/**
 * Created by alexmcgonagle on 18/11/2015.
 */
public class Movie {

    public static int id;
    public static int runtime;
    public static String status;
    public static String name;
    public static String releaseDate;
    public static String imagePath;
    public static String description;
    public static String tagline;
    public List<Movie> movies;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Movie.id = id;
    }

    public static int getRuntime() {
        return runtime;
    }

    public static void setRuntime(int runtime) {
        Movie.runtime = runtime;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        Movie.status = status;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Movie.name = name;
    }

    public static String getReleaseDate() {
        return releaseDate;
    }

    public static void setReleaseDate(String releaseDate) {
        Movie.releaseDate = releaseDate;
    }

    public static String getImagePath() {
        return imagePath;
    }

    public static void setImagePath(String imagePath) {
        Movie.imagePath = imagePath;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        Movie.description = description;
    }

    public static String getTagline() {
        return tagline;
    }

    public static void setTagline(String tagline) {
        Movie.tagline = tagline;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }




    public Movie(String description, int id, String imagePath, String name, int runtime, String releaseDate, String status, String tagline) {
        this.description = description;
        this.id = id;
        this.imagePath = imagePath;
        this.name = name;
        this.runtime = runtime;
        this.releaseDate = releaseDate;
        this.status = status;
        this.tagline = tagline;
    }




  //  public void initializeData(){
  //      movies = new ArrayList<>();
  //      movies.add(new Movie("Bling blang bloom whats up", 234, "/7k9db7pJyTaVbz3G4eshGltivR1.jpg", "Monsters. Inc", 132, "2010-11-23", "released", "This is the tagline"));
  //      movies.add(new Movie("Bling number 2", 212, "/7k9db7pJyTaVbz3G4eshGltivR1.jpg", "Monsters. Inc", 132, "2010-11-23", "released", "This is the tagline"));
  //      movies.add(new Movie("Bling Number3", 233, "/7k9db7pJyTaVbz3G4eshGltivR1.jpg", "Monsters. Inc", 132, "2010-11-23", "released", "This is the tagline"));
  //  }





}
