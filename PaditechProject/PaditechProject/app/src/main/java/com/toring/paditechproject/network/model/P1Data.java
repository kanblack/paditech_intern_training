package com.toring.paditechproject.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class P1Data {

    @SerializedName("section")
    @Expose
    private List<P1Section> p1SectionList = null;

    /**
     * No args constructor for use in serialization
     */
    public P1Data() {
    }

    /**
     * @param p1SectionList
     */
    public P1Data(List<P1Section> p1SectionList) {
        super();
        this.p1SectionList = p1SectionList;
    }

    public List<P1Section> getP1SectionList() {
        return p1SectionList;
    }

    public void setP1SectionList(List<P1Section> p1SectionList) {
        this.p1SectionList = p1SectionList;
    }
}