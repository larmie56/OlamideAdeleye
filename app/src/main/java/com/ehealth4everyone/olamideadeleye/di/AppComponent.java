package com.ehealth4everyone.olamideadeleye.di;

import com.ehealth4everyone.olamideadeleye.ui.main_activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, FilterListBinder.class})
public interface AppComponent {

    void injectMainActivity(MainActivity mainActivity);
    CarOwnerComponent plusCarOwnerFragment();
}
