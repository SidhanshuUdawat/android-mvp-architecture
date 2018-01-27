package com.app.sid.funwithflags.view.countrydetails;

import android.support.annotation.NonNull;

import com.app.sid.funwithflags.data.database.loader.CountriesDataLoader;
import com.app.sid.funwithflags.datasets.remote.Countries;
import com.app.sid.funwithflags.datasets.remote.SelectedCountry;

import rx.Observable;

/**
 * Created by Sidhanshu Udawat on 19-Jan-18.
 */

public class CountryDetailLocalDataSource implements CountryDetailMvp.LocalDataSource {

    @NonNull
    private final CountriesDataLoader countriesDataLoader;

    public CountryDetailLocalDataSource() {
        countriesDataLoader = new CountriesDataLoader();
    }

    @Override
    public Observable<Countries> getCountry(SelectedCountry country) {
        return countriesDataLoader.getCountry(country);
    }
}
