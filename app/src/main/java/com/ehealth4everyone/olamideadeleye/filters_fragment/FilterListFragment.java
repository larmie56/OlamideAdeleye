package com.ehealth4everyone.olamideadeleye.filters_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ehealth4everyone.olamideadeleye.App;
import com.ehealth4everyone.olamideadeleye.databinding.FragmentFilterListBinding;
import com.ehealth4everyone.olamideadeleye.di.AppComponent;
import com.ehealth4everyone.olamideadeleye.filter_model.Filter;
import com.ehealth4everyone.olamideadeleye.repo.FilterRepo;

import java.util.List;

import javax.inject.Inject;

public class FilterListFragment extends Fragment {
    public String TAG = this.getClass().getSimpleName();

    private static FilterListFragment INSTANCE;

    @Inject
    FilterRepo mFilterRepo;

    FragmentFilterListBinding mFilterListBinding;
    FiltersViewModel mFiltersViewModel;

    private FilterListFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFilterListBinding = FragmentFilterListBinding.inflate(inflater, container, false);
        return mFilterListBinding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppComponent appComponent = ((App) getActivity().getApplication()).mAppComponent;
        appComponent.plusFilterListFragment().injectFilterListFragment(this);

        FiltersViewModelFactory factory = new FiltersViewModelFactory(mFilterRepo);
        mFiltersViewModel = new ViewModelProvider(this, factory).get(FiltersViewModel.class);
        mFiltersViewModel.getFiltersLiveData().observe(this, new Observer<List<Filter>>() {
            @Override
            public void onChanged(List<Filter> filters) {
                Toast.makeText(getActivity(), filters.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static FilterListFragment getInstance() {
        if (INSTANCE == null)
            INSTANCE = new FilterListFragment();

        return INSTANCE;
    }
}
