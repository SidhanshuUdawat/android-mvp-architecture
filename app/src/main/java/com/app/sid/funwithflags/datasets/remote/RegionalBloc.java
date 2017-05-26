
package com.app.sid.funwithflags.datasets.remote;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegionalBloc {

    @SerializedName("acronym")
    @Expose
    public String acronym;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("otherAcronyms")
    @Expose
    public List<Object> otherAcronyms = null;
    @SerializedName("otherNames")
    @Expose
    public List<String> otherNames = null;

    public String getAcronym() {
        return acronym;
    }

    public String getName() {
        return name;
    }

    public List<Object> getOtherAcronyms() {
        return otherAcronyms;
    }

    public List<String> getOtherNames() {
        return otherNames;
    }
}
