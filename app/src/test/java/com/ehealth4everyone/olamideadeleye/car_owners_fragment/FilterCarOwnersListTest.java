package com.ehealth4everyone.olamideadeleye.car_owners_fragment;

import com.ehealth4everyone.olamideadeleye.car_owners_model.CarOwner;
import com.ehealth4everyone.olamideadeleye.filter_model.Filter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class FilterCarOwnersListTest {

    @Test
    public void filterCarOwnersListTest_1() {
        //Arrange
        List<CarOwner> carOwners = getCarOwners();
        Filter filter = getFilter();

        //Act
        CarOwnerViewModel carOwnerViewModel = new CarOwnerViewModel(carOwners, filter);
        final List<CarOwner> actualFilteredList = carOwnerViewModel.filterCarOwnersList(carOwners, filter);

        //Assert
        assertEquals(getFilteredList().size(), actualFilteredList.size());
        assertEquals(getFilteredList().get(0).getFirstName(), actualFilteredList.get(0).getFirstName());

    }

    @Test
    public void filterCarOwnersListTest_2() {
        //Arrange
        List<CarOwner> carOwners = getCarOwners();
        Filter filter = getFilter2();

        //Act
        CarOwnerViewModel carOwnerViewModel = new CarOwnerViewModel(carOwners, filter);
        final List<CarOwner> actualFilteredList = carOwnerViewModel.filterCarOwnersList(carOwners, filter);

        //Assert
        assertEquals(getFilteredList2().size(), actualFilteredList.size());
        assertEquals(getFilteredList2().get(0).getFirstName(), actualFilteredList.get(0).getFirstName());

    }

    @Test
    public void filterCarOwnersListTest_3() {
        //Arrange
        List<CarOwner> carOwners = getCarOwners();
        Filter filter = getFilter3();

        //Act
        CarOwnerViewModel carOwnerViewModel = new CarOwnerViewModel(carOwners, filter);
        final List<CarOwner> actualFilteredList = carOwnerViewModel.filterCarOwnersList(carOwners, filter);

        //Assert
        assertEquals(getFilteredList3().size(), actualFilteredList.size());
        assertEquals(getFilteredList3().get(0).getFirstName(), actualFilteredList.get(0).getFirstName());
    }

    @Test
    public void filterCarOwnersListTest_4() {
        //Arrange
        List<CarOwner> carOwners = getCarOwners();
        Filter filter = getFilter4();

        //Act
        CarOwnerViewModel carOwnerViewModel = new CarOwnerViewModel(carOwners, filter);
        final List<CarOwner> actualFilteredList = carOwnerViewModel.filterCarOwnersList(carOwners, filter);

        //Assert
        assertEquals(getFilteredList4().size(), actualFilteredList.size());
        assertEquals(getFilteredList4().get(0).getFirstName(), actualFilteredList.get(0).getFirstName());
        assertEquals(getFilteredList4().get(1).getFirstName(), actualFilteredList.get(1).getFirstName());
        assertEquals(getFilteredList4().get(2).getFirstName(), actualFilteredList.get(2).getFirstName());
    }

    @Test
    public void filterCarOwnersListTest_5() {
        //Arrange
        List<CarOwner> carOwners = getCarOwners();
        Filter filter = getFilter5();

        //Act
        CarOwnerViewModel carOwnerViewModel = new CarOwnerViewModel(carOwners, filter);
        final List<CarOwner> actualFilteredList = carOwnerViewModel.filterCarOwnersList(carOwners, filter);

        //Assert
        assertEquals(getFilteredList5().size(), actualFilteredList.size());
        assertEquals(getFilteredList5().get(0).getFirstName(), actualFilteredList.get(0).getFirstName());
    }

    private Filter getFilter() {
        return new Filter(1, 1991, 2001, "male", getCountries(), getColors());
    }

    private Filter getFilter2() {
        return new Filter(1, 2001, 2014, "female", getEmptyCountries(), getEmptyColors());
    }

    private Filter getFilter3() {
        return new Filter(1, 1990, 2010, "", getCountries3(), getEmptyColors());
    }

    private Filter getFilter4() {
        return new Filter(1, 1990, 2010, "male", getEmptyCountries(), getEmptyColors());
    }

    private Filter getFilter5() {
        return new Filter(1, 1990, 2000, "female", getEmptyCountries(), getColors5());
    }

    private List<CarOwner> getFilteredList() {
        List<CarOwner> filteredList = new ArrayList<>();

        CarOwner carOwner7 = new CarOwner(7, "Sydner", "Talker",
                "sydnertalker@yahoo.com", "Poland", "Benz",
                2000, "Violet", "Male", "Accountant", "");

        filteredList.add(carOwner7);

        return filteredList;
    }

    private List<CarOwner> getFilteredList2() {
        List<CarOwner> filteredList = new ArrayList<>();

        CarOwner carOwner3 = new CarOwner(3, "Jada", "Smith",//2
                "jadasmith@yahoo.com", "Canada", "Ford",
                2004, "Pink", "Female", "Celebrity", "");

        filteredList.add(carOwner3);

        return filteredList;
    }

    private List<CarOwner> getFilteredList3() {
        List<CarOwner> filteredList = new ArrayList<>();

        CarOwner carOwner1 = new CarOwner(1, "Tom", "Ford",
                "tomford@yahoo.com", "United States", "BMW",
                1999, "Red", "Male", "Musician", "");

        filteredList.add(carOwner1);

        return filteredList;
    }

    private List<CarOwner> getFilteredList4() {
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

    private List<CarOwner> getFilteredList5() {
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
    
    private List<CarOwner> getCarOwners() {
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