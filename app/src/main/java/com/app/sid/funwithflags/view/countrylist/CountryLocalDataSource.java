package com.app.sid.funwithflags.view.countrylist;

import android.support.annotation.NonNull;

import com.app.sid.funwithflags.constants.AppConst;
import com.app.sid.funwithflags.data.database.loader.CountriesDataLoader;
import com.app.sid.funwithflags.data.database.schema.CountriesTableSchema;
import com.app.sid.funwithflags.datasets.remote.CountryDTO;

import java.util.List;

import rx.Observable;

/**
 * Created by Sidhanshu Udawat on 19-Jan-18.
 */

public class CountryLocalDataSource implements CountryListMvp.LocalDataSource {

    @NonNull
    private final CountriesDataLoader countriesDataLoader;

    public CountryLocalDataSource() {
        countriesDataLoader = new CountriesDataLoader();
    }

    @Override
    public boolean isLocalDataPresent() {
        return countriesDataLoader.read().size() > 0;
    }

    @Override
    public Observable<List<CountryDTO>> getCountryList() {
        return countriesDataLoader.getAllCountries();
    }

    @Override
    public void saveCountry(CountryDTO country) {
        StringBuilder languages = new StringBuilder();
        StringBuilder currencies = new StringBuilder();
        StringBuilder domain = new StringBuilder();
        StringBuilder callingCode = new StringBuilder();

        if (country.getLanguages() != null) {
            for (int i = 0; i < country.getLanguages().size(); i++) {
                if (languages.length() > 0) {
                    languages.append(", ").append(country.getLanguages().get(i).getName())
                            .append(" / ").append(country.getLanguages().get(i).getNativeName());
                } else {
                    languages.append(country.getLanguages().get(i).getName())
                            .append(" / ").append(country.getLanguages().get(i).getNativeName());
                }
            }
        }

        if (country.getCurrencies() != null) {
            for (int i = 0; i < country.getCurrencies().size(); i++) {
                if (currencies.length() > 0) {
                    currencies.append(", ").append(country.getCurrencies().get(i).getName())
                            .append(" - ").append(country.getCurrencies().get(i).getSymbol());
                } else {
                    currencies.append(country.getCurrencies().get(i).getName())
                            .append(" - ").append(country.getCurrencies().get(i).getSymbol());
                }
            }
        }

        if (country.getTopLevelDomain() != null) {
            for (int i = 0; i < country.getTopLevelDomain().size(); i++) {
                if (domain.length() > 0) {
                    domain.append(", ").append(country.getTopLevelDomain().get(i));

                } else {
                    domain.append(country.getTopLevelDomain().get(i));
                }
            }
        }

        if (country.getCallingCodes() != null) {
            for (int i = 0; i < country.getCallingCodes().size(); i++) {
                if (callingCode.length() > 0) {
                    callingCode.append(", ").append(country.getCallingCodes().get(i));

                } else {
                    callingCode.append(country.getCallingCodes().get(i));
                }
            }
        }


        if (languages.length() > 0) {
            country.setLang(languages.toString());
        } else {
            country.setLang(AppConst.NA);
        }

        if (currencies.length() > 0) {
            country.setCurrency(currencies.toString());
        } else {
            country.setCurrency(AppConst.NA);
        }

        if (domain.length() > 0) {
            country.setDomain(domain.toString());
        } else {
            country.setDomain(AppConst.NA);
        }

        if (callingCode.length() > 0) {
            country.setPhonecode(callingCode.toString());
        } else {
            country.setPhonecode(AppConst.NA);
        }

        countriesDataLoader.insert(country, CountriesTableSchema.TABLE);
    }
}
