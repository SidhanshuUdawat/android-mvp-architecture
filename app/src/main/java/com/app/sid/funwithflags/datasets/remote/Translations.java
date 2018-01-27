
package com.app.sid.funwithflags.datasets.remote;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.sid.funwithflags.model.funwithflags.TranslationsBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Translations implements Parcelable {

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

    public Translations() {

    }

    public Translations(TranslationsBuilder translationsBuilder) {
        de = translationsBuilder.getDe();
        es = translationsBuilder.getEs();
        fr = translationsBuilder.getFr();
        ja = translationsBuilder.getJa();
        it = translationsBuilder.getIt();
        br = translationsBuilder.getBr();
        pt = translationsBuilder.getPt();
    }

    public Translations(Translations translations) {
        de = translations.getDe();
        es = translations.getEs();
        fr = translations.getFr();
        ja = translations.getJa();
        it = translations.getIt();
        br = translations.getBr();
        pt = translations.getPt();
    }

    protected Translations(Parcel in) {
        de = in.readString();
        es = in.readString();
        fr = in.readString();
        ja = in.readString();
        it = in.readString();
        br = in.readString();
        pt = in.readString();
    }

    public static final Creator<Translations> CREATOR = new Creator<Translations>() {
        @Override
        public Translations createFromParcel(Parcel in) {
            return new Translations(in);
        }

        @Override
        public Translations[] newArray(int size) {
            return new Translations[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(de);
        parcel.writeString(es);
        parcel.writeString(fr);
        parcel.writeString(ja);
        parcel.writeString(it);
        parcel.writeString(br);
        parcel.writeString(pt);
    }
}
