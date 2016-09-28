package com.eleaning.elearningaz.utils.dagger.module;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BaseAppModule {

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
    Resources provideResources(){
        return application.getResources();
    }


    @Provides
    @Singleton
    LayoutInflater provideLayoutInflater(){
        return (LayoutInflater) application.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

}
