package com.adroits.smartmedcare.dbmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by imatthew on 2/18/2017.
 */

public class EmergencyPostObject {
    @SerializedName("text")
    @Expose
    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
