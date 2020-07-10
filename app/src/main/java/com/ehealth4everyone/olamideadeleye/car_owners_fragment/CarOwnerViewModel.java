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
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CarOwnerViewModel extends ViewModel {

    private CarOwnerRepo mCarOwnerRepo;
    private MutableLiveData<List<CarOwner>> mCarOwnersMutableLiveData = new MutableLiveData<>();
    public LiveData<List<CarOwner>> mCarOwnersLiveData = mCarOwnersMutableLiveData;
    private Disposable mDisposable;

    @Inject
    public CarOwnerViewModel(CarOwnerRepo carOwnerRepo) {
        mCarOwnerRepo = carOwnerRepo;
        getCarOwnersList();
    }

    private void getCarOwnersList() {
        Single<List<CarOwner>> carOwners = mCarOwnerRepo.readCarOwnerData();
        mDisposable = carOwners.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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

    @Override
    protected void onCleared() {
        if (mDisposable != null && !mDisposable.isDisposed())
            mDisposable.dispose();
    }
}
