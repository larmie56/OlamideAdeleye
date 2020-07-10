package com.ehealth4everyone.olamideadeleye.util;

import java.util.List;

public class StringUtil {

    public static String formatDateRange(int startYear, int endYear) {
        String dataRange = "Date Range: ";
        if ((startYear == 0 && endYear == 0)) {
            return dataRange + "ALL";
        }
        return dataRange + startYear + " - " + endYear;
    }

    public static String formatCountries(List<String> countries){
        String formattedCountries = "Countries: ";
        if (countries.isEmpty())
            return formattedCountries + "ALL";
        StringBuilder countriesString = new StringBuilder();

        for (int index = 0; index < countries.size(); index++) {
            if (!(index == countries.size() - 1))
                countriesString.append(countries.get(index)).append(", ");
            else
                countriesString.append(countries.get(index));
        }

        return formattedCountries + countriesString.toString();
    }

    public static String formatColors(List<String> colors) {
        String formattedColors = "Colors: ";
        if (colors.isEmpty())
            return formattedColors + "ALL";
        StringBuilder colorsString = new StringBuilder();

        for (int index = 0; index < colors.size(); index++) {
            if (!(index == colors.size() - 1))
                colorsString.append(colors.get(index)).append(", ");
            else
                colorsString.append(colors.get(index));
        }

        return formattedColors + colorsString.toString();
    }

    public static String formatGender(String gender) {
        String formattedGender = "Gender: ";
        if (gender.equals(""))
            return formattedGender + "ALL";
        return formattedGender + gender;
    }

    public static String formatFullName(String firstName, String lastName) {
        String formattedFullName = "Full Name: ";

        return formattedFullName + firstName + " " + lastName;
    }

    public static String formatEmail(String email) {
        String formattedEmail = "Email: ";

        return formattedEmail + email;
    }

    public static String formatountry(String country) {
        String formattedCountry = "Country: ";

        return formattedCountry + country;
    }

    public static String formatCarMakeColorYear(String carMake, String color, int year) {
        String formattedCarMakeColorYear = "Car Make, Color and Year: ";

        return formattedCarMakeColorYear + color + " " + year + " " + carMake;
    }

    public static String formatJobTitle(String jobTitle) {
        String formattedJobTitle = "Job Title: ";

        return formattedJobTitle + jobTitle;
    }

    public static String formatBio(String bio) {
        String formattedBio = "Bio: ";

        return formattedBio + bio;
    }
}
