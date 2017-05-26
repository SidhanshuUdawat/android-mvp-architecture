package com.app.sid.funwithflags.view.wikiview;

import android.widget.ImageView;

import com.app.sid.funwithflags.datasets.remote.CountryDTO;
import com.app.sid.funwithflags.view.base.BasePresenter;
import com.app.sid.funwithflags.view.base.BaseView;

public interface WikiContract {

    interface View extends BaseView<Presenter> {
        void showProgress();

        void hideProgress();

        boolean isActive();

        boolean isGoBack();

    }

    interface Presenter extends BasePresenter {

        boolean isGoBack();
    }
}
