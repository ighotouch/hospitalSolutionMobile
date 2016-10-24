package com.adroits.smartmedcare.dbmodels;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Facility {

@SerializedName("title")
@Expose
private String title;
@SerializedName("latitude")
@Expose
private String latitude;
@SerializedName("longitude")
@Expose
private String longitude;
@SerializedName("facilityType")
@Expose
private String facilityType;
@SerializedName("address")
@Expose
private String address;
@SerializedName("lga")
@Expose
private Lga lga;

/**
* No args constructor for use in serialization
* 
*/
public Facility() {
}




/**
* 
* @param facilityType
* @param title
* @param address
* @param longitude
* @param latitude
* @param lga
*/
public Facility(String title, String latitude, String longitude, String facilityType, String address, Lga lga) {
this.title = title;
this.latitude = latitude;
this.longitude = longitude;
this.facilityType = facilityType;
this.address = address;
this.lga = lga;
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
* The facilityType
*/
public String getFacilityType() {
return facilityType;
}

/**
* 
* @param facilityType
* The facilityType
*/
public void setFacilityType(String facilityType) {
this.facilityType = facilityType;
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

/**
* 
* @return
* The lga
*/
public Lga getLga() {
return lga;
}

/**
* 
* @param lga
* The lga
*/
public void setLga(Lga lga) {
this.lga = lga;
}

}