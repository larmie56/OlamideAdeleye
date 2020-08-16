package com.ehealth4everyone.olamideadeleye.ui.main_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ehealth4everyone.olamideadeleye.App;
import com.ehealth4everyone.olamideadeleye.R;
import com.ehealth4everyone.olamideadeleye.ui.car_owners_fragment.CarOwnerFragment;
import com.ehealth4everyone.olamideadeleye.models.CarOwner;
import com.ehealth4everyone.olamideadeleye.di.AppComponent;
import com.ehealth4everyone.olamideadeleye.ui.filters_fragment.FilterItemClickHandler;
import com.ehealth4everyone.olamideadeleye.ui.filters_fragment.FilterListFragment;
import com.ehealth4everyone.olamideadeleye.repo.CarOwnerRepoImpl;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements FilterItemClickHandler {

    @Inject
    CarOwnerRepoImpl mCarOwnerRepo;
    public List<CarOwner> mCarOwners;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App app = (App) getApplication();
        AppComponent appComponent = app.mAppComponent;
        appComponent.injectMainActivity(this);

        final ProgressBar progressBar = findViewById(R.id.progress_circular);

        MainActivityViewModelFactory factory = new MainActivityViewModelFactory(mCarOwnerRepo);
        MainActivityViewModel mainActivityViewModel = new ViewModelProvider(this, factory)
                .get(MainActivityViewModel.class);
        mainActivityViewModel.loadStateLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Toast.makeText(MainActivity.this, "Loading Data", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.VISIBLE);
                }
                else {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });

        mainActivityViewModel.mCarOwnersLiveData.observe(this, new Observer<List<CarOwner>>() {
            @Override
            public void onChanged(List<CarOwner> carOwners) {
                mCarOwners = carOwners;
                //don't add the new fragment if the activity has been created previously
                if (savedInstanceState == null)
                    openFilterListFragment();
            }
        });
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

    @Override
    protected void onStop() {
        super.onStop();

    }
}