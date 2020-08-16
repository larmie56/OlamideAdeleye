package com.ehealth4everyone.olamideadeleye.ui.car_owners_fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ehealth4everyone.olamideadeleye.models.CarOwner;
import com.ehealth4everyone.olamideadeleye.models.Filter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CarOwnerViewModelFactory implements ViewModelProvider.Factory {

    List<CarOwner> mCarOwners;
    Filter mFilter;

    public CarOwnerViewModelFactory(List<CarOwner> carOwners, Filter filter) {
        mCarOwners = carOwners;
        mFilter = filter;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(List.class, Filter.class).newInstance(mCarOwners, mFilter);

        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
