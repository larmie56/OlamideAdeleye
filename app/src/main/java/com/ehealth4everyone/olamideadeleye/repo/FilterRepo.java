package com.ehealth4everyone.olamideadeleye.repo;

import com.ehealth4everyone.olamideadeleye.models.Filter;

import java.util.List;

import io.reactivex.Single;

public interface FilterRepo {

    String getJsonStringFromAsset(String fileName);
    Single<List<Filter>> getFiltersFromJsonString(String string);
}
