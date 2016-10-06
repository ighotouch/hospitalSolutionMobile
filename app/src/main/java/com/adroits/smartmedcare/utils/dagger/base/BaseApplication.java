package com.adroits.smartmedcare.utils.dagger.base;

import android.app.Application;

import com.adroits.smartmedcare.utils.dagger.component.BaseAppComponent;
import com.adroits.smartmedcare.utils.dagger.component.DaggerBaseAppComponent;
import com.adroits.smartmedcare.utils.dagger.module.BaseAppModule;
import com.adroits.smartmedcare.utils.dagger.module.NetModule;
import com.adroits.smartmedcare.utils.rest.RestConstants;


public class BaseApplication extends Application {

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

}
