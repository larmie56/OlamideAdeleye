package com.ehealth4everyone.olamideadeleye;

import com.ehealth4everyone.olamideadeleye.models.CarOwner;
import com.ehealth4everyone.olamideadeleye.models.Filter;

import java.util.ArrayList;
import java.util.List;

public class TestDataFactory {
    public Filter getTest1Filter() {
        return new Filter(1, 1991, 2001, "male", getCountries(), getColors());
    }

    public Filter getTest2Filter() {
        return new Filter(1, 2001, 2014, "female", getEmptyCountries(), getEmptyColors());
    }

    public Filter getEmptyColorsFilter() {
        return new Filter(1, 1990, 2010, "", getCountries3(), getEmptyColors());
    }

    public Filter getEmptyCountriesAndColorsFilter() {
        return new Filter(1, 1990, 2010, "male", getEmptyCountries(), getEmptyColors());
    }

    public Filter getEmptyCountriesFilter() {
        return new Filter(1, 1990, 2000, "female", getEmptyCountries(), getColors5());
    }
    public List<Filter> getFilters() {
        List<Filter> filters = new ArrayList<>();
        filters.add(getTest1Filter());
        filters.add(getTest2Filter());
        filters.add(getEmptyColorsFilter());
        filters.add(getEmptyCountriesAndColorsFilter());
        filters.add(getEmptyCountriesFilter());


        return filters;
    }

    public List<CarOwner> getTest1FilteredCarOwners() {
        List<CarOwner> filteredList = new ArrayList<>();

        CarOwner carOwner7 = new CarOwner(7, "Sydner", "Talker",
                "sydnertalker@yahoo.com", "Poland", "Benz",
                2000, "Violet", "Male", "Accountant", "");

        filteredList.add(carOwner7);

        return filteredList;
    }

    public List<CarOwner> getTest2FilteredCarOwners() {
        List<CarOwner> filteredList = new ArrayList<>();

        CarOwner carOwner3 = new CarOwner(3, "Jada", "Smith",//2
                "jadasmith@yahoo.com", "Canada", "Ford",
                2004, "Pink", "Female", "Celebrity", "");

        filteredList.add(carOwner3);

        return filteredList;
    }

    public List<CarOwner> getEmptyColorsFilteredCarOwners() {
        List<CarOwner> filteredList = new ArrayList<>();

        CarOwner carOwner1 = new CarOwner(1, "Tom", "Ford",
                "tomford@yahoo.com", "United States", "BMW",
                1999, "Red", "Male", "Musician", "");

        filteredList.add(carOwner1);

        return filteredList;
    }

    public List<CarOwner> getEmptyCountriesAndColorsFilteredCarOwners() {
        List<CarOwner> filteredList = new ArrayList<>();

        CarOwner carOwner1 = new CarOwner(1, "Tom", "Ford",
                "tomford@yahoo.com", "United States", "BMW",//1
                1999, "Red", "Male", "Musician", "");

        CarOwner carOwner2 = new CarOwner(2, "Jack", "Bauer",//2
                "jackbauer@yahoo.com", "Cuba", "Crysler",
                2010, "Green", "Male", "Actor", "");

        CarOwner carOwner7 = new CarOwner(7, "Sydner", "Talker", //6
                "sydnertalker@yahoo.com", "Poland", "Benz",
                2000, "Violet", "Male", "Accountant", "");

        filteredList.add(carOwner1);
        filteredList.add(carOwner2);
        filteredList.add(carOwner7);

        return filteredList;
    }

    public List<CarOwner> getEmptyCountriesFilteredCarOwners() {
        List<CarOwner> filteredList = new ArrayList<>();

        CarOwner carOwner5 = new CarOwner(5, "Xi", "Lee",
                "xilee@yahoo.com", "Japan", "Honda",
                1990, "Aquamarine", "Female", "Sales Executive", "");

        filteredList.add(carOwner5);

        return filteredList;
    }

    private List<String> getCountries() {
        List<String> countries = new ArrayList<>();

        countries.add("Brazil");
        countries.add("Ireland");
        countries.add("Egypt");
        countries.add("Poland");
        countries.add("Niger");
        countries.add("Greece");
        countries.add("Peru");

        return countries;
    }

    private List<String> getColors() {
        List<String> colors = new ArrayList<>();

        colors.add("Green");
        colors.add("Violet");
        colors.add("Yellow");
        colors.add("Blue");

        return colors;
    }

    private List<String> getCountries3() {
        List<String> countries = new ArrayList<>();
        countries.add("United States");
        return countries;
    }
    private List<String> getColors5() {
        List<String> colors = new ArrayList<>();
        colors.add("Aquamarine");
        return colors;
    }

    private List<String> getEmptyColors() {
        return new ArrayList<>();
    }

    private List<String> getEmptyCountries() {
        return new ArrayList<>();
    }

    public List<CarOwner> getCarOwners() {
        List<CarOwner> carOwners = new ArrayList<>();

        CarOwner carOwner1 = new CarOwner(1, "Tom", "Ford",
                "tomford@yahoo.com", "United States", "BMW",
                1999, "Red", "Male", "Musician", "");

        CarOwner carOwner2 = new CarOwner(2, "Jack", "Bauer",
                "jackbauer@yahoo.com", "Cuba", "Crysler",
                2010, "Green", "Male", "Actor", "");

        CarOwner carOwner3 = new CarOwner(3, "Jada", "Smith",
                "jadasmith@yahoo.com", "Canada", "Ford",
                2004, "Pink", "Female", "Celebrity", "");

        CarOwner carOwner4 = new CarOwner(4, "Austin", "Okocha",
                "austinokach@yahoo.com", "Niger", "Toyota",
                2014, "Green", "Male", "Footballer", "");

        CarOwner carOwner5 = new CarOwner(5, "Xi", "Lee",
                "xilee@yahoo.com", "Japan", "Honda",
                1990, "Aquamarine", "Female", "Sales Executive", "");

        CarOwner carOwner6 = new CarOwner(6, "Igne", "Cloney",
                "ignecloney", "Argentina", "Volkswagen",
                1996, "Muav", "Female", "Engineer", "");

        CarOwner carOwner7 = new CarOwner(7, "Sydner", "Talker",
                "sydnertalker@yahoo.com", "Poland", "Benz",
                2000, "Violet", "Male", "Accountant", "");

        carOwners.add(carOwner1);
        carOwners.add(carOwner2);
        carOwners.add(carOwner3);
        carOwners.add(carOwner4);
        carOwners.add(carOwner5);
        carOwners.add(carOwner6);
        carOwners.add(carOwner7);

        return carOwners;
    }
}
