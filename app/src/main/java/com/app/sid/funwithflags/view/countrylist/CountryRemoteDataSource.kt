package com.app.sid.funwithflags.view.countrylist

import com.app.sid.funwithflags.datasets.remote.Country
import com.app.sid.funwithflags.view.countrylist.CountryListMvp.RemoteDataSource
import rx.Observable
import javax.inject.Inject

internal class CountryRemoteDataSource @Inject constructor() : RemoteDataSource {

    override fun getCountryList(): Observable<List<Country>>? = Observable.just(listOf(Country()))
}