package com.ehealth4everyone.olamideadeleye.ui.filters_fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth4everyone.olamideadeleye.R;
import com.ehealth4everyone.olamideadeleye.databinding.FilterItemListBinding;
import com.ehealth4everyone.olamideadeleye.models.Filter;
import com.ehealth4everyone.olamideadeleye.util.StringUtil;

import java.util.List;

public class FilterListAdapter extends RecyclerView.Adapter<FilterListAdapter.ViewHolder> {
    FilterItemListBinding mBinding;
    LayoutInflater mInflater;
    List<Filter> mFilters;

    public FilterListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mBinding = FilterItemListBinding.inflate(mInflater, parent, false);
        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Filter filter = mFilters.get(position);
        holder.bind(filter);
    }

    @Override
    public int getItemCount() {
        return mFilters == null ? 0 : mFilters.size();
    }

    public void setItems(List<Filter> filters) {
        mFilters = filters;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        FilterItemListBinding mBinding;

        public ViewHolder(FilterItemListBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(Filter filter) {
            this.mBinding.tvDateRange.setText(StringUtil
                    .formatDateRange(filter.getStartYear(), filter.getEndYear()));
            this.mBinding.tvGender.setText(StringUtil.formatGender(filter.getGender()));
            this.mBinding.tvCountries.setText(StringUtil.formatCountries(filter.getCountries()));
            this.mBinding.tvColor.setText(StringUtil.formatColors(filter.getColors()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(Filter.TAG, filter);
                    final View.OnClickListener navigateOnClickListener =
                            Navigation.createNavigateOnClickListener(R.id.action_filterListFragment_to_carOwnerFragment, bundle);
                    navigateOnClickListener.onClick(view);
                }
            });
        }
    }
}
