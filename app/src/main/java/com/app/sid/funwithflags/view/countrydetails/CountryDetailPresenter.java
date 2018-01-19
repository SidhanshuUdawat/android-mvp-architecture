package com.app.sid.funwithflags.view.countrydetails;

import android.support.annotation.NonNull;

import com.app.sid.funwithflags.datasets.remote.CountryDTO;
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
                .subscribe(new Subscriber<CountryDTO>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CountryDTO countryDTO) {
                        loadCountryDetails(countryDTO);
                    }
                });
        mSubscriptions.add(subscription);
    }

    public void loadCountryDetails(CountryDTO countryDTO) {
        mView.setContinent(countryDTO.getRegion());
        mView.setSubRegion(countryDTO.getSubregion());
        mView.setCapital(countryDTO.getCapital());
        mView.setTerritory(countryDTO.getArea());
        mView.setPopulation(countryDTO.getPopulation());
        mView.setNativeName(countryDTO.getNativeName());
        mView.setLanguage(countryDTO.getLang());
        mView.setCurrency(countryDTO.getCurrency());
        mView.setDomain(countryDTO.getDomain());
        mView.setPinCode(countryDTO.getPhonecode());
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
