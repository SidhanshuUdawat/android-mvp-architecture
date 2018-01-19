package com.app.sid.funwithflags.view.countrydetails;

import android.widget.ImageView;

import com.app.sid.funwithflags.datasets.remote.CountryDTO;
import com.app.sid.funwithflags.datasets.remote.SelectedCountry;
import com.app.sid.funwithflags.view.base.BasePresenter;
import com.app.sid.funwithflags.view.base.BaseView;

import rx.Observable;

public interface CountryDetailMvp {

    interface View {

        void showHeaderImageProgress(boolean isShown);

        void loadCachedFlag(String url);

        void loadFlag(String url);

        void setContinent(String value);

        void setSubRegion(String value);

        void setCapital(String value);

        void setTerritory(String value);

        void setPopulation(String value);

        void setNativeName(String value);

        void setLanguage(String value);

        void setCurrency(String value);

        void setDomain(String value);

        void setPinCode(String value);

        void showWikiPage(String countryName);

        void showMap(String countryName);
    }

    interface Interactor {
        Observable<CountryDTO> getCountry(SelectedCountry country);
    }

    interface LocalDataSource {
        Observable<CountryDTO> getCountry(SelectedCountry country);
    }
}
