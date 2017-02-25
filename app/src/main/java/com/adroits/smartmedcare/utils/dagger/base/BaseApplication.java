package com.adroits.smartmedcare.utils.dagger.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.adroits.smartmedcare.utils.dagger.component.BaseAppComponent;
import com.adroits.smartmedcare.utils.dagger.component.DaggerBaseAppComponent;
import com.adroits.smartmedcare.utils.dagger.module.BaseAppModule;
import com.adroits.smartmedcare.utils.dagger.module.NetModule;
import com.adroits.smartmedcare.utils.rest.RestConstants;


public class BaseApplication extends MultiDexApplication {

    protected static BaseAppComponent component;


    @Override
    public void onCreate() {
        super.onCreate();
        buildComponentAndInject();
    }

    public void buildComponentAndInject() {
        component = DaggerComponentInitializer.init(this);
    }

    public static BaseAppComponent getComponent() {
        return component;
    }

    public final static class DaggerComponentInitializer {

        private DaggerComponentInitializer(){}

        public static BaseAppComponent init(BaseApplication app) {
            return DaggerBaseAppComponent.builder()
                    .baseAppModule(new BaseAppModule(app))
                    .netModule(new NetModule(RestConstants.BASE_URL))
                    .build();
        }
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
