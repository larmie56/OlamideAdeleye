package com.ehealth4everyone.olamideadeleye.car_owners_fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ehealth4everyone.olamideadeleye.car_owners_model.CarOwner;
import com.ehealth4everyone.olamideadeleye.repo.CarOwnerRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CarOwnerViewModel extends ViewModel {

    private CarOwnerRepo mCarOwnerRepo;
    private MutableLiveData<List<CarOwner>> mCarOwnersMutableLiveData = new MutableLiveData<>();
    public LiveData<List<CarOwner>> mCarOwnersLiveData = mCarOwnersMutableLiveData;
    private Disposable mDisposable;
    private MutableLiveData<Boolean> loadStateMutableLiveData = new MutableLiveData<>();
    public LiveData<Boolean> loadStateLiveData = loadStateMutableLiveData;

    @Inject
    public CarOwnerViewModel(CarOwnerRepo carOwnerRepo) {
        mCarOwnerRepo = carOwnerRepo;
    }

    public void getCarOwnersList() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                loadStateMutableLiveData.postValue(true);

                mDisposable = mCarOwnerRepo.readCarOwnerData()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnTerminate(new Action() {
                            @Override
                            public void run() throws Exception {
                                loadStateMutableLiveData.postValue(false);
                            }
                        })
                        .subscribe(new Consumer<List<CarOwner>>() {
                            @Override
                            public void accept(List<CarOwner> carOwners) throws Exception {
                                mCarOwnersMutableLiveData.postValue(carOwners);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        });
            }
        });
        thread.start();
    }

    @Override
    protected void onCleared() {
        if (mDisposable != null && !mDisposable.isDisposed())
            mDisposable.dispose();
    }
}
