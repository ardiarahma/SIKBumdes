package com.example.sik_bumdesa.networks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NeracaAwal_Add {
    @SerializedName("kode_klasifikasi")
    @Expose
    private int klasifikasiId;

    @SerializedName("kode_akun")
    @Expose
    private int akunId;

    @SerializedName("tanggal")
    @Expose
    private String akunDate;

    @SerializedName("jumlah")
    @Expose
    private String akunTotal;

    @SerializedName("id_neraca_awal")
    @Expose
    private int parentId;

    public NeracaAwal_Add(int klasifikasiId, int akunId, String akunDate, String akunTotal, int parentId) {
        this.klasifikasiId = klasifikasiId;
        this.akunId = akunId;
        this.akunDate = akunDate;
        this.akunTotal = akunTotal;
        this.parentId = parentId;
    }

    public int getKlasifikasiId() {
        return klasifikasiId;
    }

    public void setKlasifikasiId(int klasifikasiId) {
        this.klasifikasiId = klasifikasiId;
    }

    public int getAkunId() {
        return akunId;
    }

    public void setAkunId(int akunId) {
        this.akunId = akunId;
    }

    public String getAkunDate() {
        return akunDate;
    }

    public void setAkunDate(String akunDate) {
        this.akunDate = akunDate;
    }

    public String getAkunTotal() {
        return akunTotal;
    }

    public void setAkunTotal(String akunTotal) {
        this.akunTotal = akunTotal;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
