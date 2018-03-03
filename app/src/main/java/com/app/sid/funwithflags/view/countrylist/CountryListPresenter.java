package com.app.sid.funwithflags.view.countrylist;

import android.support.annotation.NonNull;

import com.app.sid.funwithflags.datasets.remote.Country;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Sidhanshu Udawat on 19-Jan-18.
 */

public class CountryListPresenter {

    @NonNull
    private final CountryListMvp.View mView;
    @NonNull
    private CompositeSubscription mSubscriptions;
    @NonNull
    private final CountryListMvp.Interactor mInteractor;

    public CountryListPresenter(@NonNull CountryListMvp.View view, CountryListMvp.Interactor interactor) {
        mView = checkNotNull(view, "view cannot be null!");
        mInteractor = interactor;
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
                    .subscribe(new Subscriber<List<Country>>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            loadingCountriesFailed();
                        }

                        @Override
                        public void onNext(List<Country> countryDTOS) {
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

    private void loadCountries(List<Country> countries) {
        mView.updateCountries(countries);
        mView.showProgress(false);
    }

    public void onQueryTextSubmit(String query) {
        mView.onQueryTextSubmit(query);
    }

    public void onQueryTextChange(String query) {
        mView.onQueryTextChange(query);
    }

    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
