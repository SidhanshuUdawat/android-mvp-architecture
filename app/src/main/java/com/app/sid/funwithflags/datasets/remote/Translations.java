
package com.app.sid.funwithflags.datasets.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Translations {

    @SerializedName("de")
    @Expose
    public String de;
    @SerializedName("es")
    @Expose
    public String es;
    @SerializedName("fr")
    @Expose
    public String fr;
    @SerializedName("ja")
    @Expose
    public String ja;
    @SerializedName("it")
    @Expose
    public String it;
    @SerializedName("br")
    @Expose
    public String br;
    @SerializedName("pt")
    @Expose
    public String pt;

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
