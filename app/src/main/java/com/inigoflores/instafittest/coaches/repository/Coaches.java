package com.inigoflores.instafittest.coaches.repository;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by inigo on 02/12/17.
 */

public class Coaches {
    @SerializedName("data")
    @Expose
    private List<Coach> coaches = null;

    public List<Coach> getCoaches() {
        return coaches;
    }

    public void setCoaches(List<Coach> coaches) {
        this.coaches = coaches;
    }
}
