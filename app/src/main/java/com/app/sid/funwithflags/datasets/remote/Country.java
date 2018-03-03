
package com.app.sid.funwithflags.datasets.remote;

import com.app.sid.funwithflags.model.funwithflags.CountriesBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Country {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("topLevelDomain")
    @Expose
    private List<String> topLevelDomain = null;
    @SerializedName("alpha2Code")
    @Expose
    private String alpha2Code;
    @SerializedName("alpha3Code")
    @Expose
    private String alpha3Code;
    @SerializedName("callingCodes")
    @Expose
    private List<String> callingCodes = null;
    @SerializedName("capital")
    @Expose
    private String capital;
    @SerializedName("altSpellings")
    @Expose
    private List<String> altSpellings = null;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("subregion")
    @Expose
    private String subregion;
    @SerializedName("population")
    @Expose
    private String population;
    @SerializedName("latlng")
    @Expose
    private List<String> latlng = null;
    @SerializedName("demonym")
    @Expose
    private String demonym;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("gini")
    @Expose
    private Float gini;
    @SerializedName("timezones")
    @Expose
    private List<String> timezones = null;
    @SerializedName("borders")
    @Expose
    private List<String> borders = null;
    @SerializedName("nativeName")
    @Expose
    private String nativeName;
    @SerializedName("numericCode")
    @Expose
    private String numericCode;
    @SerializedName("currencies")
    @Expose
    private List<Currency> currencies = null;
    @SerializedName("languages")
    @Expose
    private List<Language> languages = null;
    @SerializedName("translations")
    @Expose
    private Translations translations;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("regionalBlocs")
    @Expose
    private List<RegionalBloc> regionalBlocs = null;

    private int id;
    private String lang;
    private String currency;
    private String domain;
    private String phonecode;
    private String lat_long;

    public Country() {

    }

    public Country(CountriesBuilder countriesBuilder) {
        name = countriesBuilder.getName();
        topLevelDomain = countriesBuilder.getTopLevelDomain();
        alpha2Code = countriesBuilder.getAlpha2Code();
        alpha3Code = countriesBuilder.getAlpha3Code();
        callingCodes = countriesBuilder.getCallingCodes();
        capital = countriesBuilder.getCapital();
        altSpellings = countriesBuilder.getAltSpellings();
        region = countriesBuilder.getRegion();
        subregion = countriesBuilder.getSubregion();
        population = countriesBuilder.getPopulation();
        latlng = countriesBuilder.getLatlng();
        demonym = countriesBuilder.getDemonym();
        area = countriesBuilder.getArea();
        gini = countriesBuilder.getGini();
        timezones = countriesBuilder.getTimezones();
        borders = countriesBuilder.getBorders();
        nativeName = countriesBuilder.getNativeName();
        numericCode = countriesBuilder.getNumericCode();
        currencies = countriesBuilder.getCurrencies();
        languages = countriesBuilder.getLanguages();
        translations = countriesBuilder.getTranslations();
        regionalBlocs = countriesBuilder.getRegionalBlocs();
    }

    public Country(Country country) {
        name = country.getName();
        topLevelDomain = country.getTopLevelDomain();
        alpha2Code = country.getAlpha2Code();
        alpha3Code = country.getAlpha3Code();
        callingCodes = country.getCallingCodes();
        capital = country.getCapital();
        altSpellings = country.getAltSpellings();
        region = country.getRegion();
        subregion = country.getSubregion();
        population = country.getPopulation();
        latlng = country.getLatlng();
        demonym = country.getDemonym();
        area = country.getArea();
        gini = country.getGini();
        timezones = country.getTimezones();
        borders = country.getBorders();
        nativeName = country.getNativeName();
        numericCode = country.getNumericCode();
        currencies = country.getCurrencies();
        languages = country.getLanguages();
        translations = country.getTranslations();
        regionalBlocs = country.getRegionalBlocs();
    }

    public Country(String name, String alpha2Code) {
        this.name = name;
        this.alpha2Code = alpha2Code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(List<String> topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public List<String> getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(List<String> callingCodes) {
        this.callingCodes = callingCodes;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public List<String> getAltSpellings() {
        return altSpellings;
    }

    public void setAltSpellings(List<String> altSpellings) {
        this.altSpellings = altSpellings;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public List<String> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<String> latlng) {
        this.latlng = latlng;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Float getGini() {
        return gini;
    }

    public void setGini(Float gini) {
        this.gini = gini;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public Translations getTranslations() {
        return translations;
    }

    public void setTranslations(Translations translations) {
        this.translations = translations;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<RegionalBloc> getRegionalBlocs() {
        return regionalBlocs;
    }

    public void setRegionalBlocs(List<RegionalBloc> regionalBlocs) {
        this.regionalBlocs = regionalBlocs;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPhonecode() {
        return phonecode;
    }

    public void setPhonecode(String phonecode) {
        this.phonecode = phonecode;
    }
}
