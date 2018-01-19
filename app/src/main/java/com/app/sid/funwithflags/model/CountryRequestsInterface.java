package com.app.sid.funwithflags.model;

import com.app.sid.funwithflags.datasets.remote.CountryDTO;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Sidhanshu Udawat on 19-Jan-18.
 */

public interface CountriesRequestsInterface {

    @GET("all")
    Observable<CountryDTO> getCountries();
}
