package com.ehealth4everyone.olamideadeleye.util;

import com.ehealth4everyone.olamideadeleye.TestDataFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class StringUtilTest {
    TestDataFactory mTestDataFactory = new TestDataFactory();

    @Test
    public void formatDateRange_test1() {
        int startYear = 1990;
        int endYear = 2000;
        String expectedDateRangeFormat = "Date Range: 1990 - 2000";

        final String actualDateRangeFormat = StringUtil.formatDateRange(startYear, endYear);

        assertNotNull(actualDateRangeFormat);
        assertEquals(expectedDateRangeFormat, actualDateRangeFormat);
    }

    @Test
    public void formatDateRange_test2() {
        int startYear = 0;
        int endYear = 2000;
        String expectedDateRangeFormat = "Date Range: 0 - 2000";

        final String actualDateRangeFormat = StringUtil.formatDateRange(startYear, endYear);

        assertNotNull(actualDateRangeFormat);
        assertEquals(expectedDateRangeFormat, actualDateRangeFormat);
    }

    @Test
    public void formatDateRange_test3() {
        int startYear = 0;
        int endYear = 0;
        String expectedDateRangeFormat = "Date Range: ALL";

        String actualDateRangeFormat = StringUtil.formatDateRange(startYear, endYear);

        assertNotNull(actualDateRangeFormat);
        assertEquals(expectedDateRangeFormat, actualDateRangeFormat);
    }

    @Test
    public void formatCountries_test1() {
        List<String> countries = mTestDataFactory.getCountries();
        String expectedFormattedCountries = "Countries: Brazil, Ireland, Egypt, Poland, Niger, Greece, Peru";

        String actualFormattedCountries = StringUtil.formatCountries(countries);

        assertNotNull(actualFormattedCountries);
        assertEquals(expectedFormattedCountries, actualFormattedCountries);
    }

    @Test
    public void formatCountries_test2() {
        List<String> countries = mTestDataFactory.getSingleCountry();
        String expectedFormattedCountries = "Countries: United States";

        String actualFormattedCountries = StringUtil.formatCountries(countries);

        assertNotNull(actualFormattedCountries);
        assertEquals(expectedFormattedCountries, actualFormattedCountries);
    }

    @Test
    public void formatCountries_test3() {
        List<String> countries = mTestDataFactory.getEmptyCountries();
        String expectedFormattedCountries = "Countries: ALL";

        String actualFormattedCountries = StringUtil.formatCountries(countries);

        assertNotNull(actualFormattedCountries);
        assertEquals(expectedFormattedCountries, actualFormattedCountries);
    }

    @Test
    public void formatColors_test1() {
        List<String> colors = mTestDataFactory.getColors();
        String expectedFormattedColors = "Colors: Green, Violet, Yellow, Blue";

        String actualFormattedColors = StringUtil.formatColors(colors);

        assertNotNull(actualFormattedColors);
        assertEquals(expectedFormattedColors, actualFormattedColors);
    }

    @Test
    public void formatColors_test2() {
        List<String> colors = mTestDataFactory.getSingleColor();
        String expectedFormattedColors = "Colors: Aquamarine";

        String actualFormattedColors = StringUtil.formatColors(colors);


        assertNotNull(actualFormattedColors);
        assertEquals(expectedFormattedColors, actualFormattedColors);
    }

    @Test
    public void formatColors_test3() {
        List<String> colors = mTestDataFactory.getEmptyColors();
        String expectedFormattedColors = "Colors: ALL";

        String actualFormattedColors = StringUtil.formatColors(colors);

        assertNotNull(actualFormattedColors);
        assertEquals(expectedFormattedColors, actualFormattedColors);
    }

    @Test
    public void formatGender_test1() {
        String female = "Female";
        String expectedFormattedGender = "Gender: Female";

        String actualFormattedGender = StringUtil.formatGender(female);

        assertNotNull(actualFormattedGender);
        assertEquals(expectedFormattedGender, actualFormattedGender);
    }

    @Test
    public void formatGender_test2() {
        String gender = "";
        String expectedFormattedGender = "Gender: ALL";

        String actualFormatttedGender = StringUtil.formatGender(gender);

        assertNotNull(actualFormatttedGender);
        assertEquals(expectedFormattedGender, actualFormatttedGender);
    }
}