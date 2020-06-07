package com.app.sid.funwithflags.view.wikiview;



import androidx.annotation.NonNull;


public class WikiPresenter implements WikiContract.Presenter {


    @NonNull
    private final WikiContract.View mView;

    public WikiPresenter(@NonNull WikiContract.View view) {
        mView = view;
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
