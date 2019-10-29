package com.ardiarahma.sik_bumdesa.networks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NeracaAwal_AllAkun {
    @SerializedName("id")
    @Expose
    private int akunId;

    @SerializedName("nama")
    @Expose
    private String akunName;

    public NeracaAwal_AllAkun(int akunId, String akunName) {
        this.akunId = akunId;
        this.akunName = akunName;
    }

    public int getAkunId() {
        return akunId;
    }

    public void setAkunId(int akunId) {
        this.akunId = akunId;
    }

    public String getAkunName() {
        return akunName;
    }

    public void setAkunName(String akunName) {
        this.akunName = akunName;
    }

    @Override
    public String toString() {
        return akunName;
    }
}
