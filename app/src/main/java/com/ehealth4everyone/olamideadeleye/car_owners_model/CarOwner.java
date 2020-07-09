package com.ehealth4everyone.olamideadeleye.car_owners_model;

public class CarOwner {

    int id;
    String firstName;
    String lastName;
    String email;
    String country;
    String carModel;
    int carModelYear;
    String carColor;
    String gender;
    String jobTitle;
    String bio;

    public CarOwner(int id, String firstName, String lastName, String email, String country, String carModel, int carModelYear, String carColor, String gender, String jobTitle, String bio) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.carModel = carModel;
        this.carModelYear = carModelYear;
        this.carColor = carColor;
        this.gender = gender;
        this.jobTitle = jobTitle;
        this.bio = bio;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public String getCarModel() {
        return carModel;
    }

    public int getCarModelYear() {
        return carModelYear;
    }

    public String getCarColor() {
        return carColor;
    }

    public String getGender() {
        return gender;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getBio() {
        return bio;
    }
}
