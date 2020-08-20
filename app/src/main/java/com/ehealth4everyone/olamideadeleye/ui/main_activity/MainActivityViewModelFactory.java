package com.ehealth4everyone.olamideadeleye.ui.main_activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ehealth4everyone.olamideadeleye.repo.CarOwnerRepo;
import com.ehealth4everyone.olamideadeleye.repo.FilterRepo;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;
import javax.inject.Provider;

public class MainActivityViewModelFactory implements ViewModelProvider.Factory {

    //Using providers to defer object creation to when their creation is necessary
    Provider<CarOwnerRepo> mCarOwnerRepoProvider;
    Provider<FilterRepo> mFilterRepoProvider;

    @Inject
    public MainActivityViewModelFactory(Provider<CarOwnerRepo> carOwnerRepoProvider, Provider<FilterRepo> filterRepoProvider) {
        mCarOwnerRepoProvider = carOwnerRepoProvider;
        mFilterRepoProvider = filterRepoProvider;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(CarOwnerRepo.class, FilterRepo.class)
                    .newInstance(mCarOwnerRepoProvider.get(), mFilterRepoProvider.get());
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }
}
