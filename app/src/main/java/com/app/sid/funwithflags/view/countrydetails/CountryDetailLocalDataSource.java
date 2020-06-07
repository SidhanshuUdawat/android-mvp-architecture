package com.app.sid.funwithflags.view.countrydetails;

import com.app.sid.funwithflags.datasets.remote.Country;
import com.app.sid.funwithflags.datasets.remote.SelectedCountry;
import com.app.sid.funwithflags.realm.RealmManager;

import rx.Observable;

/**
 * Created by Sidhanshu Udawat on 19-Jan-18.
 */

public class CountryDetailLocalDataSource implements CountryDetailMvp.LocalDataSource {

    public CountryDetailLocalDataSource(RealmManager realmManager) {
    }

    @Override
    public Observable<Country> getCountry(SelectedCountry country) {
        return null;
    }
}
