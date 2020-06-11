package com.app.sid.funwithflags.model.api;

import com.app.sid.funwithflags.datasets.remote.Country;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface CountryRequestsInterface {

    @GET("all")
    Observable<List<Country>> getCountries();
}
