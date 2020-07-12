package com.ehealth4everyone.olamideadeleye.car_owners_fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ehealth4everyone.olamideadeleye.car_owners_model.CarOwner;
import com.ehealth4everyone.olamideadeleye.filter_model.Filter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CarOwnerViewModel extends ViewModel {

    List<CarOwner> mCarOwners;
    private MutableLiveData<List<CarOwner>> mCarOwnersMutableLiveData = new MutableLiveData<>();
    public LiveData<List<CarOwner>> mCarOwnersLiveData = mCarOwnersMutableLiveData;
    private Disposable mDisposable;
    private MutableLiveData<Boolean> loadStateMutableLiveData = new MutableLiveData<>();
    public LiveData<Boolean> loadStateLiveData = loadStateMutableLiveData;
    private Filter mFilter;

    @Inject
    public CarOwnerViewModel(List<CarOwner> carOwners, Filter filter) {
        mCarOwners = carOwners;
        mFilter = filter;
        getFilteredCarOwnersList();
    }

    public List<CarOwner> filterCarOwnersList(List<CarOwner> carOwners, Filter filter) {
        List<CarOwner> filteredCarOwners = new ArrayList<>();


        //Go through each of the filters and apply them to the list of car owners
        for (CarOwner carOwner : carOwners) {
            if (carOwner.getCarModelYear() >= filter.getStartYear() && carOwner.getCarModelYear() <= filter.getEndYear()) {
                if (carOwner.getGender().toLowerCase().equals(filter.getGender()) || filter.getGender().equals("")) {
                    if (!filter.getCountries().isEmpty() && filter.getColors().isEmpty()) {
                        for (String country : filter.getCountries()) {
                            if (carOwner.getCountry().equals(country)) {
                                filteredCarOwners.add(carOwner);
                                //if this car owner is added to the filter list, break out of the loop
                                break;
                            }
                        }
                    }
                    else if (filter.getCountries().isEmpty() && !filter.getColors().isEmpty()) {
                        for (String colors : filter.getColors()) {
                            if (carOwner.getCarColor().equals(colors)) {
                                filteredCarOwners.add(carOwner);
                                //if this car owner is added to the filter list, break out of the loop
                                break;
                            }
                        }
                    }
                    else if (!filter.getCountries().isEmpty() && !filter.getColors().isEmpty()) {
                        countryLoop: for (String country : filter.getCountries()) {
                            if (carOwner.getCountry().equals(country)) {
                                for (String colors : filter.getColors()) {
                                    if (carOwner.getCarColor().equals(colors)) {
                                        filteredCarOwners.add(carOwner);
                                        //if this car owner is added to the filter list, break out of the loop
                                        break countryLoop;
                                    }
                                }
                                //if this car owner's country matches the filter's country but the
                                //car owner's car color doesn't match the filter's color, break out of the country loop
                                break;
                            }
                        }
                    }
                    else {
                        //If other filters match and the filter's countries and colors lists are both empty
                        //add this car owner to the filteredCarOwners list
                        filteredCarOwners.add(carOwner);
                    }
                }
            }
        }

        return filteredCarOwners;
    }

    public void getFilteredCarOwnersList() {

        //switch to a background thread
        final Scheduler scheduler = Schedulers.newThread();
        mDisposable = scheduler.scheduleDirect(new Runnable() {
            @Override
            public void run() {
                //show progress bar to indicate loading
                loadStateMutableLiveData.postValue(true);
                List<CarOwner> filteredCarOwners = filterCarOwnersList(mCarOwners, mFilter);
                //load complete hide progress bar
                loadStateMutableLiveData.postValue(false);
                mCarOwnersMutableLiveData.postValue(filteredCarOwners);
            }
        });
    }

    @Override
    protected void onCleared() {
        if (mDisposable != null && !mDisposable.isDisposed())
            mDisposable.dispose();
    }

}
