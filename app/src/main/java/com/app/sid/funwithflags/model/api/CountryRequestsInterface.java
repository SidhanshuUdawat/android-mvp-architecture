package com.app.sid.funwithflags.model;

import com.app.sid.funwithflags.datasets.remote.CountryDTO;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Sidhanshu Udawat on 19-Jan-18.
 */

public interface CountryRequestsInterface {

    @GET("all")
    Observable<List<CountryDTO>> getCountries();
}
