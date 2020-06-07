package com.app.sid.funwithflags.view.countrylist;

import com.app.sid.funwithflags.datasets.remote.Country;
import com.app.sid.funwithflags.model.realm.RealmCountry;
import com.app.sid.funwithflags.realm.RealmManager;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

public class CountryLocalDataSource implements CountryListMvp.LocalDataSource {

    private RealmManager mRealManager;

    public CountryLocalDataSource(RealmManager realmManager) {
        mRealManager = realmManager;
    }

    @Override
    public boolean isLocalDataPresent() {
        return !mRealManager.getRealm().isEmpty();
    }

    @Override
    public Observable<List<Country>> getCountryList() {
        List<Country> mCountries = new ArrayList<>();
        Realm realm = mRealManager.getRealm();
        try {
            RealmResults<RealmCountry> realmCountry = realm.where(RealmCountry.class).findAll();
            if (realmCountry != null) {
                for (int i = 0; i < realmCountry.size(); i++) {
                    mCountries.add(realmCountry.get(i).asApiModel());
                }
            }
            return Observable.just(mCountries);
        } finally {
            mRealManager.closeRealm(realm);
        }
    }

    @Override
    public void saveCountry(Country country) {
        Realm realm = mRealManager.getRealm();
        try {
            realm.executeTransaction(transaction -> {
                RealmCountry realmCountry = transaction.where(RealmCountry.class)
                        .contains("name", country.getName())
                        .findFirst();
                if (realmCountry == null) {
                    RealmCountry realmCountryToStore = new RealmCountry(country);
                    transaction.copyToRealmOrUpdate(realmCountryToStore);
                }
            });
        } finally {
            mRealManager.closeRealm(realm);
        }
    }
}
