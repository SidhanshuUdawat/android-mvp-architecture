package com.app.sid.funwithflags.view.countrylist;

import com.app.sid.funwithflags.datasets.remote.Countries;

import java.util.List;

import rx.Observable;

public interface CountryListMvp {

    interface View {
        void showProgress(boolean isVisible);

        void showInternetError(boolean isVisible);

        boolean isInternetAvailable();

        void updateCountries(List<Countries> countries);

        void onQueryTextSubmit(String query);

        void onQueryTextChange(String query);
    }

    interface Interactor {
        Observable<List<Countries>> getCountryList();
    }

    interface RemoteDataSource {
        Observable<List<Countries>> getCountryList();
    }

    interface LocalDataSource {
        boolean isLocalDataPresent();

        Observable<List<Countries>> getCountryList();

        void saveCountry(Countries country);
    }
}
