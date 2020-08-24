package com.ehealth4everyone.olamideadeleye.ui.main_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.ehealth4everyone.olamideadeleye.App;
import com.ehealth4everyone.olamideadeleye.R;
import com.ehealth4everyone.olamideadeleye.di.AppComponent;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity{

    @Inject MainActivityViewModelFactory mViewModelFactory;
    AppFragmentFactory mFragmentFactory;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        //Clear the splash screen and set the theme to the AppTheme
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        App app = (App) getApplication();
        AppComponent appComponent = app.mAppComponent;
        appComponent.injectMainActivity(this);

        MainActivityViewModel mainActivityViewModel = new ViewModelProvider(this, mViewModelFactory)
                .get(MainActivityViewModel.class);

        //Use a fragment factory to initialize the fragments with the same instance of MainActivityViewModel
        mFragmentFactory = new AppFragmentFactory(mainActivityViewModel);
        getSupportFragmentManager().setFragmentFactory(mFragmentFactory);
        setContentView(R.layout.activity_main);

        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController);

        if (savedInstanceState == null) {
            mainActivityViewModel.getFiltersFromRepo();
            mainActivityViewModel.getCarOwnersFromRepo();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()
                || super.onSupportNavigateUp();
    }
}