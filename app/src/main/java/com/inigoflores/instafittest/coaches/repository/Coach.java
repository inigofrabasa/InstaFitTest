package com.inigoflores.instafittest.coaches.repository;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by inigo on 02/12/17.
 */

public class Coach extends RealmObject {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;

    @Required
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("filter_available")
    @Expose
    private Boolean filterAvailable;

    @SerializedName("avatar")
    @Expose
    private String avatarUrl;

    @SerializedName("avatar_pictures")
    @Expose
    private AvatarPictures avatarPictures;

    private String avatarLocal;

    public Coach(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFilterAvailable() {
        return filterAvailable;
    }

    public void setFilterAvailable(Boolean filterAvailable) {
        this.filterAvailable = filterAvailable;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public AvatarPictures getAvatarPictures() {
        return avatarPictures;
    }

    public void setAvatarPictures(AvatarPictures avatarPictures) {
        this.avatarPictures = avatarPictures;
    }

    public String getAvatarLocal() {
        return avatarLocal;
    }

    public void setAvatarLocal(String avatarLocal) {
        this.avatarLocal = avatarLocal;
    }
}
