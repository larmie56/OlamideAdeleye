package com.ehealth4everyone.olamideadeleye.ui.main_activity;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.ehealth4everyone.olamideadeleye.R;
import com.ehealth4everyone.olamideadeleye.ui.filters_fragment.FilterListFragment;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void verify_fragmentHostExist() {
        onView(withId(R.id.nav_host_fragment))
                .check(matches(isCompletelyDisplayed()));
    }
}