package com.app.sid.funwithflags.model.realm;

import com.app.sid.funwithflags.datasets.remote.Countries;
import com.app.sid.funwithflags.datasets.remote.Currency;
import com.app.sid.funwithflags.datasets.remote.Language;
import com.app.sid.funwithflags.datasets.remote.RegionalBloc;
import com.app.sid.funwithflags.model.funwithflags.CountriesBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sidhanshu Udawat on 27-Jan-18.
 */

public class RealmCountries extends RealmObject {

    @PrimaryKey
    private String mUuid;
    private String name;
    private RealmList<String> topLevelDomain;
    private String alpha2Code;
    private String alpha3Code;
    private RealmList<String> callingCodes;
    private String capital;
    private RealmList<String> altSpellings;
    private String region;
    private String subregion;
    private String population;
    private RealmList<String> latlng;
    private String demonym;
    private String area;
    private Float gini;
    private RealmList<String> timezones;
    private RealmList<String> borders;
    private String nativeName;
    private String numericCode;
    private RealmList<RealmCurrency> realmCurrencies;
    private RealmList<RealmLanguage> releamLanguages;
    private RealmTranslations realmTranslations;
    private RealmList<RealmRegionalBloc> realmRegionalBlocs;

    RealmCountries(Countries countries) {
        mUuid = UUID.randomUUID().toString();
        name = countries.getName();
        if (countries.getTopLevelDomain() != null) {
            topLevelDomain = new RealmList<>();
            topLevelDomain.addAll(countries.getTopLevelDomain());
        }
        alpha2Code = countries.getAlpha2Code();
        alpha3Code = countries.getAlpha3Code();
        if (countries.getCallingCodes() != null) {
            callingCodes = new RealmList<>();
            callingCodes.addAll(countries.getCallingCodes());
        }
        capital = countries.getCapital();
        if (countries.getAltSpellings() != null) {
            altSpellings = new RealmList<>();
            altSpellings.addAll(countries.getAltSpellings());
        }
        region = countries.getRegion();
        subregion = countries.getSubregion();
        population = countries.getPopulation();

        if (countries.getLatlng() != null) {
            latlng = new RealmList<>();
            latlng.addAll(countries.getLatlng());
        }
        demonym = countries.getDemonym();
        area = countries.getArea();
        gini = countries.getGini();
        if (countries.getTimezones() != null) {
            timezones = new RealmList<>();
            timezones.addAll(countries.getTimezones());
        }
        if (countries.getBorders() != null) {
            borders = new RealmList<>();
            borders.addAll(countries.getBorders());
        }
        nativeName = countries.getNativeName();
        numericCode = countries.getNumericCode();
        if (countries.getCurrencies() != null) {
            realmCurrencies = new RealmList<>();
            for (Currency currency : countries.getCurrencies()) {
                if (currency != null) {
                    RealmCurrency realmCurrency = new RealmCurrency(currency);
                    realmCurrencies.add(realmCurrency);
                }
            }
        }

        if (countries.getLanguages() != null) {
            releamLanguages = new RealmList<>();
            for (Language language : countries.getLanguages()) {
                if (language != null) {
                    RealmLanguage realmLanguage = new RealmLanguage(language);
                    releamLanguages.add(realmLanguage);
                }
            }
        }
        if (countries.getTranslations() != null) {
            realmTranslations = new RealmTranslations(countries.getTranslations());
        }
        if (countries.getRegionalBlocs() != null) {
            for (RegionalBloc regionalBloc : countries.getRegionalBlocs()) {
                if (regionalBloc != null) {
                    RealmRegionalBloc realmRegionalBloc = new RealmRegionalBloc(regionalBloc);
                    realmRegionalBlocs.add(realmRegionalBloc);
                }
            }
        }
    }

    public Countries asApiModel() {
        CountriesBuilder countriesBuilder = new CountriesBuilder()
                .setName(name)
                .setTopLevelDomain(topLevelDomain)
                .setAlpha2Code(alpha2Code)
                .setAlpha3Code(alpha3Code)
                .setCallingCodes(callingCodes)
                .setCapital(capital)
                .setAltSpellings(altSpellings)
                .setRegion(region)
                .setSubregion(subregion)
                .setPopulation(population)
                .setLatlng(latlng)
                .setDemonym(demonym)
                .setArea(area)
                .setGini(gini)
                .setTimezones(timezones)
                .setBorders(borders)
                .setNativeName(nativeName)
                .setNumericCode(numericCode);

        if (realmCurrencies != null && realmCurrencies.size() > 0) {
            List<Currency> currencyList = new ArrayList<>();
            for (RealmCurrency realmCurrency : realmCurrencies) {
                currencyList.add(realmCurrency.asApiModel());
            }
            countriesBuilder.setCurrencies(currencyList);
        }

        if (releamLanguages != null && releamLanguages.size() > 0) {
            List<Language> languageList = new ArrayList<>();
            for (RealmLanguage realmLanguage : releamLanguages) {
                languageList.add(realmLanguage.asApiModel());
            }
            countriesBuilder.setLanguages(languageList);
        }

        if (realmTranslations != null) {
            countriesBuilder.setTranslations(realmTranslations.asApiModel());
        }

        if (realmRegionalBlocs != null && realmRegionalBlocs.size() > 0) {
            List<RegionalBloc> regionalBlocList = new ArrayList<>();
            for (RealmRegionalBloc realmRegionalBloc : realmRegionalBlocs) {
                regionalBlocList.add(realmRegionalBloc.asApiModel());
            }
            countriesBuilder.setRegionalBlocs(regionalBlocList);
        }
        return countriesBuilder.createCountries();
    }

    public String getName() {
        return name;
    }

    public RealmList<String> getTopLevelDomain() {
        return topLevelDomain;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public RealmList<String> getCallingCodes() {
        return callingCodes;
    }

    public String getCapital() {
        return capital;
    }

    public RealmList<String> getAltSpellings() {
        return altSpellings;
    }

    public String getRegion() {
        return region;
    }

    public String getSubregion() {
        return subregion;
    }

    public String getPopulation() {
        return population;
    }

    public RealmList<String> getLatlng() {
        return latlng;
    }

    public String getDemonym() {
        return demonym;
    }

    public String getArea() {
        return area;
    }

    public Float getGini() {
        return gini;
    }

    public RealmList<String> getTimezones() {
        return timezones;
    }

    public RealmList<String> getBorders() {
        return borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public RealmList<RealmCurrency> getRealmCurrencies() {
        return realmCurrencies;
    }

    public RealmList<RealmLanguage> getReleamLanguages() {
        return releamLanguages;
    }

    public RealmTranslations getRealmTranslations() {
        return realmTranslations;
    }

    public RealmList<RealmRegionalBloc> getRealmRegionalBlocs() {
        return realmRegionalBlocs;
    }
}
