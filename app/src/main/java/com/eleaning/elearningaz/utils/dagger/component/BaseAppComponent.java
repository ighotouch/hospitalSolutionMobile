package com.eleaning.elearningaz.utils.dagger.component;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;

import com.eleaning.elearningaz.MainActivity;
import com.eleaning.elearningaz.utils.dagger.module.BaseAppModule;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {BaseAppModule.class})
public interface BaseAppComponent {


    void inject(MainActivity mainActivity);

    Context getContext();

    LayoutInflater getLayoutInflater();

    Resources getResources();

}
