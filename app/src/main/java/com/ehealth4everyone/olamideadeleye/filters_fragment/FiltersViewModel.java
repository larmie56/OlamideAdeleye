package com.ehealth4everyone.olamideadeleye.filters_fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ehealth4everyone.olamideadeleye.filter_model.Filter;
import com.ehealth4everyone.olamideadeleye.repo.FilterRepo;

import java.util.List;

public class FiltersViewModel extends ViewModel {
    private MutableLiveData<List<Filter>> mFilterMutableLiveData = new MutableLiveData<>();

    private LiveData<List<Filter>> mFilterLiveData = mFilterMutableLiveData;

    FilterRepo mFilterRepo;

    public FiltersViewModel(FilterRepo filterRepo) {
        mFilterRepo = filterRepo;
    }

    public void getFilterList() {
        List<Filter> filters = mFilterRepo.getFiltersFromJsonString(mFilterRepo.getJsonStringFromAsset());

        mFilterMutableLiveData.postValue(filters);
    }

    public LiveData<List<Filter>> getFiltersLiveData() {
        return mFilterLiveData;
    }
}
