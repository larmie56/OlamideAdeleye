package com.ehealth4everyone.olamideadeleye.repo;

import android.content.Context;
import android.util.Log;

import com.ehealth4everyone.olamideadeleye.models.Filter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class FilterRepoImpl implements FilterRepo {

    Context mContext;

    @Inject
    public FilterRepoImpl(Context context) {
        mContext = context;
    }

    @Override
    public String getJsonStringFromAsset(String filename) {
        String json;

        try {
            InputStream inputStream = mContext.getAssets().open(filename);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return json;
    }

    @Override
    public Single<List<Filter>> getFiltersFromJsonString(String string) {
        List<Filter> filters = new ArrayList<>();

        try {
            JSONArray jsonFilterArray = new JSONArray(string);
            Filter filter;

            for (int filterIndex = 0; filterIndex < jsonFilterArray.length(); filterIndex++) {
                //get each filter as a json object
                JSONObject jsonObjectFilter = jsonFilterArray.getJSONObject(filterIndex);

                Log.d("Filter Detail -->", String.valueOf(jsonObjectFilter.getInt("id")));

                filter = new Filter(jsonObjectFilter.getInt("id"),
                        jsonObjectFilter.getInt("start_year"),
                        jsonObjectFilter.getInt("end_year"),
                        jsonObjectFilter.getString("gender"),
                        getCountries(jsonObjectFilter),
                        getColors(jsonObjectFilter));

                filters.add(filter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return Single.just(filters);
    }

    private List<String> getCountries(JSONObject jsonObjectFilter) {
        List<String> countries = new ArrayList<>();
        JSONArray jsonCountryArray;
        try {
            //get the list of countries from the filter json object as a json array
            jsonCountryArray = jsonObjectFilter.getJSONArray("countries");
            //loop through and populate the countries array
            for (int countryIndex = 0; countryIndex < jsonCountryArray.length(); countryIndex++) {
                Log.d("Country -->", jsonCountryArray.getString(countryIndex));
                if (!jsonCountryArray.isNull(countryIndex)) {
                    String country = jsonCountryArray.getString(countryIndex);
                    countries.add(country);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return countries;
    }

    private List<String> getColors(JSONObject jsonObjectFilter) {
        List<String> colors = new ArrayList<>();
        JSONArray jsonColorArray;
        try {
            //get the list of colors from the filter json object as a json array
            jsonColorArray = jsonObjectFilter.getJSONArray("colors");
            //loop through and populate the colors array
            for (int colorIndex = 0; colorIndex < jsonColorArray.length(); colorIndex++) {
                Log.d("Color -->", jsonColorArray.getString(colorIndex));
                if (!jsonColorArray.isNull(colorIndex)) {
                    String color = jsonColorArray.getString(colorIndex);
                    colors.add(color);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return colors;
    }
}
