package com.ehealth4everyone.olamideadeleye.ui.car_owners_fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth4everyone.olamideadeleye.models.CarOwner;
import com.ehealth4everyone.olamideadeleye.databinding.CarOwnerItemBinding;
import com.ehealth4everyone.olamideadeleye.util.StringUtil;

import java.util.List;

import javax.inject.Inject;

public class CarOwnerAdapter extends RecyclerView.Adapter<CarOwnerAdapter.ViewHolder> {

    private CarOwnerItemBinding mBinding;
    private LayoutInflater mInflater;
    private List<CarOwner> mCarOwners;

    @Inject
    public CarOwnerAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mBinding = CarOwnerItemBinding.inflate(mInflater, parent, false);
        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CarOwner carOwner = mCarOwners.get(position);

        holder.bind(carOwner);
    }

    @Override
    public int getItemCount() {
        return mCarOwners == null ? 0 : mCarOwners.size();
    }

    public void setItems(List<CarOwner> carOwners) {
        mCarOwners = carOwners;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CarOwnerItemBinding mBinding;

        public ViewHolder(CarOwnerItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void bind(CarOwner carOwner) {
            mBinding.tvFullName.setText(
                    StringUtil.formatFullName(carOwner.getFirstName(), carOwner.getLastName()));
            mBinding.tvEmail.setText(StringUtil.formatEmail(carOwner.getEmail()));
            mBinding.tvCountry.setText(StringUtil.formatountry(carOwner.getCountry()));
            mBinding.tvCarMakeColorYear.setText(StringUtil.formatCarMakeColorYear(carOwner.getCarModel(), carOwner.getCarColor(), carOwner.getCarModelYear()));
            mBinding.tvGender.setText(StringUtil.formatGender(carOwner.getGender()));
            mBinding.tvJobTitle.setText(StringUtil.formatJobTitle(carOwner.getJobTitle()));
            mBinding.tvBio.setText(StringUtil.formatBio(carOwner.getBio()));
        }
    }
}
