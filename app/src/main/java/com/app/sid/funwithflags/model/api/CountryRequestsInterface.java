package com.app.sid.funwithflags.model.api;

import com.app.sid.funwithflags.datasets.remote.Countries;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Sidhanshu Udawat on 19-Jan-18.
 */

public interface CountryRequestsInterface {

    @GET("all")
    Observable<List<Countries>> getCountries();
}
