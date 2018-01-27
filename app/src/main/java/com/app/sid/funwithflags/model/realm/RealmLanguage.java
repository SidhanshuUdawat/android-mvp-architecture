package com.app.sid.funwithflags.model.realm;

import com.app.sid.funwithflags.datasets.remote.Language;
import com.app.sid.funwithflags.model.funwithflags.LanguageBuilder;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sidhanshu Udawat on 27-Jan-18.
 */

public class RealmLanguage extends RealmObject {

    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    public String iso6391;
    public String iso6392;
    public String name;
    public String nativeName;

    public RealmLanguage() {

    }

    public RealmLanguage(Language language) {
        iso6391 = language.getIso6391();
        iso6392 = language.getIso6392();
        name = language.getName();
        nativeName = language.getNativeName();
    }

    public Language asApiModel() {
        LanguageBuilder languageBuilder = new LanguageBuilder()
                .setIso6391(iso6391)
                .setIso6392(iso6391)
                .setName(name)
                .setNativeName(nativeName);
        return languageBuilder.createLanguage();
    }

    public String getIso6391() {
        return iso6391;
    }

    public String getIso6392() {
        return iso6392;
    }

    public String getName() {
        return name;
    }

    public String getNativeName() {
        return nativeName;
    }
}
