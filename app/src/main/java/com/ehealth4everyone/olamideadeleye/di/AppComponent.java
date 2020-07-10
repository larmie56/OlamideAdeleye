package com.ehealth4everyone.olamideadeleye.di;

import com.ehealth4everyone.olamideadeleye.filters_fragment.FilterListFragemt;
import com.ehealth4everyone.olamideadeleye.main_activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void injectMainActivity(MainActivity mainActivity);
    void injectFilterListFragment(FilterListFragemt filterListFragemt);
}
