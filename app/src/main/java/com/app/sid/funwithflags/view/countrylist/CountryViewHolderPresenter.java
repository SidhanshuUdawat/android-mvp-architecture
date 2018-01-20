package com.app.sid.funwithflags.view.countrylist;

import com.app.sid.funwithflags.datasets.remote.CountryDTO;

/**
 * Created by Sidhanshu Udawat on 20-Jan-18.
 */

public class CountryViewHolderPresenter {

    private static String FLAG_URL = "http://www.geonames.org/flags/l/%s.gif";
    private CountryViewHolderMvp.View mView;
    private CountryDTO mCountry;
    private String mFlagURL;

    public CountryViewHolderPresenter(CountryViewHolderMvp.View view) {
        this.mView = view;
    }

    public void bind(CountryDTO country) {
        mCountry = country;
        mFlagURL = String.format(FLAG_URL, mCountry.getAlpha2Code().toLowerCase());
        setCountry();
        setCapital();
        loadCachedFlag();
    }

    public void setCountry() {
        mView.setCountryName(mCountry.getName());
    }

    public void setCapital() {
        mView.setCountryCapital(mCountry.getCapital());
    }

    public void onCountryClicked(int pos) {
        mView.onCountryClicked(mCountry, pos);
    }

    public void onFlagLoadedSuccessfully() {
        mView.showProgress(false);
    }

    public void loadCachedFlag() {
        mView.showProgress(true);
        mView.setCachedFlagImage(mFlagURL);
    }

    public void onCachedFlagLoadingFailed() {
        mView.setFlagImage(mFlagURL);
    }

    public void onFlagLoadingFailed() {
        mView.showProgress(false);
    }
}
