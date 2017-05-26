package com.app.sid.funwithflags.view;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.app.sid.funwithflags.R;
import com.app.sid.funwithflags.view.countrylist.CountryListActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by sidhanshu.udawat
 */
@RunWith(AndroidJUnit4.class)
public class SplashTest2 {

    @Rule
    public ActivityTestRule<Splash> rule = new ActivityTestRule<>(Splash.class);

    public Splash splashActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation()
            .addMonitor(CountryListActivity.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        splashActivity = rule.getActivity();
    }


    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        // To check if app is prod or mock - if mock then test will fail.
        assertEquals("com.app.sid.funwithflags", appContext.getPackageName());
    }

    @Test
    public void ensureViewIsPresent() throws Exception {
        View viewById = splashActivity.findViewById(R.id.splash_image);
        assertNotNull(viewById);
    }

    @Test
    public void showCountryList() {
        splashActivity.showCountryList();
        Activity countriesActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 500);
        assertNotNull(countriesActivity);
        countriesActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        splashActivity = null;
    }

}