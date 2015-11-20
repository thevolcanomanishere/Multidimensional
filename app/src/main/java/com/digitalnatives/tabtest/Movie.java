package com.digitalnatives.tabtest;

import java.util.List;

/**
 * Created by alexmcgonagle on 18/11/2015.
 */
public class Movie {

    public int id;
    public int runtime;
    public String status;
    public String name;
    public String releaseDate;
    public String imagePath;
    public String description;
    public String tagline;
    public List<Movie> movies;


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


    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getName() {
        return name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }



  //  public void initializeData(){
  //      movies = new ArrayList<>();
  //      movies.add(new Movie("Bling blang bloom whats up", 234, "/7k9db7pJyTaVbz3G4eshGltivR1.jpg", "Monsters. Inc", 132, "2010-11-23", "released", "This is the tagline"));
  //      movies.add(new Movie("Bling number 2", 212, "/7k9db7pJyTaVbz3G4eshGltivR1.jpg", "Monsters. Inc", 132, "2010-11-23", "released", "This is the tagline"));
  //      movies.add(new Movie("Bling Number3", 233, "/7k9db7pJyTaVbz3G4eshGltivR1.jpg", "Monsters. Inc", 132, "2010-11-23", "released", "This is the tagline"));
  //  }





}
