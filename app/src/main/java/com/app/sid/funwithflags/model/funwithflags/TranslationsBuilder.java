package com.app.sid.funwithflags.model.funwithflags;

import com.app.sid.funwithflags.datasets.remote.Translations;

/**
 * Created by Sidhanshu Udawat on 27-Jan-18.
 */

public class TranslationsBuilder {

    public String de;
    public String es;
    public String fr;
    public String ja;
    public String it;
    public String br;
    public String pt;

    public TranslationsBuilder setDe(String de) {
        this.de = de;
        return this;
    }

    public TranslationsBuilder setEs(String es) {
        this.es = es;
        return this;
    }

    public TranslationsBuilder setFr(String fr) {
        this.fr = fr;
        return this;
    }

    public TranslationsBuilder setJa(String ja) {
        this.ja = ja;
        return this;
    }

    public TranslationsBuilder setIt(String it) {
        this.it = it;
        return this;
    }

    public TranslationsBuilder setBr(String br) {
        this.br = br;
        return this;
    }

    public TranslationsBuilder setPt(String pt) {
        this.pt = pt;
        return this;
    }

    public Translations createTranslation() {
        return new Translations(this);
    }

    public String getDe() {
        return de;
    }

    public String getEs() {
        return es;
    }

    public String getFr() {
        return fr;
    }

    public String getJa() {
        return ja;
    }

    public String getIt() {
        return it;
    }

    public String getBr() {
        return br;
    }

    public String getPt() {
        return pt;
    }
}
