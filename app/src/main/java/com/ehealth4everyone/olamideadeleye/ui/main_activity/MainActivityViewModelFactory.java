package com.ehealth4everyone.olamideadeleye.ui.main_activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ehealth4everyone.olamideadeleye.repo.CarOwnerRepo;
import com.ehealth4everyone.olamideadeleye.repo.FilterRepo;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

public class MainActivityViewModelFactory implements ViewModelProvider.Factory {

    CarOwnerRepo mCarOwnerRepo;
    FilterRepo mFilterRepo;

    @Inject
    public MainActivityViewModelFactory(CarOwnerRepo carOwnerRepo, FilterRepo filterRepo) {
        mCarOwnerRepo = carOwnerRepo;
        mFilterRepo = filterRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(CarOwnerRepo.class, FilterRepo.class)
                    .newInstance(mCarOwnerRepo, mFilterRepo);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }
}
