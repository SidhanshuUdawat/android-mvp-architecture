package com.app.sid.funwithflags.view.countrydetails;

import com.app.sid.funwithflags.datasets.remote.Country;
import com.app.sid.funwithflags.datasets.remote.SelectedCountry;
import com.app.sid.funwithflags.model.realm.RealmCountry;
import com.app.sid.funwithflags.realm.RealmManager;

import io.realm.Realm;
import rx.Observable;

public class CountryDetailLocalDataSource implements CountryDetailMvp.LocalDataSource {

    private RealmManager mRealManager;

    public CountryDetailLocalDataSource(RealmManager realmManager) {
        mRealManager = realmManager;
    }

    @Override
    public Observable<Country> getCountry(SelectedCountry country) {
        Realm realm = mRealManager.getRealm();
        try {
            RealmCountry realmCountry = realm.where(RealmCountry.class)
                    .contains("name", country.getName())
                    .findFirst();
            return Observable.just(realmCountry.asApiModel());
        } finally {
            mRealManager.closeRealm(realm);
        }
    }
}
