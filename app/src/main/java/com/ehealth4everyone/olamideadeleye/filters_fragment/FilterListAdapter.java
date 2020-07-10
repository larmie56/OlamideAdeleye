package com.ehealth4everyone.olamideadeleye.filters_fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth4everyone.olamideadeleye.databinding.FilterItemListBinding;
import com.ehealth4everyone.olamideadeleye.filter_model.Filter;
import com.ehealth4everyone.olamideadeleye.util.StringUtil;

import java.util.List;

import javax.inject.Inject;

public class FilterListAdapter extends RecyclerView.Adapter<FilterListAdapter.ViewHolder> {
    FilterItemListBinding mBinding;
    LayoutInflater mInflater;
    List<Filter> mFilters;

    @Inject
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mFilters, position);
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

        void bind(List<Filter> filters, int position) {
            Filter filter = filters.get(position);

            this.mBinding.tvDateRange.setText(StringUtil
                    .formatDateRange(filter.getStartYear(), filter.getEndYear()));
            this.mBinding.tvGender.setText(StringUtil.formatGender(filter.getGender()));
            this.mBinding.tvCountries.setText(StringUtil.formatCountries(filter.getCountries()));
            this.mBinding.tvColor.setText(StringUtil.formatColors(filter.getColors()));
        }
    }
}
