package com.app.sid.funwithflags.data;

import android.content.Context;

import com.app.sid.funwithflags.datasets.remote.CountryDTO;
import com.google.common.collect.Lists;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Sidhanshu Udawat on 26-May-17.
 */
public class DashboardRepositoryTest {

    private final static String COUNTRY_TITLE = "India";

    private final static String COUNTRY_TITLE2 = "United Kingdom";

    private final static String COUNTRY_TITLE3 = "Japan";


    private static List<CountryDTO> COUNTRIES = Lists.newArrayList(new CountryDTO("India", "IN"),
            new CountryDTO("United Kingdom", "UK"));

    private DashboardRepository mCountriesRepository;

    private TestSubscriber<List<CountryDTO>> mCountriesTestSubscriber;

    @Mock
    private DashboardDataSource mCountriesRemoteDataSource;

    @Mock
    private DashboardDataSource mCountriesLocalDataSource;

    @Mock
    private Context mContext;

    @Before
    public void setupCountiresRepository() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        mCountriesRepository = DashboardRepository.getInstance(
                mCountriesRemoteDataSource, mCountriesLocalDataSource);

        mCountriesTestSubscriber = new TestSubscriber<>();
    }

    @After
    public void destroyRepositoryInstance() {
        DashboardRepository.destroyInstance();
    }

    @Test
    public void getCountires_repositoryCachesAfterFirstSubscription_whenCountiresAvailableInLocalStorage() {
        // Given that the local data source has data available
        setCountryAvailable(mCountriesLocalDataSource, COUNTRIES);
        // And the remote data source does not have any data available
        setCountryNotAvailable(mCountriesRemoteDataSource);

        // When two subscriptions are set
        TestSubscriber<List<CountryDTO>> testSubscriber1 = new TestSubscriber<>();
        mCountriesRepository.fetchCountries().subscribe(testSubscriber1);

        TestSubscriber<List<CountryDTO>> testSubscriber2 = new TestSubscriber<>();
        mCountriesRepository.fetchCountries().subscribe(testSubscriber2);

        verify(mCountriesLocalDataSource).fetchCountries();
        testSubscriber1.assertValue(COUNTRIES);
        testSubscriber2.assertValue(COUNTRIES);
    }

    @Test
    public void getCountire_requestsAllCountiresFromLocalDataSource() {
        // Given that the local data source has data available
        setCountryAvailable(mCountriesLocalDataSource, COUNTRIES);
        // And the remote data source does not have any data available
        setCountryNotAvailable(mCountriesRemoteDataSource);

        mCountriesRepository.fetchCountries().subscribe(mCountriesTestSubscriber);

        verify(mCountriesLocalDataSource).fetchCountries();
        mCountriesTestSubscriber.assertValue(COUNTRIES);
    }

    @Test
    public void saveCountire_savesCountireToServiceAPI() {

        CountryDTO newCountry = new CountryDTO(COUNTRY_TITLE, "CACODE");
        mCountriesRepository.saveCountry(newCountry);
        verify(mCountriesLocalDataSource).saveCountry(newCountry);
    }


    private void setCountryNotAvailable(DashboardDataSource dataSource) {
        when(dataSource.fetchCountries()).thenReturn(Observable.just(Collections.<CountryDTO>emptyList()));
    }

    private void setCountryAvailable(DashboardDataSource dataSource, List<CountryDTO> country) {
        // don't allow the data sources to complete.
        when(dataSource.fetchCountries()).thenReturn(Observable.just(country).concatWith(Observable.<List<CountryDTO>>never()));
    }

    private void setCountryNotAvailable(DashboardDataSource dataSource, CountryDTO country) {
        when(dataSource.fetchCountry(eq(country))).thenReturn(Observable.<CountryDTO>just(null).concatWith(Observable.<CountryDTO>never()));
    }

    private void setCountryAvailable(DashboardDataSource dataSource, CountryDTO country) {
        when(dataSource.fetchCountry(eq(country))).thenReturn(Observable.just(country).concatWith(Observable.<CountryDTO>never()));
    }

}