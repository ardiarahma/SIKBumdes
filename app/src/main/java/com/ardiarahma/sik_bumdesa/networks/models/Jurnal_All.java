package com.ardiarahma.sik_bumdesa.networks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Jurnal_All {

    @SerializedName("tanggal")
    @Expose
    private String tanggal;

    @SerializedName("no_kwitansi")
    @Expose
    private String no_kwitansi;

    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("posisi_normal")
    @Expose
    private String posisi_normal;

    @SerializedName("jumlah")
    @Expose
    private int jumlah;

    public Jurnal_All(String tanggal, String no_kwitansi, String keterangan, int id, String nama, String posisi_normal, int jumlah) {
        this.tanggal = tanggal;
        this.no_kwitansi = no_kwitansi;
        this.keterangan = keterangan;
        this.id = id;
        this.nama = nama;
        this.posisi_normal = posisi_normal;
        this.jumlah = jumlah;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNo_kwitansi() {
        return no_kwitansi;
    }

    public void setNo_kwitansi(String no_kwitansi) {
        this.no_kwitansi = no_kwitansi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPosisi_normal() {
        return posisi_normal;
    }

    public void setPosisi_normal(String posisi_normal) {
        this.posisi_normal = posisi_normal;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
