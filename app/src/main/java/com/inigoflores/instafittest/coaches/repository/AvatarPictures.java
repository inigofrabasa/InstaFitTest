package com.inigoflores.instafittest.coaches.repository;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by inigo on 02/12/17.
 */

public class AvatarPictures extends RealmObject {

    @SerializedName("full_size")
    @Expose
    private String fullSizeUrl;

    @SerializedName("big")
    @Expose
    private String bigUrl;

    @SerializedName("medium")
    @Expose
    private String mediumUrl;

    @SerializedName("small")
    @Expose
    private String smallUrl;

    public String getFullSizeUrl() {
        return fullSizeUrl;
    }

    public void setFullSizeUrl(String fullSizeUrl) {
        this.fullSizeUrl = fullSizeUrl;
    }

    public String getBigUrl() {
        return bigUrl;
    }

    public void setBigUrl(String bigUrl) {
        this.bigUrl = bigUrl;
    }

    public String getMediumUrl() {
        return mediumUrl;
    }

    public void setMediumUrl(String mediumUrl) {
        this.mediumUrl = mediumUrl;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }
}
