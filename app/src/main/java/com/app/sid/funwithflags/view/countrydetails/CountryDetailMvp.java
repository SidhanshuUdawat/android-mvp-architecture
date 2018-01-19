package com.app.sid.funwithflags.view.countrydetails;

import android.widget.ImageView;

import com.app.sid.funwithflags.datasets.remote.CountryDTO;
import com.app.sid.funwithflags.view.base.BasePresenter;
import com.app.sid.funwithflags.view.base.BaseView;

import rx.Observable;

public interface CountryDetailContract {

    interface View {
        void showProgress();

        void hideProgress();

        void stopImageProgress();

        boolean isActive();

        void setupView(CountryDTO countryDTO);
    }

    interface Interactor {
        Observable<CountryDTO> getCountry(CountryDTO country);
    }

    interface LocalDataSource {
        Observable<CountryDTO> getCountry(CountryDTO country);
    }
}
