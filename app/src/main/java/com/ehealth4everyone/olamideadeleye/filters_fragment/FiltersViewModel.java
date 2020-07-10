package com.ehealth4everyone.olamideadeleye.filters_fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ehealth4everyone.olamideadeleye.filter_model.Filter;
import com.ehealth4everyone.olamideadeleye.repo.FilterRepo;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FiltersViewModel extends ViewModel {
    private MutableLiveData<List<Filter>> mFilterMutableLiveData = new MutableLiveData<>();

    private LiveData<List<Filter>> mFilterLiveData = mFilterMutableLiveData;

    private FilterRepo mFilterRepo;
    private Disposable mDisposable;

    public FiltersViewModel(FilterRepo filterRepo) {
        mFilterRepo = filterRepo;
        getFilterList();
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

    @Override
    protected void onCleared() {
        if (mDisposable != null && !mDisposable.isDisposed())
            mDisposable.dispose();
    }

    public LiveData<List<Filter>> getFiltersLiveData() {
        return mFilterLiveData;
    }
}
