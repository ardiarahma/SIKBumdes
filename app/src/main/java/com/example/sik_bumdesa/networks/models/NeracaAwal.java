package com.example.sik_bumdesa.networks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 on 10/13/2019.
 */

public class NeracaAwal {

    @SerializedName("kode_klasifikasi")
    @Expose
    private int kode_klasifikasi;

    @SerializedName("kode_akun")
    @Expose
    private int kode_akun;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("tanggal")
    @Expose
    private String tanggal;

    @SerializedName("jumlah")
    @Expose
    private String jumlah;

    @SerializedName("id_neraca_awal")
    @Expose
    private int id_neraca_awal;

    public NeracaAwal(int kode_klasifikasi, int kode_akun, String nama, String tanggal, String jumlah, int id_neraca_awal) {
        this.kode_klasifikasi = kode_klasifikasi;
        this.kode_akun = kode_akun;
        this.nama = nama;
        this.tanggal = tanggal;
        this.jumlah = jumlah;
        this.id_neraca_awal = id_neraca_awal;
    }

    public int getKode_klasifikasi() {
        return kode_klasifikasi;
    }

    public void setKode_klasifikasi(int kode_klasifikasi) {
        this.kode_klasifikasi = kode_klasifikasi;
    }

    public int getKode_akun() {
        return kode_akun;
    }

    public void setKode_akun(int kode_akun) {
        this.kode_akun = kode_akun;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public int getId_neraca_awal() {
        return id_neraca_awal;
    }

    public void setId_neraca_awal(int id_neraca_awal) {
        this.id_neraca_awal = id_neraca_awal;
    }
}
