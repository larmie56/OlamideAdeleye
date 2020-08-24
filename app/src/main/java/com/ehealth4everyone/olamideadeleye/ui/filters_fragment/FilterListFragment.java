package com.ehealth4everyone.olamideadeleye.ui.filters_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth4everyone.olamideadeleye.App;
import com.ehealth4everyone.olamideadeleye.databinding.FragmentFilterListBinding;
import com.ehealth4everyone.olamideadeleye.models.Filter;
import com.ehealth4everyone.olamideadeleye.ui.main_activity.MainActivityViewModel;

import java.util.List;

import javax.inject.Inject;

public class FilterListFragment extends Fragment {
    @Inject FilterListAdapter mFilterListAdapter;
    private FragmentFilterListBinding mFilterListBinding;
    private MainActivityViewModel mMainActivityViewModel;
    public RecyclerView mRecyclerView;

    public FilterListFragment(MainActivityViewModel viewModel) {
        mMainActivityViewModel = viewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFilterListBinding = FragmentFilterListBinding.inflate(inflater, container, false);
        return mFilterListBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        App app = (App) getActivity().getApplication();
        app.mAppComponent.plusFilterListFragment().injectFilterListFragment(this);

        mRecyclerView = mFilterListBinding.filterListRv;
        mFilterListAdapter = new FilterListAdapter(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mMainActivityViewModel.getFilters().observe(getViewLifecycleOwner(), new Observer<List<Filter>>() {
            @Override
            public void onChanged(List<Filter> filters) {
                mFilterListAdapter.setItems(filters);
                mFilterListAdapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
                if (mRecyclerView.getAdapter() != mFilterListAdapter) {
                    mRecyclerView.setAdapter(mFilterListAdapter);
                }
            }
        });
   }
}
