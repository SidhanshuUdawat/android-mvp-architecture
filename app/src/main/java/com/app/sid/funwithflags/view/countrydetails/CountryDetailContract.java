package com.app.sid.funwithflags.view.countrydetails;

import android.widget.ImageView;

import com.app.sid.funwithflags.datasets.remote.CountryDTO;
import com.app.sid.funwithflags.view.base.BasePresenter;
import com.app.sid.funwithflags.view.base.BaseView;

public interface CountryDetailContract {

    interface View extends BaseView<Presenter> {
        void showProgress();

        void hideProgress();

        void stopImageProgress();

        boolean isActive();

        void setupView(CountryDTO countryDTO);
    }

    interface Presenter extends BasePresenter {

        void fetchCountry(String cname, String alphaCode);

        void loadImage(ImageView mView, String alphaCode);
    }
}
