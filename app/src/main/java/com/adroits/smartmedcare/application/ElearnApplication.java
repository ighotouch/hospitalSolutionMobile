package com.adroits.smartmedcare.application;


import com.adroits.smartmedcare.utils.dagger.base.BaseApplication;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;

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
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);


    }


}
