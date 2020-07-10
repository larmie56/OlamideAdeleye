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
}
