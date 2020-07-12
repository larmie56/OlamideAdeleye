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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth4everyone.olamideadeleye.App;
import com.ehealth4everyone.olamideadeleye.car_owners_model.CarOwner;
import com.ehealth4everyone.olamideadeleye.databinding.FragmentCarOwnerBinding;
import com.ehealth4everyone.olamideadeleye.filter_model.Filter;
import com.ehealth4everyone.olamideadeleye.main_activity.MainActivity;

import java.util.List;

import javax.inject.Inject;

public class CarOwnerFragment extends Fragment {

    public String TAG = this.getClass().getSimpleName();

    @Inject
    CarOwnerAdapter mCarOwnerAdapter;
    private FragmentCarOwnerBinding mBinding;
    private RecyclerView mRecyclerView;
    private List<CarOwner> mCarOwners;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentCarOwnerBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final Bundle bundle = getArguments();
        if (bundle != null) {
            final Filter filter = bundle.getParcelable(Filter.TAG);
            App app = (App) getActivity().getApplication();
            app.mAppComponent.plusCarOwnerFragment().injectCarOwnerFragment(this);

            mBinding.tvCarOwnerFragmentTitle.setText("Car Owners");
            mRecyclerView = mBinding.rvCarOwner;
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setAdapter(mCarOwnerAdapter);

            mCarOwners = ((MainActivity) getActivity()).mCarOwners;
            CarOwnerViewModelFactory factory = new CarOwnerViewModelFactory(mCarOwners, filter);
            CarOwnerViewModel carOwnerViewModel = new ViewModelProvider(this, factory)
                    .get(CarOwnerViewModel.class);

            carOwnerViewModel.mCarOwnersLiveData.observe(getActivity(), new Observer<List<CarOwner>>() {
                @Override
                public void onChanged(List<CarOwner> carOwners) {
                    if (carOwners.isEmpty()) {
                        Toast.makeText(getActivity(), "Applied conditions does not match any car owner", Toast.LENGTH_SHORT).show();
                    }
                    mCarOwnerAdapter.setItems(carOwners);
                    mRecyclerView.setAdapter(mCarOwnerAdapter);
                }
            });

            carOwnerViewModel.loadStateLiveData.observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if (aBoolean) {
                        mBinding.tvCarOwnerFragmentTitle.setVisibility(View.INVISIBLE);
                        mBinding.progressCircular.setVisibility(View.VISIBLE);
                    }
                    else {
                        mBinding.progressCircular.setVisibility(View.INVISIBLE);
                        mBinding.tvCarOwnerFragmentTitle.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }
}
