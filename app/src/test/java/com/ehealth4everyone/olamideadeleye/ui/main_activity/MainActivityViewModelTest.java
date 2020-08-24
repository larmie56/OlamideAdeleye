package com.ehealth4everyone.olamideadeleye.ui.main_activity;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.ehealth4everyone.olamideadeleye.TestDataFactory;
import com.ehealth4everyone.olamideadeleye.models.CarOwner;
import com.ehealth4everyone.olamideadeleye.models.Filter;
import com.ehealth4everyone.olamideadeleye.repo.CarOwnerRepo;
import com.ehealth4everyone.olamideadeleye.repo.FilterRepo;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.TestScheduler;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityViewModelTest {

    private MainActivityViewModel mainActivityViewModel;
    @Mock FilterRepo mFilterRepo;
    @Mock CarOwnerRepo mCarOwnerRepo;
    @Rule public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
    private static TestScheduler mainTestScheduler = new TestScheduler();
    private static TestScheduler ioTestScheduler = new TestScheduler();
    private static TestScheduler newThreadScheduler = new TestScheduler();
    private TestDataFactory mTestDataFactory = new TestDataFactory();

    @BeforeClass
    public static void setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return mainTestScheduler;
            }
        });
        RxJavaPlugins.setInitNewThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return newThreadScheduler;
            }
        });
        RxJavaPlugins.setInitIoSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return ioTestScheduler;
            }
        });
    }

    @Before
    public void methodSetup() {
        mainActivityViewModel = new MainActivityViewModel(mCarOwnerRepo, mFilterRepo);
    }
    @Test
    public void verify_getFiltersFromRepo_getsCorrectData_fromRepo() {
        List<Filter> filters = mTestDataFactory.getFilters();

        when(mFilterRepo.getJsonStringFromAsset(anyString())).thenReturn("");
        when(mFilterRepo.getFiltersFromJsonString(anyString())).thenReturn(Single.just(filters));

        mainActivityViewModel.getFiltersFromRepo();
        ioTestScheduler.triggerActions();
        mainTestScheduler.triggerActions();

        verifyZeroInteractions(mCarOwnerRepo);
        verify(mFilterRepo).getFiltersFromJsonString(anyString());
        final List<Filter> filtersFromRepo = mainActivityViewModel.getFilters().getValue();
        assertNotNull(filtersFromRepo);
        assertEquals(filtersFromRepo, filters);
        for (int index = 0; index < filtersFromRepo.size(); index++) {
            assertEquals(filters.get(index).getId(), filtersFromRepo.get(index).getId());
        }
    }

    @Test
    public void verify_getCarOwnersFromRepo_getsCorrectData_fromRepo() {
        List<CarOwner> carOwners = mTestDataFactory.getCarOwners();
        when(mCarOwnerRepo.getCarOwnersFromAsset(anyString())).thenReturn(Single.just(carOwners));

        mainActivityViewModel.getCarOwnersFromRepo();
        newThreadScheduler.triggerActions();
        ioTestScheduler.triggerActions();
        mainTestScheduler.triggerActions();

        verifyZeroInteractions(mFilterRepo);
        verify(mCarOwnerRepo).getCarOwnersFromAsset(anyString());
        final List<CarOwner> carOwnerFromRepo = mainActivityViewModel.getCarOwners().getValue();
        assertNotNull(carOwnerFromRepo);
        assertEquals(carOwnerFromRepo, carOwners);
        for (int index = 0; index < carOwnerFromRepo.size(); index++) {
            assertEquals(carOwners.get(index).getId(), carOwnerFromRepo.get(index).getId());
        }
    }

    @Test
    public void verify_getFilteredCarOwnersList_getsCorrectFilteredCarOwnersList() {
        List<CarOwner> carOwners = mTestDataFactory.getCarOwners();
        Filter selectedFilter = mTestDataFactory.getTest1Filter();
        List<CarOwner> expectedFilteredCarOwnersList = mTestDataFactory.getTest1FilteredCarOwners();

        mainActivityViewModel.updateFilteredCarOwnersList(carOwners, selectedFilter);
        newThreadScheduler.triggerActions();

        List<CarOwner> actualFilteredCarOwnersList = mainActivityViewModel.getFilteredCarOwners().getValue();
        assertNotNull(actualFilteredCarOwnersList);
        assertEquals(expectedFilteredCarOwnersList.size(), actualFilteredCarOwnersList.size());
        for (int index = 0; index < actualFilteredCarOwnersList.size(); index++) {
            assertEquals(expectedFilteredCarOwnersList.get(0).getId(), actualFilteredCarOwnersList.get(0).getId());
        }
    }
}