package com.app.sid.funwithflags.view.countrydetails;

import com.app.sid.funwithflags.datasets.remote.Country;
import com.app.sid.funwithflags.datasets.remote.SelectedCountry;

import rx.Observable;

/**
 * Created by Sidhanshu Udawat on 19-Jan-18.
 */

public class CountryDetailInteractor implements CountryDetailMvp.Interactor {

    private final CountryDetailMvp.LocalDataSource mLocalDataSource;

    public CountryDetailInteractor(CountryDetailMvp.LocalDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    @Override
    public Observable<Country> getCountry(SelectedCountry country) {
        return mLocalDataSource.getCountry(country);
    }
}
