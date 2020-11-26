package com.roomdemojava.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CountryListModel implements Serializable {
    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("capital")
    @Expose
    public String capital;

    @SerializedName("region")
    @Expose
    public String region;

    @SerializedName("subregion")
    @Expose
    public String subregion;

    @SerializedName("population")
    @Expose
    public int population;

    @SerializedName("borders")
    @Expose
    public List<String> borders = null;

    @SerializedName("languages")
    @Expose
    public List<Language> languages = null;

    @SerializedName("flag")
    @Expose
    public String flag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}