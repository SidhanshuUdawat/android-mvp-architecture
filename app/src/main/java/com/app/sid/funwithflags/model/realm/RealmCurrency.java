package com.app.sid.funwithflags.model.realm;

import com.app.sid.funwithflags.datasets.remote.Currency;
import com.app.sid.funwithflags.model.funwithflags.CurrencyBuilder;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sidhanshu Udawat on 27-Jan-18.
 */

public class RealmCurrency extends RealmObject {

    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    public String code;
    public String name;
    public String symbol;

    public RealmCurrency() {

    }

    public RealmCurrency(Currency currency) {
        code = currency.getCode();
        name = currency.getName();
        symbol = currency.getSymbol();
    }

    public Currency asApiModel() {
        CurrencyBuilder currencyBuilder = new CurrencyBuilder()
                .setCode(code)
                .setName(name)
                .setSymbol(symbol);
        return currencyBuilder.createCurrency();
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
