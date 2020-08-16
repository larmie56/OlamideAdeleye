package com.ehealth4everyone.olamideadeleye.ui.filters_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth4everyone.olamideadeleye.databinding.FragmentFilterListBinding;
import com.ehealth4everyone.olamideadeleye.models.Filter;
import com.ehealth4everyone.olamideadeleye.ui.main_activity.MainActivityViewModel;

import java.util.List;

public class FilterListFragment extends Fragment {
    public String TAG = this.getClass().getSimpleName();

    FilterListAdapter mFilterListAdapter;

    FragmentFilterListBinding mFilterListBinding;
    MainActivityViewModel mActivityViewModel;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFilterListBinding = FragmentFilterListBinding.inflate(inflater, container, false);
        return mFilterListBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = mFilterListBinding.filterListRv;
        mFilterListBinding.tvFilterFragmentTitle.setText("Filters");
        mFilterListAdapter = new FilterListAdapter(getActivity(), (FilterItemClickHandler) getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        mActivityViewModel.getFilters().observe(this, new Observer<List<Filter>>() {
            @Override
            public void onChanged(List<Filter> filters) {
                mFilterListAdapter.setItems(filters);
                mRecyclerView.setAdapter(mFilterListAdapter);
            }
        });
    }

}
