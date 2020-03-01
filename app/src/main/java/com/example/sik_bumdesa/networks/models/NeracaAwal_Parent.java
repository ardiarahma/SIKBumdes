package com.example.sik_bumdesa.networks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NeracaAwal_Parent {
    @SerializedName("id")
    @Expose
    private int parentId;

    @SerializedName("nama")
    @Expose
    private String parentName;

    private boolean childrenVisible;

    public NeracaAwal_Parent(int parentId, String parentName) {
        this.parentId = parentId;
        this.parentName = parentName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public boolean isChildrenVisible() {
        return childrenVisible;
    }

    public void setChildrenVisible(boolean childrenVisible) {
        this.childrenVisible = childrenVisible;
    }
}
