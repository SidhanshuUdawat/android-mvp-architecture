package com.app.sid.funwithflags.view.countrylist

import com.app.sid.funwithflags.datasets.remote.Country
import rx.Observable

interface CountryListMvp {
    interface View {
        fun showProgress(isVisible: Boolean)
        fun showInternetError(isVisible: Boolean)
        fun isInternetAvailable(): Boolean
        fun updateCountries(countries: List<Country>)
        fun onQueryTextSubmit(query: String)
        fun onQueryTextChange(query: String)
    }

    interface Interactor {
        fun getCountryList(): Observable<List<Country>>?
    }

    interface RemoteDataSource {
        fun getCountryList(): Observable<List<Country>>?
    }

    interface LocalDataSource {
        fun isLocalDataPresent(): Boolean
        fun getCountryList(): Observable<List<Country>>?
        fun saveCountry(country: Country?)
    }
}