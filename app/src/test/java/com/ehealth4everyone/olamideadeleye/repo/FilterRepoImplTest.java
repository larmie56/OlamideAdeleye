package com.ehealth4everyone.olamideadeleye.repo;

import androidx.appcompat.view.ContextThemeWrapper;
import androidx.test.core.app.ApplicationProvider;

import com.ehealth4everyone.olamideadeleye.R;
import com.ehealth4everyone.olamideadeleye.TestDataFactory;
import com.ehealth4everyone.olamideadeleye.models.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.List;

import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Config(sdk = 21)
@RunWith(RobolectricTestRunner.class)
public class FilterRepoImplTest {
    private static final String FILE_NAME = "test_filter.json";
    private ContextThemeWrapper mContext;
    private FilterRepo mFilterRepo;

    @Before
    public void setup() {
    mContext = new ContextThemeWrapper(ApplicationProvider.getApplicationContext(), R.style.AppTheme);
    mFilterRepo = new FilterRepoImpl(mContext);
    }

    @Test
    public void shouldGetJsonString_fromAsset() throws IOException {
        final String jsonStringFromAsset = mFilterRepo.getJsonStringFromAsset(FILE_NAME);

        assertNotNull(jsonStringFromAsset);
    }

    @Test
    public void shouldReturnCorrectFilters_fromJsonString() {
        String jsonString = mFilterRepo.getJsonStringFromAsset(FILE_NAME);
        TestDataFactory testDataFactory = new TestDataFactory();
        final List<Filter> expectedFilters = testDataFactory.simulateFiltersFromJsonString();

        final TestObserver<List<Filter>> actualFiltersTestObserver = mFilterRepo.getFiltersFromJsonString(jsonString).test();
        final List<Filter> actualFilters = (List<Filter>)actualFiltersTestObserver.getEvents().get(0).get(0);

        actualFiltersTestObserver.assertSubscribed();
        assertNotNull(actualFilters);
        assertEquals(expectedFilters.size(), actualFilters.size());
        for (int filterIndex = 0; filterIndex < actualFilters.size(); filterIndex++) {
            assertEquals(expectedFilters.get(0).getId(), actualFilters.get(0).getId());
        }
        actualFiltersTestObserver.dispose();
    }
}