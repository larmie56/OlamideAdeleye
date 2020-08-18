package com.ehealth4everyone.olamideadeleye.ui.main_activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ehealth4everyone.olamideadeleye.models.CarOwner;
import com.ehealth4everyone.olamideadeleye.models.Filter;
import com.ehealth4everyone.olamideadeleye.repo.CarOwnerRepo;
import com.ehealth4everyone.olamideadeleye.repo.FilterRepo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewModel extends ViewModel {

    private final CarOwnerRepo mCarOwnerRepo;
    private FilterRepo mFilterRepo;
    private CompositeDisposable mDisposable = new CompositeDisposable();
    private MutableLiveData<List<CarOwner>> mFilteredCarOwnersLiveData = new MutableLiveData<>();
    private MutableLiveData<List<CarOwner>> mCarOwnersLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> mainLoadStateLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> carOwnersLoadStateLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Filter>> mFilterLiveData = new MutableLiveData<>();

    public MainActivityViewModel(CarOwnerRepo carOwnerRepo, FilterRepo filterRepo) {
        mCarOwnerRepo = carOwnerRepo;
        mFilterRepo = filterRepo;
    }

    public void getCarOwnersFromRepo() {
        mainLoadStateLiveData.postValue(true);
        //switch to a background thread
        final Scheduler scheduler = Schedulers.newThread();
        mDisposable.add(scheduler.scheduleDirect(new Runnable() {
            @Override
            public void run() {
                //Load the car owners list from the .csv file on creation of MainActivity
                mDisposable.add(mCarOwnerRepo.getCarOwnersFromAsset()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<CarOwner>>() {
                            @Override
                            public void accept(List<CarOwner> carOwners) throws Exception {
                                mainLoadStateLiveData.postValue(false);
                                mCarOwnersLiveData.postValue(carOwners);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                throwable.printStackTrace();
                            }
                        }));

            }
        }));
    }

    public void getFiltersFromRepo() {
        mDisposable.add(mFilterRepo.getFiltersFromJsonString(mFilterRepo.getJsonStringFromAsset())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Filter>>() {
                    @Override
                    public void accept(List<Filter> filters) throws Exception {
                        mFilterLiveData.postValue(filters);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                }));

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
                    } else if (filter.getCountries().isEmpty() && !filter.getColors().isEmpty()) {
                        for (String colors : filter.getColors()) {
                            if (carOwner.getCarColor().equals(colors)) {
                                filteredCarOwners.add(carOwner);
                                //if this car owner is added to the filter list, break out of the loop
                                break;
                            }
                        }
                    } else if (!filter.getCountries().isEmpty() && !filter.getColors().isEmpty()) {
                        countryLoop:
                        for (String country : filter.getCountries()) {
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
                    } else {
                        //If other filters match and the filter's countries and colors lists are both empty
                        //add this car owner to the filteredCarOwners list
                        filteredCarOwners.add(carOwner);
                    }
                }
            }
        }

        return filteredCarOwners;
    }

    public void updateFilteredCarOwnersList(List<CarOwner> carOwners, Filter selectedFilter) {
        //switch to a background thread
        final Scheduler scheduler = Schedulers.newThread();
        mDisposable.add(scheduler.scheduleDirect(new Runnable() {
            @Override
            public void run() {
                //show progress bar to indicate loading
                carOwnersLoadStateLiveData.postValue(true);
                List<CarOwner> filteredCarOwners = filterCarOwnersList(carOwners, selectedFilter);
                //load complete hide progress bar
                carOwnersLoadStateLiveData.postValue(false);
                mFilteredCarOwnersLiveData.postValue(filteredCarOwners);
            }
        }));
    }

    @Override
    protected void onCleared() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    public LiveData<List<Filter>> getFilters() {
        return mFilterLiveData;
    }

    public LiveData<Boolean> getMainLoadState() { return mainLoadStateLiveData;}

    public LiveData<List<CarOwner>> getCarOwners() { return mCarOwnersLiveData;}

    public LiveData<Boolean> getCarOwnersLoadState() { return carOwnersLoadStateLiveData;}

    public LiveData<List<CarOwner>> getFilteredCarOwners() { return mFilteredCarOwnersLiveData;}
}
