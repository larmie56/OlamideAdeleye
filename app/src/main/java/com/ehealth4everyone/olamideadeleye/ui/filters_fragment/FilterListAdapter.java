package com.ehealth4everyone.olamideadeleye.ui.filters_fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth4everyone.olamideadeleye.databinding.FilterItemListBinding;
import com.ehealth4everyone.olamideadeleye.models.Filter;
import com.ehealth4everyone.olamideadeleye.util.StringUtil;

import java.util.List;

public class FilterListAdapter extends RecyclerView.Adapter<FilterListAdapter.ViewHolder> {
    FilterItemListBinding mBinding;
    LayoutInflater mInflater;
    List<Filter> mFilters;
    FilterItemClickHandler mClickHandler;

    public FilterListAdapter(Context context, FilterItemClickHandler clickHandler) {
        mInflater = LayoutInflater.from(context);
        mClickHandler = clickHandler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mBinding = FilterItemListBinding.inflate(mInflater, parent, false);
        return new ViewHolder(mBinding, mClickHandler);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Filter filter = mFilters.get(position);
        holder.bind(filter);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {;
                holder.handleOnClick(filter);
            }
        });
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
        FilterItemClickHandler mClickHandler;

        public ViewHolder(FilterItemListBinding binding, FilterItemClickHandler clickHandler) {
            super(binding.getRoot());
            mBinding = binding;
            mClickHandler = clickHandler;
        }

        void bind(Filter filter) {
            this.mBinding.tvDateRange.setText(StringUtil
                    .formatDateRange(filter.getStartYear(), filter.getEndYear()));
            this.mBinding.tvGender.setText(StringUtil.formatGender(filter.getGender()));
            this.mBinding.tvCountries.setText(StringUtil.formatCountries(filter.getCountries()));
            this.mBinding.tvColor.setText(StringUtil.formatColors(filter.getColors()));
        }

        public void handleOnClick(Filter filter) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(Filter.TAG, filter);
            this.mClickHandler.openCarOwnerFragment(bundle);
        }
    }
}
