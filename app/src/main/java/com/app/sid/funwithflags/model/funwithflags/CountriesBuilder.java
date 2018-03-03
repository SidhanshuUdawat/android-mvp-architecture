package com.app.sid.funwithflags.model.funwithflags;

import com.app.sid.funwithflags.datasets.remote.Country;
import com.app.sid.funwithflags.datasets.remote.Currency;
import com.app.sid.funwithflags.datasets.remote.Language;
import com.app.sid.funwithflags.datasets.remote.RegionalBloc;
import com.app.sid.funwithflags.datasets.remote.Translations;

import java.util.List;

/**
 * Created by Sidhanshu Udawat on 27-Jan-18.
 */

public class CountriesBuilder {

    private String name;
    private List<String> topLevelDomain;
    private String alpha2Code;
    private String alpha3Code;
    private List<String> callingCodes;
    private String capital;
    private List<String> altSpellings;
    private String region;
    private String subregion;
    private String population;
    private List<String> latlng;
    private String demonym;
    private String area;
    private Float gini;
    private List<String> timezones;
    private List<String> borders;
    private String nativeName;
    private String numericCode;
    private List<Currency> currencies;
    private List<Language> languages;
    private Translations translations;
    private List<RegionalBloc> regionalBlocs;

    public CountriesBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CountriesBuilder setTopLevelDomain(List<String> topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
        return this;
    }

    public CountriesBuilder setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
        return this;
    }

    public CountriesBuilder setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
        return this;
    }

    public CountriesBuilder setCallingCodes(List<String> callingCodes) {
        this.callingCodes = callingCodes;
        return this;
    }

    public CountriesBuilder setCapital(String capital) {
        this.capital = capital;
        return this;
    }

    public CountriesBuilder setAltSpellings(List<String> altSpellings) {
        this.altSpellings = altSpellings;
        return this;
    }

    public CountriesBuilder setRegion(String region) {
        this.region = region;
        return this;
    }

    public CountriesBuilder setSubregion(String subregion) {
        this.subregion = subregion;
        return this;
    }

    public CountriesBuilder setPopulation(String population) {
        this.population = population;
        return this;
    }

    public CountriesBuilder setLatlng(List<String> latlng) {
        this.latlng = latlng;
        return this;
    }

    public CountriesBuilder setDemonym(String demonym) {
        this.demonym = demonym;
        return this;
    }

    public CountriesBuilder setArea(String area) {
        this.area = area;
        return this;
    }

    public CountriesBuilder setGini(Float gini) {
        this.gini = gini;
        return this;
    }

    public CountriesBuilder setTimezones(List<String> timezones) {
        this.timezones = timezones;
        return this;
    }

    public CountriesBuilder setBorders(List<String> borders) {
        this.borders = borders;
        return this;
    }

    public CountriesBuilder setNativeName(String nativeName) {
        this.nativeName = nativeName;
        return this;
    }

    public CountriesBuilder setNumericCode(String numericCode) {
        this.numericCode = numericCode;
        return this;
    }

    public CountriesBuilder setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
        return this;
    }

    public CountriesBuilder setLanguages(List<Language> languages) {
        this.languages = languages;
        return this;
    }

    public CountriesBuilder setTranslations(Translations translations) {
        this.translations = translations;
        return this;
    }

    public CountriesBuilder setRegionalBlocs(List<RegionalBloc> regionalBlocs) {
        this.regionalBlocs = regionalBlocs;
        return this;
    }


    public Country createCountries() {
        return new Country(this);
    }

    public String getName() {
        return name;
    }

    public List<String> getTopLevelDomain() {
        return topLevelDomain;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public List<String> getCallingCodes() {
        return callingCodes;
    }

    public String getCapital() {
        return capital;
    }

    public List<String> getAltSpellings() {
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

    public List<String> getLatlng() {
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

    public List<String> getTimezones() {
        return timezones;
    }

    public List<String> getBorders() {
        return borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public Translations getTranslations() {
        return translations;
    }

    public List<RegionalBloc> getRegionalBlocs() {
        return regionalBlocs;
    }
}
