package com.adroits.smartmedcare.utils.dagger.component;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.LayoutInflater;

import com.adroits.smartmedcare.MainActivity;
import com.adroits.smartmedcare.adapters.CategotyListAdapter;
import com.adroits.smartmedcare.utils.dagger.module.BaseAppModule;
import com.adroits.smartmedcare.utils.dagger.module.NetModule;
import com.adroits.smartmedcare.adapters.CourseListRecycleViewAdapter;
import com.google.gson.Gson;

import javax.inject.Singleton;
import dagger.Component;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {BaseAppModule.class, NetModule.class})
public interface BaseAppComponent {

    void inject(MainActivity mainActivity);
    void inject(CategotyListAdapter categotyListAdapter);
    void inject(CourseListRecycleViewAdapter courseListRecycleViewAdapter);

    Context getContext();

    LayoutInflater getLayoutInflater();

    Resources getResources();

    SharedPreferences getSharedPreferences();

    Cache getHttpCache();

    Gson getGson();

    OkHttpClient getOkhttpClient();

    Retrofit getRetrofit();

}
