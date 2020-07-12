package com.ehealth4everyone.olamideadeleye.main_activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ehealth4everyone.olamideadeleye.repo.CarOwnerRepoImpl;

import java.lang.reflect.InvocationTargetException;

public class MainActivityViewModelFactory implements ViewModelProvider.Factory {

    CarOwnerRepoImpl mCarOwnerRepo;

    public MainActivityViewModelFactory(CarOwnerRepoImpl carOwnerRepo) {
        mCarOwnerRepo = carOwnerRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(CarOwnerRepoImpl.class).newInstance(mCarOwnerRepo);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }
}
