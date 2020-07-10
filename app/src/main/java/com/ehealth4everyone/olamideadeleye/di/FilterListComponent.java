package com.ehealth4everyone.olamideadeleye.di;

import com.ehealth4everyone.olamideadeleye.filters_fragment.FilterListFragemt;

import dagger.Subcomponent;

@FilterListScope
@Subcomponent(modules = FilterListBinder.class)
public interface FilterListComponent {

    void injectFilterListFragment(FilterListFragemt filterListFragemt);
}
