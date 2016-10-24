package com.adroits.smartmedcare.dbmodels;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Lga {

@SerializedName("title")
@Expose
private String title;
@SerializedName("latitude")
@Expose
private String latitude;
@SerializedName("longitude")
@Expose
private String longitude;
@SerializedName("address")
@Expose
private String address;

/**
* No args constructor for use in serialization
* 
*/
public Lga() {
}

    public Lga(String title) {
        this.title = title;
    }

/**
* 
* @param title
* @param address
* @param longitude
* @param latitude
*/
public Lga(String title, String latitude, String longitude, String address) {
this.title = title;
this.latitude = latitude;
this.longitude = longitude;
this.address = address;
}

/**
* 
* @return
* The title
*/
public String getTitle() {
return title;
}

/**
* 
* @param title
* The title
*/
public void setTitle(String title) {
this.title = title;
}

/**
* 
* @return
* The latitude
*/
public String getLatitude() {
return latitude;
}

/**
* 
* @param latitude
* The latitude
*/
public void setLatitude(String latitude) {
this.latitude = latitude;
}

/**
* 
* @return
* The longitude
*/
public String getLongitude() {
return longitude;
}

/**
* 
* @param longitude
* The longitude
*/
public void setLongitude(String longitude) {
this.longitude = longitude;
}

/**
* 
* @return
* The address
*/
public String getAddress() {
return address;
}

/**
* 
* @param address
* The address
*/
public void setAddress(String address) {
this.address = address;
}

}