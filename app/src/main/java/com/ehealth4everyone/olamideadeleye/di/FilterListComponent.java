package com.ehealth4everyone.olamideadeleye.di;

import com.ehealth4everyone.olamideadeleye.ui.filters_fragment.FilterListFragment;

import dagger.Subcomponent;

@FilterListScope
@Subcomponent(modules = FilterListBinder.class)
public interface FilterListComponent {

    void injectFilterListFragment(FilterListFragment filterListFragment);
}
