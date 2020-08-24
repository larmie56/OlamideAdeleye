package com.ehealth4everyone.olamideadeleye.ui.filters_fragment;

import android.view.ContextThemeWrapper;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.test.core.app.ApplicationProvider;

import com.ehealth4everyone.olamideadeleye.R;
import com.ehealth4everyone.olamideadeleye.TestDataFactory;
import com.ehealth4everyone.olamideadeleye.models.Filter;
import com.ehealth4everyone.olamideadeleye.util.StringUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.junit.Assert.*;

@Config(sdk = 21)
@RunWith(RobolectricTestRunner.class)
public class FilterListAdapterTest {

    private ContextThemeWrapper mContext;
    private FilterListAdapter mFilterListAdapter;
    private TestDataFactory mTestDataFactory;

    @Before
    public void methodSetup() {
        mContext = new ContextThemeWrapper(ApplicationProvider.getApplicationContext(), R.style.AppTheme);
        mFilterListAdapter = new FilterListAdapter(mContext);
        mTestDataFactory = new TestDataFactory();
    }

    @Test
    public void verify_onCreateViewHolder_createsTheCorrectViewHolder() {
        LinearLayout linearLayout = new LinearLayout(mContext);

        FilterListAdapter.ViewHolder filterListAdapterViewHolder = mFilterListAdapter.createViewHolder(linearLayout, R.layout.filter_item);

        assertNotNull(filterListAdapterViewHolder);
        assertTrue(filterListAdapterViewHolder.mBinding.getRoot().findViewById(R.id.filter_icon) instanceof ImageView);
        assertTrue(filterListAdapterViewHolder.mBinding.getRoot().findViewById(R.id.tv_dateRange) instanceof TextView);
    }

    @Test
    public void verify_onBindViewHolder_bindsViewToCorrectData() {
        LinearLayout linearLayout = new LinearLayout(mContext);

        FilterListAdapter.ViewHolder filterListAdapterViewHolder = mFilterListAdapter.createViewHolder(linearLayout, R.layout.filter_item);
        mFilterListAdapter.setItems(mTestDataFactory.getFilters());

        mFilterListAdapter.onBindViewHolder(filterListAdapterViewHolder, 0);

        final Filter filter1 = mTestDataFactory.getFilters().get(0);
        assertEquals(StringUtil.formatGender(filter1.getGender()), filterListAdapterViewHolder.mBinding.tvGender.getText().toString());
        assertEquals(StringUtil.formatDateRange(filter1.getStartYear(), filter1.getEndYear()), filterListAdapterViewHolder.mBinding.tvDateRange.getText().toString());

        mFilterListAdapter.onBindViewHolder(filterListAdapterViewHolder, 1);

        final Filter filter2 = mTestDataFactory.getFilters().get(1);
        assertEquals(StringUtil.formatGender(filter2.getGender()), filterListAdapterViewHolder.mBinding.tvGender.getText());
        assertEquals(StringUtil.formatDateRange(filter2.getStartYear(), filter2.getEndYear()), filterListAdapterViewHolder.mBinding.tvDateRange.getText().toString());
    }

    @Test
    public void verify_getItemsCount_returnsCorrectCount() {
        final List<Filter> filters = mTestDataFactory.getFilters();
        mFilterListAdapter.setItems(mTestDataFactory.getFilters());

        final int itemCount = mFilterListAdapter.getItemCount();

        assertEquals(filters.size(), itemCount);
    }

    @Test
    public void test_filterListFragment_toCarOwnerFragment() {

    }
}