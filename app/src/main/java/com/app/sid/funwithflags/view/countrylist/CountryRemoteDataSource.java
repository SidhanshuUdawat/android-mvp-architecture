package com.app.sid.funwithflags.view.countrylist;

import com.app.sid.funwithflags.datasets.remote.Countries;
import com.app.sid.funwithflags.model.api.CountryApi;

import java.util.List;

import rx.Observable;

/**
 * Created by Sidhanshu Udawat on 19-Jan-18.
 */

public class CountryRemoteDataSource implements CountryListMvp.RemoteDataSource {

    public CountryApi mCountryApi;

    public CountryRemoteDataSource(CountryApi countryApi) {
        mCountryApi = countryApi;
    }

    @Override
    public Observable<List<Countries>> getCountryList() {
        return mCountryApi.getCountries();
    }
}
