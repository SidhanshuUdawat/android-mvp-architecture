package com.app.sid.fun.withflags.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.app.sid.funwithflags.data.DashboardDataSource;
import com.app.sid.funwithflags.data.database.loader.CountriesDataLoader;
import com.app.sid.funwithflags.data.database.schema.CountriesTableSchema;
import com.app.sid.funwithflags.datasets.DashboardDTO;
import com.app.sid.funwithflags.datasets.remote.CountryDTO;

import java.util.List;

import rx.Observable;

/**
 * Created by sidhanshu.udawat
 */

public class FakeRemoteDataSource implements DashboardDataSource {

    private static FakeRemoteDataSource INSTANCE;

    @NonNull
    private final CountriesDataLoader countriesDataLoader;

    // Prevent direct instantiation.
    private FakeRemoteDataSource(Context context) {
        countriesDataLoader = new CountriesDataLoader(context);
    }


    public static FakeRemoteDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new FakeRemoteDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public Observable<DashboardDTO> downloadCountries() {
        return null;
    }

    @Override
    public Observable<List<CountryDTO>> fetchCountries() {
        return countriesDataLoader.readObserver();
    }

    @Override
    public Observable<CountryDTO> fetchCountry(CountryDTO countryDTO) {
        return countriesDataLoader.readSingleObserver(countryDTO);
    }

    @Override
    public void saveCountry(CountryDTO objCountry) {
        countriesDataLoader.insert(objCountry, CountriesTableSchema.TABLE);
    }

    @Override
    public void deleteAllCountries() {
        countriesDataLoader.delete(CountriesTableSchema.TABLE);
    }

    @Override
    public void deleteCountry(CountryDTO countryDTO) {
        countriesDataLoader.delete(countryDTO);
    }

    @VisibleForTesting
    public void addCountries(CountryDTO... tasks) {
        for (CountryDTO country : tasks) {
            saveCountry(country);
        }
    }
}
