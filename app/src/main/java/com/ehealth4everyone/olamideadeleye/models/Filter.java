package com.ehealth4everyone.olamideadeleye.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Filter implements Parcelable {

    public static String TAG = "Filter";

    private int id;
    private int startYear;
    private int endYear;
    private String gender;
    private List<String> countries;
    private List<String> colors;

    public Filter(int id, int startYear, int endYear, String gender, List<String> countries, List<String> colors) {
        this.id = id;
        this.startYear = startYear;
        this.endYear = endYear;
        this.gender = gender;
        this.countries = countries;
        this.colors = colors;
    }

    protected Filter(Parcel in) {
        id = in.readInt();
        startYear = in.readInt();
        endYear = in.readInt();
        gender = in.readString();
        countries = in.createStringArrayList();
        colors = in.createStringArrayList();
    }

    public static final Creator<Filter> CREATOR = new Creator<Filter>() {
        @Override
        public Filter createFromParcel(Parcel in) {
            return new Filter(in);
        }

        @Override
        public Filter[] newArray(int size) {
            return new Filter[size];
        }
    };

    public int getId() {
        return id;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public String getGender() {
        return gender;
    }

    public List<String> getCountries() {
        return countries;
    }

    public List<String> getColors() {
        return colors;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(startYear);
        parcel.writeInt(endYear);
        parcel.writeString(gender);
        parcel.writeStringList(countries);
        parcel.writeStringList(colors);
    }
}
