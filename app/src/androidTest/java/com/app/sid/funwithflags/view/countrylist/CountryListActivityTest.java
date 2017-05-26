package com.app.sid.funwithflags.view.countrylist;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.app.sid.funwithflags.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by sidhanshu.udawat
 */
public class CountryListActivityTest {

    @Rule
    public ActivityTestRule<CountryListActivity> rule = new ActivityTestRule<>(CountryListActivity.class);

    public CountryListActivity activity = null;

    public CountryListFragment mFragment = null;

    @Before
    public void setUp() throws Exception {
        activity = rule.getActivity();
        this.rule.launchActivity(new Intent());
        this.mFragment = ((CountryListFragment) this.rule.getActivity()
                .getSupportFragmentManager().findFragmentById(R.id.contentFrame));
        this.mFragment.setPresenter(Mockito.mock(CountryListPresenter.class));
    }

    @Test
    public void checkViewVisible() {
        View viewById = activity.findViewById(R.id.contentFrame);
        assertNotNull(viewById);
    }

    @Test
    public void attachFragment() {
        assertNotNull(this.mFragment);
    }
    

    @After
    public void tearDown() throws Exception {
        activity = null;
    }

}