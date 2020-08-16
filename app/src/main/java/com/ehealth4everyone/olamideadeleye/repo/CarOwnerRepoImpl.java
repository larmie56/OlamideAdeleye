package com.ehealth4everyone.olamideadeleye.repo;

import android.content.Context;
import android.util.Log;

import com.ehealth4everyone.olamideadeleye.models.CarOwner;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class CarOwnerRepoImpl implements CarOwnerRepo {
    Context mContext;

    @Inject
    public CarOwnerRepoImpl(Context context) {
        mContext = context;
    }

    @Override
    public Single<List<CarOwner>> readCarOwnerData() {
        List<CarOwner> carOwners = new ArrayList<>();

        try {
            InputStream inputStream = mContext.getAssets().open("cars/car_ownsers_data.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            CSVReader csvReader = new CSVReader(reader);

            //step over headers
            csvReader.readNext();

            String[] tokens;
            while ((tokens = csvReader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                Log.d("Car Owner details -->", tokens[0]);

                CarOwner carOwner = new CarOwner(Integer.parseInt(tokens[0]), tokens[1], tokens[2],
                        tokens[3], tokens[4], tokens[5], Integer.parseInt(tokens[6]), tokens[7],
                        tokens[8], tokens[9], tokens[10]);

                carOwners.add(carOwner);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Single.just(carOwners).subscribeOn(Schedulers.newThread());
    }

}