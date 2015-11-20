package com.digitalnatives.tabtest;

/**
 * Created by alexmcgonagle on 19/11/2015.
 */
public class LibraryItem {

    private String description;
    private int id;
    private String image_url;
    private String name;
    private int runtime;
    private String releaseDate;
    private String tagline;
    private String rateDate;



    public LibraryItem(String description, int id, String image_url, String name, String releaseDate, int runtime, String tagline, String rateDate) {
        this.description = description;
        this.id = id;
        this.image_url = image_url;
        this.name = name;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.tagline = tagline;
        this.rateDate = rateDate;
    }

    public String getRateDate() {
        return rateDate;
    }

    public void setRateDate(String rateDate) {
        this.rateDate = rateDate;
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

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

}


