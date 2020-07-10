package com.ehealth4everyone.olamideadeleye.car_owners_fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ehealth4everyone.olamideadeleye.repo.CarOwnerRepo;

import java.lang.reflect.InvocationTargetException;

public class CarOwnerViewModelFactory implements ViewModelProvider.Factory {

    CarOwnerRepo mCarOwnerRepo;

    public CarOwnerViewModelFactory(CarOwnerRepo carOwnerRepo) {
        mCarOwnerRepo = carOwnerRepo;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(CarOwnerRepo.class).newInstance(mCarOwnerRepo);

        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
