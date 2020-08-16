package com.ehealth4everyone.olamideadeleye.ui.main_activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ehealth4everyone.olamideadeleye.models.CarOwner;
import com.ehealth4everyone.olamideadeleye.models.Filter;
import com.ehealth4everyone.olamideadeleye.repo.CarOwnerRepoImpl;
import com.ehealth4everyone.olamideadeleye.repo.FilterRepo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewModel extends ViewModel {

    private final CarOwnerRepoImpl mCarOwnerRepo;
    private Disposable newThreadDisposable;
    private Disposable ioThreadDisposable;
    private MutableLiveData<List<CarOwner>> mFilteredCarOwnersLiveData = new MutableLiveData<>();
    private MutableLiveData<List<CarOwner>> mCarOwnersMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loadStateMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> carOwnersLoadStateLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Filter>> mFilterMutableLiveData = new MutableLiveData<>();
    private Filter mSelectedFilter;

    private List<CarOwner> mCarOwners;

    private FilterRepo mFilterRepo;
    private Disposable mDisposable;

    public MainActivityViewModel(CarOwnerRepoImpl carOwnerRepo, FilterRepo filterRepo) {
        mCarOwnerRepo = carOwnerRepo;
        mFilterRepo = filterRepo;
        loadCarOwnersData();
        getFilterList();
    }

    public void loadCarOwnersData() {
        loadStateMutableLiveData.postValue(true);
        //switch to a background thread
        final Scheduler scheduler = Schedulers.newThread();
        newThreadDisposable = scheduler.scheduleDirect(new Runnable() {
            @Override
            public void run() {
                //Load the car owners list from the .csv file on creation of MainActivity
                ioThreadDisposable = mCarOwnerRepo.readCarOwnerData()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<CarOwner>>() {
                            @Override
                            public void accept(List<CarOwner> carOwners) throws Exception {
                                mCarOwners = carOwners;
                                loadStateMutableLiveData.postValue(false);
                                mCarOwnersMutableLiveData.postValue(carOwners);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        });

            }
        });
    }

    public void getFilterList() {
        Single<List<Filter>> filters = mFilterRepo.getFiltersFromJsonString(mFilterRepo.getJsonStringFromAsset());


        mDisposable = filters.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Filter>>() {
                    @Override
                    public void accept(List<Filter> filters) throws Exception {
                        mFilterMutableLiveData.postValue(filters);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

    }

    public void setSelectedFilter(Filter filter) {
        mSelectedFilter = filter;
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

    public void getFilteredCarOwnersList() {
        //switch to a background thread
        final Scheduler scheduler = Schedulers.newThread();
        mDisposable = scheduler.scheduleDirect(new Runnable() {
            @Override
            public void run() {
                //show progress bar to indicate loading
                carOwnersLoadStateLiveData.postValue(true);
                List<CarOwner> filteredCarOwners = filterCarOwnersList(mCarOwners, mSelectedFilter);
                //load complete hide progress bar
                carOwnersLoadStateLiveData.postValue(false);
                mFilteredCarOwnersLiveData.postValue(filteredCarOwners);
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (newThreadDisposable != null && !newThreadDisposable.isDisposed()) {
            newThreadDisposable.dispose();
        }
        if (ioThreadDisposable != null && !ioThreadDisposable.isDisposed()) {
            ioThreadDisposable.dispose();
        }
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    public LiveData<List<Filter>> getFilters() {
        return mFilterMutableLiveData;
    }

    public LiveData<Boolean> getLoadState() { return loadStateMutableLiveData;}

    public LiveData<List<CarOwner>> getCarOwners() { return mCarOwnersMutableLiveData;}

    public LiveData<Boolean> getCarOwnersLoadState() { return carOwnersLoadStateLiveData;}

    public LiveData<List<CarOwner>> getFilteredCarOwners() { return mFilteredCarOwnersLiveData;}
}
