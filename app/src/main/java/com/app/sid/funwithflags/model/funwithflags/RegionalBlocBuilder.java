package com.app.sid.funwithflags.model.funwithflags;

import com.app.sid.funwithflags.datasets.remote.RegionalBloc;

import java.util.List;

/**
 * Created by Sidhanshu Udawat on 27-Jan-18.
 */

public class RegionalBlocBuilder {
    public String acronym;
    public String name;
    public List<String> otherAcronyms;
    public List<String> otherNames;


    public RegionalBlocBuilder setAcronym(String acronym) {
        this.acronym = acronym;
        return this;
    }

    public RegionalBlocBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public RegionalBlocBuilder setOtherAcronyms(List<String> otherAcronyms) {
        this.otherAcronyms = otherAcronyms;
        return this;
    }

    public RegionalBlocBuilder setOtherNames(List<String> otherNames) {
        this.otherNames = otherNames;
        return this;
    }

    public RegionalBloc createRegionalBloc() {
        return new RegionalBloc(this);
    }

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
}
