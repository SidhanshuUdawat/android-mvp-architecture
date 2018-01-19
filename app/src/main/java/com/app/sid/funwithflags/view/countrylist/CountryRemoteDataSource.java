package com.app.sid.funwithflags.view.countrylist;

import com.app.sid.funwithflags.datasets.remote.CountryDTO;
import com.app.sid.funwithflags.model.CountryApi;

import java.util.List;

import rx.Observable;

/**
 * Created by Sidhanshu Udawat on 19-Jan-18.
 */

public class CountryRemoteDataSource implements CountryListMvp.RemoteDataSource {

    public CountryApi mCountryApi;

    public CountryRemoteDataSource() {
        mCountryApi = new CountryApi();
    }

    @Override
    public Observable<List<CountryDTO>> getCountryList() {
        return mCountryApi.getCountries();
    }
}
