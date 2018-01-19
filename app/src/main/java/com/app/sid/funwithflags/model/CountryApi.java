package com.app.sid.funwithflags.model;

import com.app.sid.funwithflags.datasets.remote.CountryDTO;
import com.app.sid.funwithflags.network.WebApiBase;

import rx.Observable;

/**
 * Created by Sidhanshu Udawat on 19-Jan-18.
 */

public class CountriesApi  extends WebApiBase {

    private CountriesRequestsInterface mRequestsService;

    public CountriesApi() {
        getRequestsService();
    }

    public Observable<CountryDTO> getCountries() {
        return mRequestsService.getCountries();
    }

    protected void getRequestsService() {
        mRequestsService = mRetrofit.create(CountriesRequestsInterface.class);
    }
}