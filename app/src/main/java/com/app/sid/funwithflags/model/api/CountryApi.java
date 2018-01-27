package com.app.sid.funwithflags.model.api;

import com.app.sid.funwithflags.datasets.remote.Countries;
import com.app.sid.funwithflags.network.WebApiBase;

import java.util.List;

import rx.Observable;

/**
 * Created by Sidhanshu Udawat on 19-Jan-18.
 */

public class CountryApi extends WebApiBase {

    private CountryRequestsInterface mRequestsService;

    public CountryApi() {
        getRequestsService();
    }

    public Observable<List<Countries>> getCountries() {
        return mRequestsService.getCountries();
    }

    protected void getRequestsService() {
        mRequestsService = mRetrofit.create(CountryRequestsInterface.class);
    }
}