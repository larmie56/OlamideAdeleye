package com.ehealth4everyone.olamideadeleye.repo;

import com.ehealth4everyone.olamideadeleye.models.CarOwner;

import java.util.List;

import io.reactivex.Single;

public interface CarOwnerRepo {

    Single<List<CarOwner>> getCarOwnersFromAsset();
}
