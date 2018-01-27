
package com.app.sid.funwithflags.datasets.remote;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.sid.funwithflags.model.funwithflags.LanguageBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Language implements Parcelable {

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

    public Language(LanguageBuilder languageBuilder) {
        iso6391 = languageBuilder.getIso6391();
        iso6392 = languageBuilder.getIso6391();
        name = languageBuilder.getName();
        nativeName = languageBuilder.getNativeName();
    }

    public Language(Language language) {
        iso6391 = language.getIso6391();
        iso6392 = language.getIso6391();
        name = language.getName();
        nativeName = language.getNativeName();
    }

    protected Language(Parcel in) {
        iso6391 = in.readString();
        iso6392 = in.readString();
        name = in.readString();
        nativeName = in.readString();
    }

    public static final Creator<Language> CREATOR = new Creator<Language>() {
        @Override
        public Language createFromParcel(Parcel in) {
            return new Language(in);
        }

        @Override
        public Language[] newArray(int size) {
            return new Language[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(iso6391);
        parcel.writeString(iso6392);
        parcel.writeString(name);
        parcel.writeString(nativeName);
    }
}
