package com.ehealth4everyone.olamideadeleye.filter_model;

import java.util.List;

public class Filter {

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
}
