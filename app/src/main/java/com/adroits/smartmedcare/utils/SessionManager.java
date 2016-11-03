package com.adroits.smartmedcare.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;



import javax.inject.Inject;
import javax.inject.Named;

public class SessionManager {

    @Inject
    PrefCache prefCache;

    @Inject
    Context appContext;

    @Inject
    @Named("Session")
    SharedPreferences pref;



    //to confirm login
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String LOCATION = "location";

    @Inject
    public SessionManager() { }

    /**
     * Create login session with login pref model
     */
    public void setCoordinates(float latitude,float longitude) {
        SharedPreferences.Editor editor = pref.edit();
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        prefCache.saveCoordinates(latitude,longitude);
        editor.commit();


    }

    public void saveGeolocation(String location) {
        prefCache.saveGeolocation(location);
    }

    public String getGeoLocation(){
        return prefCache.getGeoLocation();
    }

    public float getLatitude(){
        return prefCache.getLatitude();
    }
    public float getLongitude(){
        return prefCache.getLongitude();
    }









}