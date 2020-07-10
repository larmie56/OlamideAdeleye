package com.ehealth4everyone.olamideadeleye.di;

import com.ehealth4everyone.olamideadeleye.repo.CarOwnerRepo;
import com.ehealth4everyone.olamideadeleye.repo.CarOwnerRepoImpl;

import dagger.Binds;
import dagger.Module;

@Module
public interface CarOwnerBinder {

    @CarOwnerScope
    @Binds
    CarOwnerRepo providesCarOwnerRepo(CarOwnerRepoImpl carOwnerRepo);

}
