package com.app.sid.funwithflags.model.funwithflags;

import com.app.sid.funwithflags.datasets.remote.Currency;

/**
 * Created by Sidhanshu Udawat on 27-Jan-18.
 */

public class CurrencyBuilder {
    public String code;
    public String name;
    public String symbol;

    public CurrencyBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    public CurrencyBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CurrencyBuilder setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public Currency createCurrency() {
        return new Currency(this);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }
}
