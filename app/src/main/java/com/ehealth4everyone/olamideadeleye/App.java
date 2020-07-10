package com.ehealth4everyone.olamideadeleye;

import android.app.Application;

import com.ehealth4everyone.olamideadeleye.di.AppComponent;
import com.ehealth4everyone.olamideadeleye.di.AppModule;
import com.ehealth4everyone.olamideadeleye.di.DaggerAppComponent;

public class App extends Application {

    public AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this)).build();
    }
}
