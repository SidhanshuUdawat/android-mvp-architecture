package com.app.sid.funwithflags.view.countrylist;

import android.support.annotation.NonNull;

import com.app.sid.funwithflags.data.database.loader.CountriesDataLoader;
import com.app.sid.funwithflags.datasets.remote.Country;
import com.app.sid.funwithflags.model.realm.RealmCountry;
import com.app.sid.funwithflags.realm.RealmManager;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by Sidhanshu Udawat on 19-Jan-18.
 */

public class CountryLocalDataSource implements CountryListMvp.LocalDataSource {

    @NonNull
    private final CountriesDataLoader countriesDataLoader;
    private RealmManager mRealManager;

    public CountryLocalDataSource(RealmManager realmManager) {
        countriesDataLoader = new CountriesDataLoader();
        mRealManager = realmManager;
    }

    @Override
    public boolean isLocalDataPresent() {
        return countriesDataLoader.read() != null && (countriesDataLoader.read().size() > 0);
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
        final RealmCountry realmCountryToStore = new RealmCountry(country);
        Realm realm = mRealManager.getRealm();
        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmCountry realmCountry = realm.where(RealmCountry.class).findFirst();
                    if (realmCountry == null) {
                        realm.copyToRealmOrUpdate(realmCountryToStore);
                    }
                }
            });
        } finally {
            mRealManager.closeRealm(realm);
        }
    }
}
