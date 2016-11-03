package com.adroits.smartmedcare.utils;

import android.content.SharedPreferences;


import com.adroits.smartmedcare.application.ElearnApplication;

import javax.inject.Inject;
import javax.inject.Named;

public class PrefCache {

    public static final String LATITUDE = "lat";
    public static final String LONGITUDE = "long";
    public static final String LOCATION = "location";

    @Inject
    @Named("PrefCache")
    SharedPreferences prefs = ElearnApplication.getComponent().providePrefCacheSharedPreferences();

    @Inject
    public PrefCache(){}

    private void setLongPrefrences(String prefName, float Value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat(prefName, Value);
        editor.commit();
    }

    private void setStringPrefrences(String prefName, String Value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(prefName, Value);
        editor.commit();
    }

    private String getStringPrefrences(String prefName) {
        if (prefs.contains(prefName))
            return prefs.getString(prefName, null);
        else
            return "1234";
    }

    private float getFloatPrefrences(String prefName) {
        if (prefs.contains(prefName))
            return prefs.getFloat(prefName, 0);
        else
            return 0;
    }

    public void saveGeolocation(String location){
        setStringPrefrences(LOCATION,location);

        String c = null;
    }

    public String getGeoLocation(){
        return getStringPrefrences(LOCATION);
    }

    public float getLatitude(){
        return getFloatPrefrences(LATITUDE);
    }

    public float getLongitude(){
        return getFloatPrefrences(LONGITUDE);
    }

    public void saveCoordinates(float latitude,float longitude){
        setLongPrefrences(LATITUDE, latitude);
        setLongPrefrences(LONGITUDE, longitude);
    }


    
    public void clearPixAndUserId(){
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }
}