package com.ehealth4everyone.olamideadeleye.di;

import com.ehealth4everyone.olamideadeleye.repo.FilterRepo;
import com.ehealth4everyone.olamideadeleye.repo.FilterRepoImpl;

import dagger.Binds;
import dagger.Module;

@Module
public interface FilterListBinder {

    @Binds
    FilterRepo providersFilterRepo(FilterRepoImpl filterRepoImpl);
}
