package com.toring.paditechproject.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class P1Data {

    @SerializedName("p1Section")
    @Expose
    private List<P1Section> p1Section = null;

    /**
     * No args constructor for use in serialization
     */
    public P1Data() {
    }

    /**
     * @param p1Section
     */
    public P1Data(List<P1Section> p1Section) {
        super();
        this.p1Section = p1Section;
    }

    public List<P1Section> getP1Section() {
        return p1Section;
    }

    public void setP1Section(List<P1Section> p1Section) {
        this.p1Section = p1Section;
    }
}