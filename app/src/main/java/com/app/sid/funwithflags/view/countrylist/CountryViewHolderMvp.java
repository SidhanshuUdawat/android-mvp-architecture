package com.app.sid.funwithflags.view.countrylist;

import com.app.sid.funwithflags.datasets.remote.Country;

/**
 * Created by Sidhanshu Udawat on 20-Jan-18.
 */

public interface CountryViewHolderMvp {

    interface View {

        void setCountryCapital(String value);

        void setCountryName(String value);

        void showProgress(boolean isShown);

        void setCachedFlagImage(String url);

        void setFlagImage(String url);

        void onCountryClicked(Country object, int pos);
    }
}
