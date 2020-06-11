package com.app.sid.funwithflags.view.countrylist

import com.app.sid.funwithflags.datasets.remote.Country
import com.app.sid.funwithflags.view.countrylist.CountryListMvp.RemoteDataSource
import rx.Observable
import javax.inject.Inject

internal class CountryListInteractor @Inject constructor(
        private val mLocalDataSource: CountryListMvp.LocalDataSource,
        private val mRemoteDataSource: RemoteDataSource
) : CountryListMvp.Interactor {

    override fun getCountryList(): Observable<List<Country>>? = if (mLocalDataSource.isLocalDataPresent()) {
        mLocalDataSource.getCountryList()
    } else {
        mRemoteDataSource.getCountryList()?.also { observable ->
            saveCountry(observable)
        }
    }

    private fun saveCountry(observable: Observable<List<Country>>) {
        observable.map { countries ->
            countries?.map { country ->
                mLocalDataSource.saveCountry(country)
            }
        }
    }
}