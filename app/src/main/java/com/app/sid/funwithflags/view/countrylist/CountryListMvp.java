package com.app.sid.funwithflags.view.countrylist;

import com.app.sid.funwithflags.datasets.remote.CountryDTO;

import java.util.List;

import rx.Observable;

public interface CountryListMvp {

    interface View {
        void showProgress(boolean isVisible);

        void showInternetError(boolean isVisible);

        boolean isInternetAvailable();

        void updateCountries(List<CountryDTO> countryDTOs);
    }

    interface Interactor {
        Observable<List<CountryDTO>> getCountryList();
    }

    interface RemoteDataSource {
        Observable<List<CountryDTO>> getCountryList();
    }

    interface LocalDataSource {
        boolean isLocalDataPresent();
        Observable<List<CountryDTO>> getCountryList();
        void saveCountry(CountryDTO country);
    }
}
