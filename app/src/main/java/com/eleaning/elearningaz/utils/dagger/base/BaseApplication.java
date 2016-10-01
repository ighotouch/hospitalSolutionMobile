package com.eleaning.elearningaz.utils.dagger.base;

import android.app.Application;

import com.eleaning.elearningaz.utils.dagger.component.BaseAppComponent;
import com.eleaning.elearningaz.utils.dagger.component.DaggerBaseAppComponent;
import com.eleaning.elearningaz.utils.dagger.module.BaseAppModule;
import com.eleaning.elearningaz.utils.dagger.module.NetModule;
import com.eleaning.elearningaz.utils.rest.RestConstants;


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
