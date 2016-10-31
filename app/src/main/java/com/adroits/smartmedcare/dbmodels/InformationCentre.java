package com.adroits.smartmedcare.dbmodels;

/**
 * Created by Access on 6/28/2016.
 */
public class InformationCentre {
    String title;
    String thumbnail;

    public InformationCentre(String title, String thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
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
}
