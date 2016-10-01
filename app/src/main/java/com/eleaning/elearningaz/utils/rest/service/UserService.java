package com.eleaning.elearningaz.utils.rest.service;


import com.eleaning.elearningaz.utils.rest.model.user.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {

    @GET("users")
    Call<List<User>> getUsersList();

}
