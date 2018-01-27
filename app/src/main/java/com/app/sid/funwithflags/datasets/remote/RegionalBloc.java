
package com.app.sid.funwithflags.datasets.remote;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import com.app.sid.funwithflags.model.funwithflags.RegionalBlocBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;

public class RegionalBloc implements Parcelable {

    @SerializedName("acronym")
    @Expose
    public String acronym;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("otherAcronyms")
    @Expose
    public List<String> otherAcronyms = null;
    @SerializedName("otherNames")
    @Expose
    public List<String> otherNames = null;

    public RegionalBloc() {

    }

    public RegionalBloc(RegionalBlocBuilder regionalBlocBuilder) {
        acronym = regionalBlocBuilder.getAcronym();
        name = regionalBlocBuilder.getName();
        otherAcronyms = regionalBlocBuilder.getOtherAcronyms();
        otherNames = regionalBlocBuilder.getOtherNames();
    }

    public RegionalBloc(RegionalBloc regionalBloc) {
        acronym = regionalBloc.getAcronym();
        name = regionalBloc.getName();
        otherAcronyms = regionalBloc.getOtherAcronyms();
        otherNames = regionalBloc.getOtherNames();
    }

    protected RegionalBloc(Parcel in) {
        acronym = in.readString();
        name = in.readString();
        otherAcronyms = in.createStringArrayList();
        otherNames = in.createStringArrayList();
    }

    public static final Creator<RegionalBloc> CREATOR = new Creator<RegionalBloc>() {
        @Override
        public RegionalBloc createFromParcel(Parcel in) {
            return new RegionalBloc(in);
        }

        @Override
        public RegionalBloc[] newArray(int size) {
            return new RegionalBloc[size];
        }
    };

    public String getAcronym() {
        return acronym;
    }

    public String getName() {
        return name;
    }

    public List<String> getOtherAcronyms() {
        return otherAcronyms;
    }

    public List<String> getOtherNames() {
        return otherNames;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(acronym);
        parcel.writeString(name);
        parcel.writeStringList(otherAcronyms);
        parcel.writeStringList(otherNames);
    }
}
