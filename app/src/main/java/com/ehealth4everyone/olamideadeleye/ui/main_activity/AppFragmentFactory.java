package com.ehealth4everyone.olamideadeleye.ui.main_activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import com.ehealth4everyone.olamideadeleye.ui.car_owners_fragment.CarOwnerFragment;
import com.ehealth4everyone.olamideadeleye.ui.filters_fragment.FilterListFragment;

import javax.inject.Inject;

public class AppFragmentFactory extends FragmentFactory {

    private MainActivityViewModel mMainActivityViewModel;

    @Inject
    public AppFragmentFactory(MainActivityViewModel mainActivityViewModel) {
        mMainActivityViewModel = mainActivityViewModel;
    }

    @NonNull
    @Override
    public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {
        if (className.equals(FilterListFragment.class.getName())) {
            return new FilterListFragment(mMainActivityViewModel);
        }
        if (className.equals(CarOwnerFragment.class.getName())) {
            return new CarOwnerFragment(mMainActivityViewModel);
        }

        return super.instantiate(classLoader, className);
    }
}
