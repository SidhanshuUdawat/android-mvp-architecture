package com.app.sid.funwithflags.view.countrydetails;


import android.util.Log;

import androidx.annotation.NonNull;

import com.app.sid.funwithflags.datasets.remote.Country;
import com.app.sid.funwithflags.datasets.remote.SelectedCountry;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class CountryDetailPresenter {

    private static final String FLAG_URL = "http://www.geonames.org/flags/x/%s.gif";
    private final CountryDetailMvp.View mView;
    private final CountryDetailMvp.Interactor mInteractor;
    private CompositeSubscription mSubscriptions;
    protected SelectedCountry mSelectedCountry;
    protected String mFlagURL;

    public CountryDetailPresenter(CountryDetailMvp.View view, CountryDetailMvp.Interactor interactor) {
        mView = view;
        mInteractor = interactor;
        mSubscriptions = new CompositeSubscription();
    }


    public void init(SelectedCountry selectedCountry) {
        mSelectedCountry = selectedCountry;
        mFlagURL = String.format(FLAG_URL, selectedCountry.getAlpha2Code().toLowerCase());
        loadCachedFlag();
        getCountry();
    }

    public void getCountry() {
        mSubscriptions.clear();
        Subscription subscription = mInteractor.getCountry(mSelectedCountry)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Country>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Detail", "Error fetching country", e);
                    }

                    @Override
                    public void onNext(Country countryDTO) {
                        loadCountryDetails(countryDTO);
                    }
                });
        mSubscriptions.add(subscription);
    }

    public void loadCountryDetails(Country country) {
        mView.setContinent(country.getRegion());
        mView.setSubRegion(country.getSubregion());
        mView.setCapital(country.getCapital());
        mView.setTerritory(country.getArea());
        mView.setPopulation(country.getPopulation());
        mView.setNativeName(country.getNativeName());
        mView.setLanguage(country.getLang());
        mView.setCurrency(country.getCurrency());
        mView.setDomain(country.getDomain());
        mView.setPinCode(country.getPhonecode());
    }

    public void onWikiClicked() {
        mView.showWikiPage(mSelectedCountry.getName());
    }

    public void onMapClicked() {
        mView.showMap(mSelectedCountry.getName());
    }

    public void onFlagLoadedSuccessfully() {
        mView.showHeaderImageProgress(false);
    }

    public void loadCachedFlag() {
        mView.loadCachedFlag(mFlagURL);
    }

    public void onCachedFlagLoadingFailed() {
        mView.loadFlag(mFlagURL);
    }

    public void onFlagLoadingFailed() {
        mView.showHeaderImageProgress(false);
    }

    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
