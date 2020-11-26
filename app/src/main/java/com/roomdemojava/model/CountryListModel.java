package com.roomdemojava.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryListModel {
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
    public Integer population;

    @SerializedName("area")
    @Expose
    public Double area;

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

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
