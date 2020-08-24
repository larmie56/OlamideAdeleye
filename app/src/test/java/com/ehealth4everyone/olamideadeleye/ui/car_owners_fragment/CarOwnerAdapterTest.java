package com.ehealth4everyone.olamideadeleye.ui.car_owners_fragment;

import android.view.ContextThemeWrapper;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.test.core.app.ApplicationProvider;

import com.ehealth4everyone.olamideadeleye.R;
import com.ehealth4everyone.olamideadeleye.TestDataFactory;
import com.ehealth4everyone.olamideadeleye.models.CarOwner;
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
public class CarOwnerAdapterTest {

    private ContextThemeWrapper mContext;
    private CarOwnerAdapter mCarOwnerAdapter;
    private TestDataFactory mTestDataFactory;

    @Before
    public void methodSetup() {
        mContext = new ContextThemeWrapper(ApplicationProvider.getApplicationContext(), R.style.AppTheme);
        mCarOwnerAdapter = new CarOwnerAdapter(mContext);
        mTestDataFactory = new TestDataFactory();
    }

    @Test
    public void verify_onCreateViewHolder_createsCorrectViewHolder() {
        LinearLayout linearLayout = new LinearLayout(mContext);

        CarOwnerAdapter.ViewHolder carOwnerAdapterViewHolder = mCarOwnerAdapter.createViewHolder(linearLayout
        , R.layout.car_owner_item);

        assertNotNull(carOwnerAdapterViewHolder);
        assertTrue(carOwnerAdapterViewHolder.mBinding.getRoot().findViewById(
                R.id.car_owner_icon) instanceof ImageView);
        assertTrue(carOwnerAdapterViewHolder.mBinding.getRoot().findViewById(
                R.id.tv_fullName) instanceof TextView);
    }

    @Test
    public void verify_onBindViewHolder_bindsCorrectDataToViewHolder() {
        List<CarOwner> carOwners = mTestDataFactory.getCarOwners();
        mCarOwnerAdapter.setItems(carOwners);
        LinearLayout linearLayout = new LinearLayout(mContext);
        CarOwnerAdapter.ViewHolder carOwnerAdapterViewHolder = mCarOwnerAdapter.createViewHolder(linearLayout, R.layout.car_owner_item);

        mCarOwnerAdapter.bindViewHolder(carOwnerAdapterViewHolder, 0);
        final CarOwner carOwner1 = carOwners.get(0);
        assertEquals(StringUtil.formatEmail(carOwner1.getEmail()),carOwnerAdapterViewHolder.mBinding.tvEmail.getText().toString());
        assertEquals(StringUtil.formatCarMakeColorYear(carOwner1.getCarModel(), carOwner1.getCarColor(), carOwner1.getCarModelYear()), carOwnerAdapterViewHolder.mBinding.tvCarMakeColorYear.getText().toString());

        mCarOwnerAdapter.bindViewHolder(carOwnerAdapterViewHolder, 1);
        final CarOwner carOwner2 = carOwners.get(1);
        assertEquals(StringUtil.formatEmail(carOwner2.getEmail()),carOwnerAdapterViewHolder.mBinding.tvEmail.getText().toString());
        assertEquals(StringUtil.formatCarMakeColorYear(carOwner2.getCarModel(), carOwner2.getCarColor(), carOwner2.getCarModelYear()), carOwnerAdapterViewHolder.mBinding.tvCarMakeColorYear.getText().toString());
    }

    @Test
    public void verify_getItemsCount_returnsCorrectCount() {
        List<CarOwner> carOwners = mTestDataFactory.getCarOwners();
        mCarOwnerAdapter.setItems(carOwners);

        final int itemCount = mCarOwnerAdapter.getItemCount();

        assertEquals(carOwners.size(), itemCount);
    }
}