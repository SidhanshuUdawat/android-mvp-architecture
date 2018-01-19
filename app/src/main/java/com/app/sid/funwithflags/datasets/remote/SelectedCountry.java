package com.app.sid.funwithflags.datasets.remote;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sidhanshu Udawat on 20-Jan-18.
 */

public class SelectedCountry implements Parcelable {

    private String name;
    private String alpha2Code;

    public SelectedCountry(String name, String alpha2Code) {
        this.name = name;
        this.alpha2Code = alpha2Code;
    }

    protected SelectedCountry(Parcel in) {
        name = in.readString();
        alpha2Code = in.readString();
    }

    public static final Creator<SelectedCountry> CREATOR = new Creator<SelectedCountry>() {
        @Override
        public SelectedCountry createFromParcel(Parcel in) {
            return new SelectedCountry(in);
        }

        @Override
        public SelectedCountry[] newArray(int size) {
            return new SelectedCountry[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(alpha2Code);
    }
}
