package com.eleaning.elearningaz.utils.dagger.component;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.LayoutInflater;

import com.eleaning.elearningaz.MainActivity;
import com.eleaning.elearningaz.utils.dagger.module.BaseAppModule;
import com.eleaning.elearningaz.utils.dagger.module.NetModule;
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

    Context getContext();

    LayoutInflater getLayoutInflater();

    Resources getResources();

    SharedPreferences getSharedPreferences();

    Cache getHttpCache();

    Gson getGson();

    OkHttpClient getOkhttpClient();

    Retrofit getRetrofit();

}
