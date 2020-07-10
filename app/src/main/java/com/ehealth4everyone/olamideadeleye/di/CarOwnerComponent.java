package com.ehealth4everyone.olamideadeleye.di;

import com.ehealth4everyone.olamideadeleye.car_owners_fragment.CarOwnerFragment;

import dagger.Subcomponent;

@CarOwnerScope
@Subcomponent(modules = CarOwnerBinder.class)
public interface CarOwnerComponent {

    void injectCarOwnerFragment(CarOwnerFragment fragment);
}
