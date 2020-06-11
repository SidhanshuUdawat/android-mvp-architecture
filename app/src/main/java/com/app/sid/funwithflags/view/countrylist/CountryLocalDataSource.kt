package com.app.sid.funwithflags.view.countrylist

import com.app.sid.funwithflags.datasets.remote.Country
import com.app.sid.funwithflags.model.realm.RealmCountry
import com.app.sid.funwithflags.realm.RealmManager
import io.realm.Realm
import rx.Observable
import java.util.*

class CountryLocalDataSource(private val mRealManager: RealmManager) : CountryListMvp.LocalDataSource {

    override fun isLocalDataPresent(): Boolean {
        return !mRealManager.getRealm().isEmpty
    }

    override fun getCountryList(): Observable<List<Country>> {
        val mCountries = mutableListOf<Country>()
        val realm = mRealManager.getRealm()
        return try {
            realm.where(RealmCountry::class.java).findAll()?.forEach {
                mCountries.add(it.asApiModel())
            }
            Observable.just(mCountries)
        } finally {
            mRealManager.closeRealm(realm)
        }
    }

    override fun saveCountry(country: Country?) {
        val realm = mRealManager.getRealm()
        try {
            realm.executeTransaction { transaction: Realm ->
                val realmCountry = transaction.where(RealmCountry::class.java)
                        .contains("name", country!!.name)
                        .findFirst()
                if (realmCountry == null) {
                    val realmCountryToStore = RealmCountry(country)
                    transaction.copyToRealmOrUpdate(realmCountryToStore)
                }
            }
        } finally {
            mRealManager.closeRealm(realm)
        }
    }

}