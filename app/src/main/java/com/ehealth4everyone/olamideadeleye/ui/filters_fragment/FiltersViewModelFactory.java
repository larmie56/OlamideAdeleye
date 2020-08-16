package com.ehealth4everyone.olamideadeleye.ui.filters_fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ehealth4everyone.olamideadeleye.repo.FilterRepo;

import java.lang.reflect.InvocationTargetException;

public class FiltersViewModelFactory implements ViewModelProvider.Factory {
    private FilterRepo mFilterRepo;

    public FiltersViewModelFactory(FilterRepo filterRepo) {
        mFilterRepo = filterRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(FilterRepo.class).newInstance(mFilterRepo);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
