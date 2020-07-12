package com.ehealth4everyone.olamideadeleye.main_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ehealth4everyone.olamideadeleye.App;
import com.ehealth4everyone.olamideadeleye.R;
import com.ehealth4everyone.olamideadeleye.car_owners_fragment.CarOwnerFragment;
import com.ehealth4everyone.olamideadeleye.car_owners_model.CarOwner;
import com.ehealth4everyone.olamideadeleye.di.AppComponent;
import com.ehealth4everyone.olamideadeleye.filters_fragment.FilterItemClickHandler;
import com.ehealth4everyone.olamideadeleye.filters_fragment.FilterListFragment;
import com.ehealth4everyone.olamideadeleye.repo.CarOwnerRepo;
import com.ehealth4everyone.olamideadeleye.repo.CarOwnerRepoImpl;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements FilterItemClickHandler {

    Disposable newThreadDisposable;
    Disposable ioThreadDisposable;

    CarOwnerRepo mCarOwnerRepo = new CarOwnerRepoImpl(this);
    public List<CarOwner> mCarOwners;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App app = (App) getApplication();
        AppComponent appComponent = app.mAppComponent;
        appComponent.injectMainActivity(this);

        //Notify user of data loading
        Toast.makeText(MainActivity.this, "Loading data...", Toast.LENGTH_SHORT).show();
        final ProgressBar progressBar = findViewById(R.id.progress_circular);
        progressBar.setVisibility(View.VISIBLE);

        //switch to a background thread
        final Scheduler scheduler = Schedulers.newThread();
        newThreadDisposable = scheduler.scheduleDirect(new Runnable() {
            @Override
            public void run() {
                //Load the car owners list from the .csv file on creation of MainActivity
                ioThreadDisposable = mCarOwnerRepo.readCarOwnerData()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<CarOwner>>() {
                            @Override
                            public void accept(List<CarOwner> carOwners) throws Exception {
                                progressBar.setVisibility(View.INVISIBLE);
                                if (savedInstanceState == null)
                                    openFilterListFragment();
                                mCarOwners = carOwners;
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        });
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
        if (newThreadDisposable != null && !newThreadDisposable.isDisposed())
            newThreadDisposable.dispose();
        if (ioThreadDisposable != null && !ioThreadDisposable.isDisposed())
            ioThreadDisposable.dispose();
    }
}