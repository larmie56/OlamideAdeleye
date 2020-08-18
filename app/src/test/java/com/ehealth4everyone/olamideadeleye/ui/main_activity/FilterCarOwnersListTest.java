package com.ehealth4everyone.olamideadeleye.car_owners_fragment;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.ehealth4everyone.olamideadeleye.TestDataFactory;
import com.ehealth4everyone.olamideadeleye.models.CarOwner;
import com.ehealth4everyone.olamideadeleye.models.Filter;
import com.ehealth4everyone.olamideadeleye.repo.CarOwnerRepo;
import com.ehealth4everyone.olamideadeleye.repo.FilterRepo;
import com.ehealth4everyone.olamideadeleye.ui.main_activity.MainActivityViewModel;

import org.junit.Before;
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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FilterCarOwnersListTest {
    @Mock FilterRepo mFilterRepo;
    @Mock CarOwnerRepo mCarOwnerRepo;
    @Rule public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
    private TestScheduler mainTestScheduler = new TestScheduler();
    private TestScheduler ioTestScheduler = new TestScheduler();
    private TestScheduler newThreadScheduler = new TestScheduler();
    private TestDataFactory mTestDataFactory = new TestDataFactory();

    @Before
    public void setup() {
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
    @Test
    public void filterCarOwnersListTest_1() {
        //Arrange
        List<CarOwner> carOwners = mTestDataFactory.getCarOwners();
        List<Filter> filters = mTestDataFactory.getFilterList();


        when(mCarOwnerRepo.getCarOwnersFromAsset()).thenReturn(Single.just(carOwners));
        when(mFilterRepo.getJsonStringFromAsset()).thenReturn("");
        when(mFilterRepo.getFiltersFromJsonString(anyString())).thenReturn(Single.just(filters));

        MainActivityViewModel mainActivityViewModel = new MainActivityViewModel(mCarOwnerRepo, mFilterRepo);

        //Act
        mainActivityViewModel.getFiltersFromRepo();
        ioTestScheduler.triggerActions();
        mainTestScheduler.triggerActions();

        //Assert
        assertNotNull(mainActivityViewModel.getFilters().getValue());
        assertEquals(mainActivityViewModel.getFilters().getValue(), filters);
        assertEquals(mainActivityViewModel.getFilters().getValue().get(1).getId(), filters.get(1).getId());
    }
/*
    @Test
    public void filterCarOwnersListTest_2() {
        //Arrange
        List<CarOwner> carOwners = getCarOwners();
        Filter filter = getFilter2();

        //Act
        CarOwnerViewModel carOwnerViewModel = new CarOwnerViewModel(carOwners, filter);
        final List<CarOwner> actualFilteredList = carOwnerViewModel.filterCarOwnersList(carOwners, filter);

        //Assert
        assertEquals(getFilteredList2().size(), actualFilteredList.size());
        assertEquals(getFilteredList2().get(0).getFirstName(), actualFilteredList.get(0).getFirstName());

    }

    @Test
    public void filterCarOwnersListTest_3() {
        //Arrange
        List<CarOwner> carOwners = getCarOwners();
        Filter filter = getFilter3();

        //Act
        CarOwnerViewModel carOwnerViewModel = new CarOwnerViewModel(carOwners, filter);
        final List<CarOwner> actualFilteredList = carOwnerViewModel.filterCarOwnersList(carOwners, filter);

        //Assert
        assertEquals(getFilteredList3().size(), actualFilteredList.size());
        assertEquals(getFilteredList3().get(0).getFirstName(), actualFilteredList.get(0).getFirstName());
    }

    @Test
    public void filterCarOwnersListTest_4() {
        //Arrange
        List<CarOwner> carOwners = getCarOwners();
        Filter filter = getFilter4();

        //Act
        CarOwnerViewModel carOwnerViewModel = new CarOwnerViewModel(carOwners, filter);
        final List<CarOwner> actualFilteredList = carOwnerViewModel.filterCarOwnersList(carOwners, filter);

        //Assert
        assertEquals(getFilteredList4().size(), actualFilteredList.size());
        assertEquals(getFilteredList4().get(0).getFirstName(), actualFilteredList.get(0).getFirstName());
        assertEquals(getFilteredList4().get(1).getFirstName(), actualFilteredList.get(1).getFirstName());
        assertEquals(getFilteredList4().get(2).getFirstName(), actualFilteredList.get(2).getFirstName());
    }

    @Test
    public void filterCarOwnersListTest_5() {
        //Arrange
        List<CarOwner> carOwners = getCarOwners();
        Filter filter = getFilter5();

        //Act
        CarOwnerViewModel carOwnerViewModel = new CarOwnerViewModel(carOwners, filter);
        final List<CarOwner> actualFilteredList = carOwnerViewModel.filterCarOwnersList(carOwners, filter);

        //Assert
        assertEquals(getFilteredList5().size(), actualFilteredList.size());
        assertEquals(getFilteredList5().get(0).getFirstName(), actualFilteredList.get(0).getFirstName());
    }*/
}
