package com.ehealth4everyone.olamideadeleye.repo;

import androidx.appcompat.view.ContextThemeWrapper;
import androidx.test.core.app.ApplicationProvider;

import com.ehealth4everyone.olamideadeleye.R;
import com.ehealth4everyone.olamideadeleye.TestDataFactory;
import com.ehealth4everyone.olamideadeleye.models.CarOwner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;

@Config(sdk = 21)
@RunWith(RobolectricTestRunner.class)
public class CarOwnerRepoImplTest {
    private static String FILE_NAME = "test_car_owners.csv";
    private ContextThemeWrapper mContext;
    private CarOwnerRepoImpl mCarOwnerRepo;
    private TestDataFactory mTestDataFactory;

    @Before
    public void methodSetup() {
        mContext = new ContextThemeWrapper(ApplicationProvider.getApplicationContext(), R.style.AppTheme);
        mCarOwnerRepo = new CarOwnerRepoImpl(mContext);
        mTestDataFactory = new TestDataFactory();
    }

    @Test
    public void verify_getCarOwnersFromAsset_getCorrectData() {
        List<CarOwner> expectedCarOwners = mTestDataFactory.getCarOwners();

        final TestObserver<List<CarOwner>> actualCarOwnersTestObserver = mCarOwnerRepo.getCarOwnersFromAsset(FILE_NAME).test();
        List<CarOwner> actualCarOwners = (List<CarOwner>) actualCarOwnersTestObserver.getEvents().get(0).get(0);

        actualCarOwnersTestObserver.assertSubscribed();
        assertNotNull(actualCarOwners);
        assertEquals(expectedCarOwners.size(), actualCarOwners.size());
        for (int carOwnerIndex = 0; carOwnerIndex < actualCarOwners.size(); carOwnerIndex++) {
            assertEquals(expectedCarOwners.get(carOwnerIndex).getId(), actualCarOwners.get(carOwnerIndex).getId());
        }
        actualCarOwnersTestObserver.dispose();
    }
}