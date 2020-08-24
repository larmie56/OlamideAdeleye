package com.ehealth4everyone.olamideadeleye.ui.car_owners_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth4everyone.olamideadeleye.App;
import com.ehealth4everyone.olamideadeleye.models.CarOwner;
import com.ehealth4everyone.olamideadeleye.databinding.FragmentCarOwnerBinding;
import com.ehealth4everyone.olamideadeleye.models.Filter;
import com.ehealth4everyone.olamideadeleye.ui.main_activity.MainActivityViewModel;

import java.util.List;

import javax.inject.Inject;

public class CarOwnerFragment extends Fragment {
    @Inject CarOwnerAdapter mCarOwnerAdapter;
    private MainActivityViewModel mMainActivityViewModel;
    private FragmentCarOwnerBinding mBinding;
    @VisibleForTesting
    private RecyclerView mRecyclerView;

    public CarOwnerFragment(MainActivityViewModel mainActivityViewModel) {
        mMainActivityViewModel = mainActivityViewModel;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentCarOwnerBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final Bundle arguments = getArguments();
        if (arguments != null) {
            Filter filter = arguments.getParcelable(Filter.TAG);
            App app = (App) getActivity().getApplication();
            app.mAppComponent.plusCarOwnerFragment().injectCarOwnerFragment(this);

            mRecyclerView = mBinding.rvCarOwner;
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(linearLayoutManager);

            //Check the load status of the car owners data from the repo
            mMainActivityViewModel.getIsCarOwnersLoaded().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean isLoaded) {
                    if (isLoaded) {
                        //Only filter the car owners list if it has been loaded from the repo
                        mMainActivityViewModel.getCarOwners().observe(getViewLifecycleOwner(), new Observer<List<CarOwner>>() {
                            @Override
                            public void onChanged(List<CarOwner> carOwners) {
                                mMainActivityViewModel.updateFilteredCarOwnersList(carOwners, filter);
                            }
                        });
                    } else {
                        mBinding.progressCircular.setVisibility(View.VISIBLE);
                    }
                }
            });
            mMainActivityViewModel.getIsCarOwnersFiltered().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean isFiltered) {
                    if (isFiltered) {
                        mBinding.progressCircular.setVisibility(View.INVISIBLE);
                    } else {
                        mBinding.progressCircular.setVisibility(View.VISIBLE);
                    }
                }
            });
            mMainActivityViewModel.getFilteredCarOwners().observe(getViewLifecycleOwner(), new Observer<List<CarOwner>>() {
                @Override
                public void onChanged(List<CarOwner> carOwners) {
                    if (carOwners.isEmpty()) {
                        Toast.makeText(getActivity(),
                                "Applied conditions does not match any car owner", Toast.LENGTH_SHORT).show();
                    } else {
                        mCarOwnerAdapter.setItems(carOwners);
                        mCarOwnerAdapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
                        if (mRecyclerView.getAdapter() != mCarOwnerAdapter) {
                            mRecyclerView.setAdapter(mCarOwnerAdapter);
                        }
                    }
                }
            });
        }
    }
}
