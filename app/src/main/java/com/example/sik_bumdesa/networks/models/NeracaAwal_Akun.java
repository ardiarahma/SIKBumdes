package com.example.sik_bumdesa.networks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NeracaAwal_Akun {
    @SerializedName("kode_klasifikasi")
    @Expose
    private int klasifikasiId;

    @SerializedName("kode_akun")
    @Expose
    private int akunId;

    @SerializedName("nama_akun")
    @Expose
    private String akunName;

    @SerializedName("tanggal")
    @Expose
    private String akunDate;

    @SerializedName("jumlah")
    @Expose
    private String akunTotal;

    @SerializedName("id_neraca_awal")
    @Expose
    private int parentId;

    public NeracaAwal_Akun(int klasifikasiId, int akunId, String akunName, String akunDate, String akunTotal, int parentId) {
        this.klasifikasiId = klasifikasiId;
        this.akunId = akunId;
        this.akunName = akunName;
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

    public String getAkunName() {
        return akunName;
    }

    public void setAkunName(String akunName) {
        this.akunName = akunName;
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
