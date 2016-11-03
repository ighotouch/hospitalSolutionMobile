package com.adroits.smartmedcare.utils.dagger.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.LayoutInflater;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BaseAppModule {
    private static final String PREFCACHE_PREF_NAME = "com.adroits.smartmedcare.Cache";
    private static final String SESSION_PREF_NAME = "com.adroits.smartmedcare.SavedInfo";
    private final Application application;

    public BaseAppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    Resources provideResources(){
        return application.getResources();
    }


    @Provides
    @Singleton
    LayoutInflater provideLayoutInflater(){
        return (LayoutInflater) application.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Provides
    @Singleton
    @Named("PrefCache")
    SharedPreferences providePrefCacheSharedPreferences(){ return application.getSharedPreferences(PREFCACHE_PREF_NAME, Context.MODE_PRIVATE); }

    @Provides
    @Singleton
    @Named("Session")
    SharedPreferences provideSessionSharedPreferences(){ return application.getSharedPreferences(SESSION_PREF_NAME, Context.MODE_PRIVATE); }


}
