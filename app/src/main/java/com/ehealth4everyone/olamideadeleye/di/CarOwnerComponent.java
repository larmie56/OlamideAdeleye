package com.ehealth4everyone.olamideadeleye.di;

import com.ehealth4everyone.olamideadeleye.ui.car_owners_fragment.CarOwnerFragment;

import dagger.Subcomponent;

@CarOwnerScope
@Subcomponent()
public interface CarOwnerComponent {

    void injectCarOwnerFragment(CarOwnerFragment fragment);
}
