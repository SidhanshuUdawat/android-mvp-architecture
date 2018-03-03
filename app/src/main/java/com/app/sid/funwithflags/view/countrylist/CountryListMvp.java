package com.app.sid.funwithflags.view.countrylist;

import com.app.sid.funwithflags.datasets.remote.Country;

import java.util.List;

import rx.Observable;

public interface CountryListMvp {

    interface View {
        void showProgress(boolean isVisible);

        void showInternetError(boolean isVisible);

        boolean isInternetAvailable();

        void updateCountries(List<Country> countries);

        void onQueryTextSubmit(String query);

        void onQueryTextChange(String query);
    }

    interface Interactor {
        Observable<List<Country>> getCountryList();
    }

    interface RemoteDataSource {
        Observable<List<Country>> getCountryList();
    }

    interface LocalDataSource {
        boolean isLocalDataPresent();

        Observable<List<Country>> getCountryList();

        void saveCountry(Country country);
    }
}
