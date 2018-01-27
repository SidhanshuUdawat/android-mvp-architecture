package com.app.sid.funwithflags.view.wikiview;

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
