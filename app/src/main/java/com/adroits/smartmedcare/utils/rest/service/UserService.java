package com.adroits.smartmedcare.utils.rest.service;


import com.adroits.smartmedcare.utils.rest.model.user.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    @GET("users")
    Call<List<User>> getUsersList();

}
