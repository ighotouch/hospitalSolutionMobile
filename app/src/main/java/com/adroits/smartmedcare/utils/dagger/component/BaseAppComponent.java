package com.adroits.smartmedcare.utils.dagger.component;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.LayoutInflater;

import com.adroits.smartmedcare.DetailActivity;
import com.adroits.smartmedcare.HospitalLocator;
import com.adroits.smartmedcare.MainActivity;
import com.adroits.smartmedcare.SplashActivity;
import com.adroits.smartmedcare.adapters.CategotyListAdapter;
import com.adroits.smartmedcare.utils.dagger.module.BaseAppModule;
import com.adroits.smartmedcare.utils.dagger.module.NetModule;
import com.adroits.smartmedcare.adapters.CourseListRecycleViewAdapter;
import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;
import dagger.Component;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {BaseAppModule.class, NetModule.class})
public interface BaseAppComponent {

    void inject(MainActivity mainActivity);
    void inject(SplashActivity splashActivity);
    void inject(HospitalLocator hospitalLocator);
    void inject(DetailActivity detailActivity);
    void inject(CategotyListAdapter categotyListAdapter);
    void inject(CourseListRecycleViewAdapter courseListRecycleViewAdapter);

    Context getContext();

    LayoutInflater getLayoutInflater();

    Resources getResources();

    @Named("PrefCache")
    SharedPreferences providePrefCacheSharedPreferences();

    Cache getHttpCache();

    Gson getGson();

    OkHttpClient getOkhttpClient();

    Retrofit getRetrofit();

}
