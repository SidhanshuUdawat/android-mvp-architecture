package com.app.sid.funwithflags.view.countrylist;

import android.support.annotation.NonNull;

import com.app.sid.funwithflags.datasets.remote.CountryDTO;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;


public class CountryListPresenter {

    @NonNull
    private final CountryListMvp.View mView;
    @NonNull
    private CompositeSubscription mSubscriptions;
    @NonNull
    private final CountryListInteractor mInteractor;

    public CountryListPresenter(@NonNull CountryListMvp.View view) {
        mView = checkNotNull(view, "view cannot be null!");
        mInteractor = new CountryListInteractor();
        mSubscriptions = new CompositeSubscription();
    }


    public void onPause() {
        unsubscribe();
    }

    public void init() {
        getCountries();
    }

    public void onRetry() {
        mView.showInternetError(true);
        getCountries();
    }

    public void getCountries() {
        if (!mView.isInternetAvailable()) {
            mView.showInternetError(true);
        } else {
            mView.showProgress(true);
            mSubscriptions.clear();

            Subscription subscription = mInteractor.getCountryList()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Subscriber<List<CountryDTO>>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            loadingCountriesFailed();
                        }

                        @Override
                        public void onNext(List<CountryDTO> countryDTOS) {
                            loadCountries(countryDTOS);
                        }
                    });

            mSubscriptions.add(subscription);
        }
    }

    private void loadingCountriesFailed() {
        mView.showProgress(false);
        mView.showInternetError(true);
    }

    private void loadCountries(List<CountryDTO> countryDTOS) {
        mView.updateCountries(countryDTOS);
        mView.showProgress(false);
    }

    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
