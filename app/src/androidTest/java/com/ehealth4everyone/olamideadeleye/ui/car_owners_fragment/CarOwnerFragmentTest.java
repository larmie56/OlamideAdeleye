package com.ehealth4everyone.olamideadeleye.ui.car_owners_fragment;

import android.os.Bundle;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import com.ehealth4everyone.olamideadeleye.R;
import com.ehealth4everyone.olamideadeleye.TestDataFactory;
import com.ehealth4everyone.olamideadeleye.models.CarOwner;
import com.ehealth4everyone.olamideadeleye.models.Filter;
import com.ehealth4everyone.olamideadeleye.ui.main_activity.AppFragmentFactory;
import com.ehealth4everyone.olamideadeleye.ui.main_activity.MainActivityViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class CarOwnerFragmentTest {
    private TestDataFactory mTestDataFactory = new TestDataFactory();
    private AppFragmentFactory mFragmentFactory;
    private MainActivityViewModel mMainActivityViewModel;
    private MutableLiveData<Boolean> carOwnersLoadState;
    private MutableLiveData<List<CarOwner>> carOwnersLiveData;
    private MutableLiveData<Boolean> filterState;
    private MutableLiveData<List<CarOwner>> filteredCarOwnersLiveData;

    @Before
    public void methodSetup() {
        carOwnersLoadState = new MutableLiveData<>();
        carOwnersLiveData = new MutableLiveData<>();
        filterState = new MutableLiveData<>();
        filteredCarOwnersLiveData = new MutableLiveData<>();
        mMainActivityViewModel = mock(MainActivityViewModel.class);
    }

    @Test
    public void verify_recyclerViewIsShown_whenDataIsReady() {
        Filter filter = mTestDataFactory.getFilter();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Filter.TAG, filter);

        carOwnersLoadState.postValue(true);
        carOwnersLiveData.postValue(mTestDataFactory.getCarOwners());
        filterState.postValue(true);
        filteredCarOwnersLiveData.postValue(mTestDataFactory.getCarOwners());

        when(mMainActivityViewModel.getIsCarOwnersLoaded()).thenReturn(carOwnersLoadState);
        when(mMainActivityViewModel.getCarOwners()).thenReturn(carOwnersLiveData);
        when(mMainActivityViewModel.getIsCarOwnersFiltered()).thenReturn(filterState);
        when(mMainActivityViewModel.getFilteredCarOwners()).thenReturn(filteredCarOwnersLiveData);
        mFragmentFactory = new AppFragmentFactory(mMainActivityViewModel);

        FragmentScenario<CarOwnerFragment> fragmentScenario = FragmentScenario.launchInContainer(CarOwnerFragment.class, bundle, mFragmentFactory);

        onView(withId(R.id.rv_carOwner)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void verify_progressBarIsShown_whenCarOwnerListIsLoading() {
        Filter filter = mTestDataFactory.getFilter();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Filter.TAG, filter);

        carOwnersLoadState.postValue(false);
        carOwnersLiveData.postValue(mTestDataFactory.getEmptyCarOwnersList());
        filterState.postValue(false);
        filteredCarOwnersLiveData.postValue(mTestDataFactory.getEmptyCarOwnersList());

        when(mMainActivityViewModel.getIsCarOwnersLoaded()).thenReturn(carOwnersLoadState);
        when(mMainActivityViewModel.getCarOwners()).thenReturn(carOwnersLiveData);
        when(mMainActivityViewModel.getIsCarOwnersFiltered()).thenReturn(filterState);
        when(mMainActivityViewModel.getFilteredCarOwners()).thenReturn(filteredCarOwnersLiveData);
        mFragmentFactory = new AppFragmentFactory(mMainActivityViewModel);

        FragmentScenario<CarOwnerFragment> fragmentScenario = FragmentScenario.launchInContainer(CarOwnerFragment.class, bundle, mFragmentFactory);

        onView(withId(R.id.progress_circular)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_carOwner)).check(matches(not(isDisplayed())));
    }

    @Test
    public void verify_progressBarIsShown_duringCarOwnersFiltering() {
        Filter filter = mTestDataFactory.getFilter();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Filter.TAG, filter);

        carOwnersLoadState.postValue(true);
        carOwnersLiveData.postValue(mTestDataFactory.getCarOwners());
        filterState.postValue(false);
        filteredCarOwnersLiveData.postValue(mTestDataFactory.getEmptyCarOwnersList());

        when(mMainActivityViewModel.getIsCarOwnersLoaded()).thenReturn(carOwnersLoadState);
        when(mMainActivityViewModel.getCarOwners()).thenReturn(carOwnersLiveData);
        when(mMainActivityViewModel.getIsCarOwnersFiltered()).thenReturn(filterState);
        when(mMainActivityViewModel.getFilteredCarOwners()).thenReturn(filteredCarOwnersLiveData);
        mFragmentFactory = new AppFragmentFactory(mMainActivityViewModel);

        FragmentScenario<CarOwnerFragment> fragmentScenario = FragmentScenario.launchInContainer(CarOwnerFragment.class, bundle, mFragmentFactory);

        onView(withId(R.id.progress_circular)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_carOwner)).check(matches(not(isDisplayed())));
    }
}