package com.ehealth4everyone.olamideadeleye.main_activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ehealth4everyone.olamideadeleye.car_owners_model.CarOwner;
import com.ehealth4everyone.olamideadeleye.repo.CarOwnerRepoImpl;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewModel extends ViewModel {

    private final CarOwnerRepoImpl mCarOwnerRepo;
    Disposable newThreadDisposable;
    Disposable ioThreadDisposable;
    private MutableLiveData<List<CarOwner>> mCarOwnersMutableLiveData = new MutableLiveData<>();
    public LiveData<List<CarOwner>> mCarOwnersLiveData = mCarOwnersMutableLiveData;
    private MutableLiveData<Boolean> loadStateMutableLiveData = new MutableLiveData<>();
    public LiveData<Boolean> loadStateLiveData = loadStateMutableLiveData;

    public MainActivityViewModel(CarOwnerRepoImpl carOwnerRepo) {
        mCarOwnerRepo = carOwnerRepo;
        loadCarOwnersData();
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

    @Override
    protected void onCleared() {
        super.onCleared();
        if (newThreadDisposable != null && !newThreadDisposable.isDisposed())
            newThreadDisposable.dispose();
        if (ioThreadDisposable != null && !ioThreadDisposable.isDisposed())
            ioThreadDisposable.dispose();
    }
}
