package com.app.sid.funwithflags.view.countrydetails;

import android.support.annotation.NonNull;

import com.app.sid.funwithflags.datasets.remote.Countries;
import com.app.sid.funwithflags.datasets.remote.SelectedCountry;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;


public class CountryDetailPresenter {

    private static final String FLAG_URL = "http://www.geonames.org/flags/x/%s.gif";
    @NonNull
    private final CountryDetailMvp.View mView;
    @NonNull
    private final CountryDetailMvp.Interactor mInteractor;
    @NonNull
    private CompositeSubscription mSubscriptions;
    protected SelectedCountry mSelectedCountry;
    protected String mFlagURL;

    public CountryDetailPresenter(@NonNull CountryDetailMvp.View view, SelectedCountry selectedCountry) {
        mView = checkNotNull(view, "view cannot be null!");
        mInteractor = new CountryDetailInteractor();
        mSubscriptions = new CompositeSubscription();
        mSelectedCountry = selectedCountry;
        mFlagURL = String.format(FLAG_URL, mSelectedCountry.getAlpha2Code().toLowerCase());
    }


    public void init() {
        loadCachedFlag();
        getCountry();
    }

    public void getCountry() {
        mSubscriptions.clear();
        Subscription subscription = mInteractor.getCountry(mSelectedCountry)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Countries>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Countries countryDTO) {
                        loadCountryDetails(countryDTO);
                    }
                });
        mSubscriptions.add(subscription);
    }

    public void loadCountryDetails(Countries countries) {
        mView.setContinent(countries.getRegion());
        mView.setSubRegion(countries.getSubregion());
        mView.setCapital(countries.getCapital());
        mView.setTerritory(countries.getArea());
        mView.setPopulation(countries.getPopulation());
        mView.setNativeName(countries.getNativeName());
        mView.setLanguage(countries.getLang());
        mView.setCurrency(countries.getCurrency());
        mView.setDomain(countries.getDomain());
        mView.setPinCode(countries.getPhonecode());
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
