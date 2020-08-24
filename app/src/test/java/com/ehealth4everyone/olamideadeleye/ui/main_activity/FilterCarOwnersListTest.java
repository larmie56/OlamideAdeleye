package com.ehealth4everyone.olamideadeleye.ui.main_activity;

import com.ehealth4everyone.olamideadeleye.TestDataFactory;
import com.ehealth4everyone.olamideadeleye.models.CarOwner;
import com.ehealth4everyone.olamideadeleye.models.Filter;
import com.ehealth4everyone.olamideadeleye.repo.CarOwnerRepo;
import com.ehealth4everyone.olamideadeleye.repo.FilterRepo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FilterCarOwnersListTest {
    @Mock FilterRepo mFilterRepo;
    @Mock CarOwnerRepo mCarOwnerRepo;
    private TestDataFactory mTestDataFactory = new TestDataFactory();
    private MainActivityViewModel mMainActivityViewModel;

    @Before
    public void methodSetup() {
        mMainActivityViewModel = new MainActivityViewModel(mCarOwnerRepo, mFilterRepo);
    }

    @Test
    public void verify_selectedFilterIsApplied_onCarOwnersList_test1() {
        List<CarOwner> carOwners = mTestDataFactory.getCarOwners();
        Filter selectedFilter = mTestDataFactory.getTest1Filter();
        List<CarOwner> expectedFilteredCarOwnersList = mTestDataFactory.getTest1FilteredCarOwners();

        List<CarOwner> actualFilteredCarOwnersList = mMainActivityViewModel.filterCarOwnersList(carOwners, selectedFilter);

        assertNotNull(actualFilteredCarOwnersList);
        assertEquals(expectedFilteredCarOwnersList.size(), actualFilteredCarOwnersList.size());
        for (int index = 0; index < actualFilteredCarOwnersList.size(); index++) {
            assertEquals(expectedFilteredCarOwnersList.get(index).getId(), actualFilteredCarOwnersList.get(index).getId());
        }
    }

    @Test
    public void verify_selectedFilterIsApplied_onCarOwnersList_test2() {
        List<CarOwner> carOwners = mTestDataFactory.getCarOwners();
        Filter selectedFilter = mTestDataFactory.getTest2Filter();
        List<CarOwner> expectedFilteredCarOwnersList = mTestDataFactory.getTest2FilteredCarOwners();

        List<CarOwner> actualFilteredCarOwnersList = mMainActivityViewModel.filterCarOwnersList(carOwners, selectedFilter);

        assertNotNull(actualFilteredCarOwnersList);
        assertEquals(expectedFilteredCarOwnersList.size(), actualFilteredCarOwnersList.size());
        for (int index = 0; index < actualFilteredCarOwnersList.size(); index++) {
            assertEquals(expectedFilteredCarOwnersList.get(index).getId(), actualFilteredCarOwnersList.get(index).getId());
        }
    }

    @Test
    public void verify_colorsFieldIsIgnored_forEmptyColorsFilter() {
        List<CarOwner> carOwners = mTestDataFactory.getCarOwners();
        Filter selectedFilter = mTestDataFactory.getEmptyColorsFilter();
        List<CarOwner> expectedFilteredCarOwnersList = mTestDataFactory.getEmptyColorsFilteredCarOwners();

        List<CarOwner> actualFilteredCarOwnersList = mMainActivityViewModel.filterCarOwnersList(carOwners, selectedFilter);

        assertNotNull(actualFilteredCarOwnersList);
        assertEquals(expectedFilteredCarOwnersList.size(), actualFilteredCarOwnersList.size());
        for (int index = 0; index < actualFilteredCarOwnersList.size(); index++) {
            assertEquals(expectedFilteredCarOwnersList.get(index).getId(), actualFilteredCarOwnersList.get(index).getId());
        }
    }


    @Test
    public void verify_countriesFieldIsIgnored_forEmptyCountriesFilter() {
        List<CarOwner> carOwners = mTestDataFactory.getCarOwners();
        Filter selectedFilter = mTestDataFactory.getEmptyCountriesFilter();
        List<CarOwner> expectedFilteredCarOwnersList = mTestDataFactory.getEmptyCountriesFilteredCarOwners();

        List<CarOwner> actualFilteredCarOwnersList = mMainActivityViewModel.filterCarOwnersList(carOwners, selectedFilter);

        assertNotNull(actualFilteredCarOwnersList);
        assertEquals(expectedFilteredCarOwnersList.size(), actualFilteredCarOwnersList.size());
        for (int index = 0; index < actualFilteredCarOwnersList.size(); index++) {
            assertEquals(expectedFilteredCarOwnersList.get(index).getId(), actualFilteredCarOwnersList.get(index).getId());
        }
    }


    @Test
    public void verify_countriesAndColorsFieldsAreIgnored_forEmptyCountriesAndColorsFilter() {
        List<CarOwner> carOwners = mTestDataFactory.getCarOwners();
        Filter filter = mTestDataFactory.getEmptyCountriesAndColorsFilter();
        List<CarOwner> expectedFilteredCarOwnersList = mTestDataFactory.getEmptyCountriesAndColorsFilteredCarOwners();

        List<CarOwner> actualFilteredCarOwnersList = mMainActivityViewModel.filterCarOwnersList(carOwners, filter);

        assertNotNull(actualFilteredCarOwnersList);
        assertEquals(expectedFilteredCarOwnersList.size(), actualFilteredCarOwnersList.size());
        for (int index = 0; index < actualFilteredCarOwnersList.size(); index++) {
            assertEquals(expectedFilteredCarOwnersList.get(index).getId(), actualFilteredCarOwnersList.get(index).getId());
        }
    }
}
