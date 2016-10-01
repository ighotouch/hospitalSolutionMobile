package com.eleaning.elearningaz.application;


import com.eleaning.elearningaz.utils.dagger.base.BaseApplication;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;

public class ElearnApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        //For debugging to remove in production
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

        //initialiase Fresco
        Fresco.initialize(this);


    }


}
