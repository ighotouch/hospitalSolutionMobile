package com.adroits.smartmedcare.dbmodels;

/**
 * Created by Access on 6/28/2016.
 */
public class Facilities {
    String title;
    String thumbnail;
    String location;

    public Facilities(String title, String thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;

    }

    public Facilities(String title, String thumbnail, String location) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
