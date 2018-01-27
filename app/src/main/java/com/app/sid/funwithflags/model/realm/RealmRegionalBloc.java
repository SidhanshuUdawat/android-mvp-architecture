package com.app.sid.funwithflags.model.realm;

import com.app.sid.funwithflags.datasets.remote.RegionalBloc;
import com.app.sid.funwithflags.model.funwithflags.RegionalBlocBuilder;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Sidhanshu Udawat on 27-Jan-18.
 */

public class RealmRegionalBloc extends RealmObject {

    public String acronym;
    public String name;
    public RealmList<String> otherAcronyms;
    public RealmList<String> otherNames;

    public RealmRegionalBloc() {

    }

    public RealmRegionalBloc(RegionalBloc regionalBloc) {
        acronym = regionalBloc.getAcronym();
        name = regionalBloc.getName();
        if (regionalBloc.getOtherAcronyms() != null) {
            otherAcronyms = new RealmList<>();
            otherAcronyms.addAll(regionalBloc.getOtherAcronyms());
        }
        if (regionalBloc.getOtherNames() != null) {
            otherNames = new RealmList<>();
            otherNames.addAll(regionalBloc.getOtherNames());
        }
    }

    public RegionalBloc asApiModel() {
        RegionalBlocBuilder regionalBlocBuilder = new RegionalBlocBuilder()
                .setAcronym(acronym)
                .setName(name)
                .setOtherAcronyms(otherAcronyms)
                .setOtherNames(otherNames);
        return regionalBlocBuilder.createRegionalBloc();
    }

    public String getAcronym() {
        return acronym;
    }

    public String getName() {
        return name;
    }

    public RealmList<String> getOtherAcronyms() {
        return otherAcronyms;
    }

    public RealmList<String> getOtherNames() {
        return otherNames;
    }
}
