
package com.app.sid.funwithflags.datasets.remote;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.sid.funwithflags.model.funwithflags.CurrencyBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Currency implements Parcelable {

    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("symbol")
    @Expose
    public String symbol;

    public Currency() {

    }

    public Currency(CurrencyBuilder currencyBuilder) {
        code = currencyBuilder.getCode();
        name = currencyBuilder.getName();
        symbol = currencyBuilder.getSymbol();
    }

    public Currency(Currency currency) {
        code = currency.getCode();
        name = currency.getName();
        symbol = currency.getSymbol();
    }

    protected Currency(Parcel in) {
        code = in.readString();
        name = in.readString();
        symbol = in.readString();
    }

    public static final Creator<Currency> CREATOR = new Creator<Currency>() {
        @Override
        public Currency createFromParcel(Parcel in) {
            return new Currency(in);
        }

        @Override
        public Currency[] newArray(int size) {
            return new Currency[size];
        }
    };

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(code);
        parcel.writeString(name);
        parcel.writeString(symbol);
    }
}
