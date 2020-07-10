package com.ehealth4everyone.olamideadeleye.car_owners_fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ehealth4everyone.olamideadeleye.car_owners_model.CarOwner;
import com.ehealth4everyone.olamideadeleye.repo.CarOwnerRepo;

import java.util.List;

import javax.inject.Inject;

public class CarOwnerViewModel extends ViewModel {

    private CarOwnerRepo mCarOwnerRepo;
    private MutableLiveData<List<CarOwner>> mCarOwnersMutableLiveData = new MutableLiveData<>();
    public LiveData<List<CarOwner>> mCarOwnersLiveData = mCarOwnersMutableLiveData;

    @Inject
    public CarOwnerViewModel(CarOwnerRepo carOwnerRepo) {
        mCarOwnerRepo = carOwnerRepo;
        getCarOwnersList();
    }

    private void getCarOwnersList() {
        List<CarOwner> carOwners = mCarOwnerRepo.readCarOwnerData();
        mCarOwnersMutableLiveData.postValue(carOwners);
    }

}
