package com.app.sid.funwithflags.view.wikiview;

public interface WikiContract {

    interface View {
        void showProgress();

        void hideProgress();

        boolean isActive();

        boolean isGoBack();
    }

    interface Presenter {
        boolean isGoBack();
    }
}
