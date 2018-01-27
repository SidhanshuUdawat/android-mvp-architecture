package com.app.sid.funwithflags.model.realm;

import com.app.sid.funwithflags.datasets.remote.Translations;
import com.app.sid.funwithflags.model.funwithflags.TranslationsBuilder;

import io.realm.RealmObject;

/**
 * Created by Sidhanshu Udawat on 27-Jan-18.
 */

public class RealmTranslations extends RealmObject {

    public String de;
    public String es;
    public String fr;
    public String ja;
    public String it;
    public String br;
    public String pt;

    public RealmTranslations() {

    }

    public RealmTranslations(Translations translations) {
        de = translations.getDe();
        es = translations.getEs();
        fr = translations.getFr();
        ja = translations.getJa();
        it = translations.getIt();
        br = translations.getBr();
        pt = translations.getPt();
    }

    public Translations asApiModel() {
        TranslationsBuilder translationsBuilder = new TranslationsBuilder()
                .setBr(br)
                .setDe(de)
                .setEs(es)
                .setFr(fr)
                .setIt(it)
                .setJa(ja)
                .setPt(pt);
        return translationsBuilder.createTranslation();
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
