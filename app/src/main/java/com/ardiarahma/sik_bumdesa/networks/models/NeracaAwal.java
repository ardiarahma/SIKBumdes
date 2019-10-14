package com.ardiarahma.sik_bumdesa.networks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 on 10/13/2019.
 */

public class NeracaAwal {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("id_data_akun")
    @Expose
    private int id_data_akun;

    @SerializedName("tanggal")
    @Expose
    private String tanggal;

    @SerializedName("jumlah")
    @Expose
    private String jumlah;

    @SerializedName("id_klasifikasi_akun")
    @Expose
    private int id_klasifikasi_akun;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("posisi_normal")
    @Expose
    private String posisi_normal;

    public NeracaAwal(int id, int id_data_akun, String tanggal, String jumlah, int id_klasifikasi_akun, String nama, String posisi_normal) {
        this.id = id;
        this.id_data_akun = id_data_akun;
        this.tanggal = tanggal;
        this.jumlah = jumlah;
        this.id_klasifikasi_akun = id_klasifikasi_akun;
        this.nama = nama;
        this.posisi_normal = posisi_normal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_data_akun() {
        return id_data_akun;
    }

    public void setId_data_akun(int id_data_akun) {
        this.id_data_akun = id_data_akun;
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

    public int getId_klasifikasi_akun() {
        return id_klasifikasi_akun;
    }

    public void setId_klasifikasi_akun(int id_klasifikasi_akun) {
        this.id_klasifikasi_akun = id_klasifikasi_akun;
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
}
