package com.ehealth4everyone.olamideadeleye.repo;

import com.ehealth4everyone.olamideadeleye.car_owners_model.CarOwner;

import java.util.List;

import io.reactivex.Single;

public interface CarOwnerRepo {

    Single<List<CarOwner>> readCarOwnerData();
}
