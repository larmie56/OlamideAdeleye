package com.ehealth4everyone.olamideadeleye.ui.car_owners_fragment;

import androidx.recyclerview.widget.DiffUtil;

import com.ehealth4everyone.olamideadeleye.models.CarOwner;

import java.util.List;

public class CarOwnersAdapterDiffUtil extends DiffUtil.Callback {

    private List<CarOwner> mOldCarOwners;
    private List<CarOwner> mNewCarOwners;

    public CarOwnersAdapterDiffUtil(List<CarOwner> oldList, List<CarOwner> newList) {
        mOldCarOwners = oldList;
        mNewCarOwners = newList;
    }
    @Override
    public int getOldListSize() {
        return mOldCarOwners.size();
    }

    @Override
    public int getNewListSize() {
        return mNewCarOwners.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldCarOwners.get(oldItemPosition).getId() == mNewCarOwners.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldCarOwners.get(oldItemPosition).equals(mNewCarOwners.get(newItemPosition));
    }
}
