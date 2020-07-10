package com.ehealth4everyone.olamideadeleye.main_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.ehealth4everyone.olamideadeleye.App;
import com.ehealth4everyone.olamideadeleye.R;
import com.ehealth4everyone.olamideadeleye.car_owners_fragment.CarOwnerFragment;
import com.ehealth4everyone.olamideadeleye.di.AppComponent;
import com.ehealth4everyone.olamideadeleye.filters_fragment.FilterItemClickHandler;
import com.ehealth4everyone.olamideadeleye.filters_fragment.FilterListFragment;

public class MainActivity extends AppCompatActivity implements FilterItemClickHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App app = (App) getApplication();
        AppComponent appComponent = app.mAppComponent;
        appComponent.injectMainActivity(this);

        openFilterListFragment();
    }

    private void openFilterListFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        FilterListFragment filterListFragment = new FilterListFragment();
        fragmentTransaction.add(R.id.fragment_container, filterListFragment, filterListFragment.TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void openCarOwnerFragment(Bundle bundle) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        CarOwnerFragment carOwnerFragment = new CarOwnerFragment();
        carOwnerFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container, carOwnerFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}