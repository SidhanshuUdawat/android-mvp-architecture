package com.app.sid.funwithflags.view.countrylist;

import com.app.sid.funwithflags.datasets.remote.CountryDTO;
import com.app.sid.funwithflags.view.base.BasePresenter;
import com.app.sid.funwithflags.view.base.BaseView;

import java.util.List;

public interface CountryListContract {

    interface View extends BaseView<Presenter> {
        void showProgress();

        void hideProgress();

        void noInternet();

        void refreshMessages(List<CountryDTO> countryDTOs);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void isDataAvailable();

        void downloadData();

        void fetchCountries();
    }
}
