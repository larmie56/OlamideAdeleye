package com.ehealth4everyone.olamideadeleye;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.ehealth4everyone.olamideadeleye.models.Filter;
import com.ehealth4everyone.olamideadeleye.ui.filters_fragment.FilterListAdapter;
import com.ehealth4everyone.olamideadeleye.ui.filters_fragment.FilterListFragment;
import com.ehealth4everyone.olamideadeleye.ui.main_activity.AppFragmentFactory;
import com.ehealth4everyone.olamideadeleye.ui.main_activity.MainActivityViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class FilterListToCarOwnersNavigationTest {
    TestDataFactory mTestDataFactory = new TestDataFactory();
    NavController mockNavController;
    MainActivityViewModel mainActivityViewModel;

    @Before
    public void methodSetup() {
        mockNavController = mock(NavController.class);
        mainActivityViewModel = mock(MainActivityViewModel.class);
    }

    @Test
    public void navigateFromFilterListFragment_toCarOwnerFragmentTest() {
        MutableLiveData<List<Filter>> filtersLiveData = new MutableLiveData<>();

        List<Filter> filters = mTestDataFactory.getFilterList();
        filtersLiveData.postValue(filters);

        when(mainActivityViewModel.getFilters()).thenReturn(filtersLiveData);

        AppFragmentFactory fragmentFactory = new AppFragmentFactory(mainActivityViewModel);

        // Create a graphical FragmentScenario for the TitleScreen
        FragmentScenario<FilterListFragment> mFilterListFragmentFragmentScenario =
                FragmentScenario.launchInContainer(FilterListFragment.class, null, fragmentFactory);

        mFilterListFragmentFragmentScenario.onFragment(new FragmentScenario.FragmentAction<FilterListFragment>() {
            @Override
            public void perform(@NonNull FilterListFragment fragment) {
                // Set the NavController property on the fragment
                Navigation.setViewNavController(fragment.requireView(), mockNavController);
                final FilterListAdapter.ViewHolder viewHolder =
                        (FilterListAdapter.ViewHolder) fragment.mRecyclerView.findViewHolderForAdapterPosition(0);
                viewHolder.mBundle = new Bundle();
                viewHolder.itemView.performClick();
                viewHolder.mBundle.putParcelable(Filter.TAG, filters.get(0));
                verify(mockNavController).navigate(R.id.action_filterListFragment_to_carOwnerFragment, viewHolder.mBundle);
            }
        });
    }
}
