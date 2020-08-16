package com.ehealth4everyone.olamideadeleye.ui.main_activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ehealth4everyone.olamideadeleye.repo.CarOwnerRepoImpl;
import com.ehealth4everyone.olamideadeleye.repo.FilterRepo;

import java.lang.reflect.InvocationTargetException;

public class MainActivityViewModelFactory implements ViewModelProvider.Factory {

    CarOwnerRepoImpl mCarOwnerRepo;
    FilterRepo mFilterRepo;

    public MainActivityViewModelFactory(CarOwnerRepoImpl carOwnerRepo, FilterRepo filterRepo) {
        mCarOwnerRepo = carOwnerRepo;
        mFilterRepo = filterRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(CarOwnerRepoImpl.class, FilterRepo.class)
                    .newInstance(mCarOwnerRepo, mFilterRepo);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }
}
