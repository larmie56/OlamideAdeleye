package com.ehealth4everyone.olamideadeleye.di;

import com.ehealth4everyone.olamideadeleye.repo.CarOwnerRepo;
import com.ehealth4everyone.olamideadeleye.repo.CarOwnerRepoImpl;
import com.ehealth4everyone.olamideadeleye.repo.FilterRepo;
import com.ehealth4everyone.olamideadeleye.repo.FilterRepoImpl;

import dagger.Binds;
import dagger.Module;

@Module
public interface MainActivityBinders {

    @Binds
    FilterRepo providersFilterRepo(FilterRepoImpl filterRepoImpl);

    @Binds
    CarOwnerRepo providesCarOwnerRepo(CarOwnerRepoImpl carOwnerRepoImpl);
}
