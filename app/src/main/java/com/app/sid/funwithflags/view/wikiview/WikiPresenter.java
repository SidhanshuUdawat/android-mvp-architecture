package com.app.sid.funwithflags.view.wikiview;

import androidx.annotation.NonNull;

public class WikiPresenter implements WikiContract.Presenter {

    private final WikiContract.View mView;

    public WikiPresenter(@NonNull WikiContract.View view) {
        mView = view;
    }

    @Override
    public boolean isGoBack() {
        return mView.isGoBack();
    }
}
