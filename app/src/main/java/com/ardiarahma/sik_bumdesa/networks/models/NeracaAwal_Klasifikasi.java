package com.ardiarahma.sik_bumdesa.networks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NeracaAwal_Klasifikasi {
    @SerializedName("id")
    @Expose
    private int klasifikasiId;

    @SerializedName("nama")
    @Expose
    private String klasifikasiName;

    @SerializedName("id_parent_akun")
    @Expose
    private int parentId;

    private boolean childrenVisible;

    public NeracaAwal_Klasifikasi(int klasifikasiId, String klasifikasiName, int parentId) {
        this.klasifikasiId = klasifikasiId;
        this.klasifikasiName = klasifikasiName;
        this.parentId = parentId;
    }

    public int getKlasifikasiId() {
        return klasifikasiId;
    }

    public void setKlasifikasiId(int klasifikasiId) {
        this.klasifikasiId = klasifikasiId;
    }

    public String getKlasifikasiName() {
        return klasifikasiName;
    }

    public void setKlasifikasiName(String klasifikasiName) {
        this.klasifikasiName = klasifikasiName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public boolean isChildrenVisible() {
        return childrenVisible;
    }

    public void setChildrenVisible(boolean childrenVisible) {
        this.childrenVisible = childrenVisible;
    }
}
