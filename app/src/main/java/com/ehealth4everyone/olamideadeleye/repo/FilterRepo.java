package com.ehealth4everyone.olamideadeleye.repo;

import android.content.Context;

import com.ehealth4everyone.olamideadeleye.filter_model.Filter;

import java.util.List;

public interface FilterRepo {

    String getJsonStringFromAsset();
    List<Filter> getFiltersFromJsonString(String string);
}
