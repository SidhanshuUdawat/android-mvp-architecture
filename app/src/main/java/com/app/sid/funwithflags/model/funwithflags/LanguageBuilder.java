package com.app.sid.funwithflags.model.funwithflags;

import com.app.sid.funwithflags.datasets.remote.Language;

/**
 * Created by Sidhanshu Udawat on 27-Jan-18.
 */

public class LanguageBuilder {

    public String iso6391;
    public String iso6392;
    public String name;
    public String nativeName;

    public LanguageBuilder setIso6391(String iso6391) {
        this.iso6391 = iso6391;
        return this;
    }

    public LanguageBuilder setIso6392(String iso6392) {
        this.iso6392 = iso6392;
        return this;
    }

    public LanguageBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public LanguageBuilder setNativeName(String nativeName) {
        this.nativeName = nativeName;
        return this;
    }

    public Language createLanguage() {
        return new Language(this);
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
