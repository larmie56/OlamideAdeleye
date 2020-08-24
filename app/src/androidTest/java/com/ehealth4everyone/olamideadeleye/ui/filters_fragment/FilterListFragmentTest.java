package com.ehealth4everyone.olamideadeleye.ui.filters_fragment;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.lifecycle.MutableLiveData;

import com.ehealth4everyone.olamideadeleye.R;
import com.ehealth4everyone.olamideadeleye.TestDataFactory;
import com.ehealth4everyone.olamideadeleye.models.Filter;
import com.ehealth4everyone.olamideadeleye.ui.main_activity.AppFragmentFactory;
import com.ehealth4everyone.olamideadeleye.ui.main_activity.MainActivityViewModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class FilterListFragmentTest {
    TestDataFactory mTestDataFactory = new TestDataFactory();
    MainActivityViewModel mainActivityViewModel = mock(MainActivityViewModel.class);
    AppFragmentFactory fragmentFactory;

    @Test
    public void verify_recyclerViewIsShown() {
        MutableLiveData<List<Filter>> filtersLiveData = new MutableLiveData<>();

        List<Filter> filters = mTestDataFactory.getFilterList();
        filtersLiveData.postValue(filters);

        when(mainActivityViewModel.getFilters()).thenReturn(filtersLiveData);

        fragmentFactory = new AppFragmentFactory(mainActivityViewModel);
        FragmentScenario<FilterListFragment> mFragmentScenario =
            FragmentScenario.launchInContainer(FilterListFragment.class, null, fragmentFactory);

        onView(withId(R.id.filter_list_rv)).check(matches(isCompletelyDisplayed()));
    }
}