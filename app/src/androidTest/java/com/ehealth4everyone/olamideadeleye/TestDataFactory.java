package com.ehealth4everyone.olamideadeleye;

import com.ehealth4everyone.olamideadeleye.models.CarOwner;
import com.ehealth4everyone.olamideadeleye.models.Filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestDataFactory {

    public List<Filter> getFilterList() {
        return Arrays.asList(new Filter(1, 1990, 2010, "male", Arrays.asList("", ""), Arrays.asList("", "")));
    }

    public Filter getFilter() {
        return new Filter(1, 1990, 2010, "male", Arrays.asList("", ""), Arrays.asList("", ""));
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

    public List<CarOwner> getEmptyCarOwnersList() {
        return Arrays.asList();
    }
}
