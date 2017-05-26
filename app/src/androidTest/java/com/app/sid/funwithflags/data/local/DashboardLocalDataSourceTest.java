package com.app.sid.funwithflags.data.local;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.app.sid.funwithflags.constants.AppConst;
import com.app.sid.funwithflags.datasets.remote.CountryDTO;
import com.app.sid.funwithflags.utils.schedulers.BaseSchedulerProvider;
import com.app.sid.funwithflags.utils.schedulers.ImmediateSchedulerProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DashboardLocalDataSourceTest {

    private BaseSchedulerProvider mSchedulerProvider;

    private DashboardLocalDataSource mLocalDataSource;

    @Before
    public void setUp() throws Exception {
        DashboardLocalDataSource.destroyInstance();
        mSchedulerProvider = new ImmediateSchedulerProvider();

        mLocalDataSource = DashboardLocalDataSource.getInstance(
                InstrumentationRegistry.getTargetContext(), mSchedulerProvider);
    }

    @Test
    public void testPreConditions() {
        assertNotNull(mLocalDataSource);
    }

    @Test
    public void saveTask_retrievesCountries() {

        final CountryDTO newCountry = new CountryDTO();
        newCountry.setName("India");
        newCountry.setAlpha2Code("in");
        newCountry.setAlpha3Code("in2");
        newCountry.setCapital("Delhi");
        newCountry.setRegion("A");
        newCountry.setSubregion("A2");
        newCountry.setPopulation("12222222");
        newCountry.setDemonym("data");
        newCountry.setArea("Area");
        newCountry.setNativeName("India");
        newCountry.setNumericCode("Data");
        newCountry.setFlag("url");
        newCountry.setPhonecode("na");
        newCountry.setLang("na");
        newCountry.setDomain("na");
        newCountry.setCurrency("na");

        // When saved into the persistent repository
        mLocalDataSource.saveCountry(newCountry);

        // Then the task can be retrieved from the persistent repository
        TestSubscriber<CountryDTO> testSubscriber = new TestSubscriber<>();
        mLocalDataSource.fetchCountry(newCountry).subscribe(testSubscriber);
        testSubscriber.assertValue(newCountry);
    }

    @Test
    public void saveAndDeleteAllCountries() {
        final CountryDTO newCountry = new CountryDTO();

        newCountry.setName("India");
        newCountry.setAlpha2Code("in");
        newCountry.setAlpha3Code("in2");
        newCountry.setCapital("Delhi");
        newCountry.setRegion("A");
        newCountry.setSubregion("A2");
        newCountry.setPopulation("12222222");
        newCountry.setDemonym("data");
        newCountry.setArea("Area");
        newCountry.setNativeName("India");
        newCountry.setNumericCode("Data");
        newCountry.setFlag("url");


        //mLocalDataSource.saveCountry(newCountry);

        // When all tasks are deleted
        mLocalDataSource.deleteAllCountries();

        // Then the task can be retrieved from the persistent repository
        TestSubscriber<List<CountryDTO>> testSubscriber = new TestSubscriber<>();
        mLocalDataSource.fetchCountries().subscribe(testSubscriber);
        List<CountryDTO> result = testSubscriber.getOnNextEvents().get(0);
        assertThat(result.isEmpty(), is(true));
    }

    @After
    public void tearDown() throws Exception {
        mLocalDataSource.deleteAllCountries();
    }

}