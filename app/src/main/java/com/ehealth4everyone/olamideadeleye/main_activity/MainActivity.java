package com.ehealth4everyone.olamideadeleye.main_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.ehealth4everyone.olamideadeleye.App;
import com.ehealth4everyone.olamideadeleye.R;
import com.ehealth4everyone.olamideadeleye.di.AppComponent;
import com.ehealth4everyone.olamideadeleye.filters_fragment.FilterListFragment;

public class MainActivity extends AppCompatActivity {

    private FilterListFragment mFilterListFragment;

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
        mFilterListFragment = FilterListFragment.getInstance();
        fragmentTransaction.add(R.id.fragment_container, mFilterListFragment, mFilterListFragment.TAG);
        fragmentTransaction.commit();
    }
}