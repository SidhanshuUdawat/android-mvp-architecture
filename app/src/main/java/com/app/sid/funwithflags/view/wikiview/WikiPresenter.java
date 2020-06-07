package com.app.sid.funwithflags.view.wikiview;



import androidx.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;


public class WikiPresenter implements WikiContract.Presenter {


    @NonNull
    private final WikiContract.View mView;

    public WikiPresenter(@NonNull WikiContract.View view) {
        mView = checkNotNull(view, "view cannot be null!");
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
    }

    @Override
    public boolean isGoBack() {
        return mView.isGoBack();
    }
}
