package com.digitalnatives.tabtest;

import java.util.Date;

/**
 * Created by alexmcgonagle on 19/11/2015.
 */
public class LibraryItem {

    private String description;
    private int id;
    private String image_url;
    private String name;
    private String releaseDate;
    private int heartRateAverage;



    public LibraryItem(String description, String image_url, String name, String releaseDate, int heartRateAverage) {
        this.description = description;
//        this.id = id;
        this.image_url = image_url;
        this.name = name;
        this.releaseDate = releaseDate;
        this.heartRateAverage = heartRateAverage;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getHeartRateAverage(){
        return heartRateAverage;
    }

    public void setHeartRateAverage(int heartRateAverage){
        this.heartRateAverage = heartRateAverage;
    }


}


