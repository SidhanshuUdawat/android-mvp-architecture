
package com.app.sid.funwithflags.datasets.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Language {

    @SerializedName("iso639_1")
    @Expose
    public String iso6391;
    @SerializedName("iso639_2")
    @Expose
    public String iso6392;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("nativeName")
    @Expose
    public String nativeName;

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
