package com.adroits.smartmedcare.utils.rest.service;


import com.adroits.smartmedcare.dbmodels.EmergencyPostObject;
import com.adroits.smartmedcare.utils.rest.model.Information;
import com.adroits.smartmedcare.utils.rest.model.user.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SmartMedCareService {

    @GET("api/getAllInformation")
    Call<List<Information>> getInformations();
    @GET("api/getEvents")
    Call<List<Information>> getEvents();
    @POST("api/postEmergency")
    Call<String> postEmergency(@Body EmergencyPostObject emergencyPostObject);
    @GET("api/getTopics")
    Call<List<Information>> getTopicOfTheWeek();
    @GET("api/getOfficials")
    Call<List<Information>> getOfficials();
}
