package com.ehealth4everyone.olamideadeleye.car_owners_fragment;

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
import com.ehealth4everyone.olamideadeleye.car_owners_model.CarOwner;
import com.ehealth4everyone.olamideadeleye.databinding.FragmentCarOwnerBinding;
import com.ehealth4everyone.olamideadeleye.repo.CarOwnerRepo;

import java.util.List;

import javax.inject.Inject;

public class CarOwnerFragment extends Fragment {

    @Inject CarOwnerRepo mCarOwnerRepo;
    FragmentCarOwnerBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentCarOwnerBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        App app = (App) getActivity().getApplication();
        app.mAppComponent.plusCarOWnerFragment().injectCarOwnerFragment(this);

        CarOwnerViewModelFactory factory = new CarOwnerViewModelFactory(mCarOwnerRepo);
        CarOwnerViewModel carOwnerViewModel = new ViewModelProvider(this, factory)
                .get(CarOwnerViewModel.class);

        carOwnerViewModel.mCarOwnersLiveData.observe(this, new Observer<List<CarOwner>>() {
            @Override
            public void onChanged(List<CarOwner> carOwners) {
                Toast.makeText(getActivity(), carOwners.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
