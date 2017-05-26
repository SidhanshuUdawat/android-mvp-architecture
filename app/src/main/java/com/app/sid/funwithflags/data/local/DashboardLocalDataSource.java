package com.app.sid.funwithflags.data.local;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.app.sid.funwithflags.constants.AppConst;
import com.app.sid.funwithflags.data.DashboardDataSource;
import com.app.sid.funwithflags.data.database.loader.CountriesDataLoader;
import com.app.sid.funwithflags.data.database.schema.CountriesTableSchema;
import com.app.sid.funwithflags.datasets.DashboardDTO;
import com.app.sid.funwithflags.datasets.remote.CountryDTO;
import com.app.sid.funwithflags.utils.schedulers.BaseSchedulerProvider;

import java.util.List;

import rx.Observable;


public class DashboardLocalDataSource implements DashboardDataSource<DashboardDTO> {
    @Nullable
    private static DashboardLocalDataSource INSTANCE;

    @NonNull
    private final CountriesDataLoader countriesDataLoader;

    private DashboardLocalDataSource(@NonNull Context context,
                                     @NonNull BaseSchedulerProvider schedulerProvider) {

        countriesDataLoader = new CountriesDataLoader(context);

    }

    public static DashboardLocalDataSource getInstance(
            @NonNull Context context,
            @NonNull BaseSchedulerProvider schedulerProvider) {
        if (INSTANCE == null) {
            INSTANCE = new DashboardLocalDataSource(context, schedulerProvider);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


    @Override
    public Observable<List<CountryDTO>> downloadCountries() {
        // Do nothing here
        return null;
    }

    @Override
    public Observable<List<CountryDTO>> fetchCountries() {
        return countriesDataLoader.readObserver();
    }

    @Override
    public Observable<CountryDTO> fetchCountry(CountryDTO countryDTO) {
        return countriesDataLoader.readSingleObserver(countryDTO);
    }

    @Override
    public void saveCountry(CountryDTO objCountry) {
        StringBuilder languages = new StringBuilder();
        StringBuilder currencies = new StringBuilder();
        StringBuilder domain = new StringBuilder();
        StringBuilder callingCode = new StringBuilder();

        if (objCountry.getLanguages() != null) {
            for (int i = 0; i < objCountry.getLanguages().size(); i++) {
                if (languages.length() > 0) {
                    languages.append(", ").append(objCountry.getLanguages().get(i).getName())
                            .append(" / ").append(objCountry.getLanguages().get(i).getNativeName());
                } else {
                    languages.append(objCountry.getLanguages().get(i).getName())
                            .append(" / ").append(objCountry.getLanguages().get(i).getNativeName());
                }
            }
        }

        if (objCountry.getCurrencies() != null) {
            for (int i = 0; i < objCountry.getCurrencies().size(); i++) {
                if (currencies.length() > 0) {
                    currencies.append(", ").append(objCountry.getCurrencies().get(i).getName())
                            .append(" - ").append(objCountry.getCurrencies().get(i).getSymbol());
                } else {
                    currencies.append(objCountry.getCurrencies().get(i).getName())
                            .append(" - ").append(objCountry.getCurrencies().get(i).getSymbol());
                }
            }
        }

        if (objCountry.getTopLevelDomain() != null) {
            for (int i = 0; i < objCountry.getTopLevelDomain().size(); i++) {
                if (domain.length() > 0) {
                    domain.append(", ").append(objCountry.getTopLevelDomain().get(i));

                } else {
                    domain.append(objCountry.getTopLevelDomain().get(i));
                }
            }
        }

        if (objCountry.getCallingCodes() != null) {
            for (int i = 0; i < objCountry.getCallingCodes().size(); i++) {
                if (callingCode.length() > 0) {
                    callingCode.append(", ").append(objCountry.getCallingCodes().get(i));

                } else {
                    callingCode.append(objCountry.getCallingCodes().get(i));
                }
            }
        }


        if (languages.length() > 0) {
            objCountry.setLang(languages.toString());
        } else {
            objCountry.setLang(AppConst.NA);
        }

        if (currencies.length() > 0) {
            objCountry.setCurrency(currencies.toString());
        } else {
            objCountry.setCurrency(AppConst.NA);
        }

        if (domain.length() > 0) {
            objCountry.setDomain(domain.toString());
        } else {
            objCountry.setDomain(AppConst.NA);
        }

        if (callingCode.length() > 0) {
            objCountry.setPhonecode(callingCode.toString());
        } else {
            objCountry.setPhonecode(AppConst.NA);
        }

        countriesDataLoader.insert(objCountry, CountriesTableSchema.TABLE);
    }

    @Override
    public void deleteAllCountries() {
        countriesDataLoader.delete(CountriesTableSchema.TABLE);
    }

    @Override
    public void deleteCountry(CountryDTO countryDTO) {
        countriesDataLoader.delete(countryDTO);
    }
}
