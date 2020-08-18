package com.ehealth4everyone.olamideadeleye.di;

import android.app.Application;
import android.content.Context;

import com.ehealth4everyone.olamideadeleye.repo.CarOwnerRepo;
import com.ehealth4everyone.olamideadeleye.repo.CarOwnerRepoImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public Context providesContext() {
        return mApplication;
    }
}
