package com.example.sik_bumdesa.networks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 on 8/29/2019.
 */

public class Jurnal {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("id_kwitansi")
    @Expose
    private int id_kwitansi;

    @SerializedName("tanggal")
    @Expose
    private String tanggal;

    @SerializedName("id_data_akun")
    @Expose
    private int id_data_akun;

    @SerializedName("jumlah")
    @Expose
    private int jumlah;

    @SerializedName("posisi_normal")
    @Expose
    private String posisi_normal;

    @SerializedName("saldo_akhir")
    @Expose
    private int saldo_akhir;

    @SerializedName("id_neraca_awal")
    @Expose
    private int id_neraca_awal;

    @SerializedName("data_akun")
    @Expose
    private Akun_DataAkun data_akun;

    public Jurnal(int id, int id_kwitansi, String tanggal, int id_data_akun, int jumlah, String posisi_normal, int saldo_akhir, int id_neraca_awal, Akun_DataAkun data_akun) {
        this.id = id;
        this.id_kwitansi = id_kwitansi;
        this.tanggal = tanggal;
        this.id_data_akun = id_data_akun;
        this.jumlah = jumlah;
        this.posisi_normal = posisi_normal;
        this.saldo_akhir = saldo_akhir;
        this.id_neraca_awal = id_neraca_awal;
        this.data_akun = data_akun;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_kwitansi() {
        return id_kwitansi;
    }

    public void setId_kwitansi(int id_kwitansi) {
        this.id_kwitansi = id_kwitansi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getId_data_akun() {
        return id_data_akun;
    }

    public void setId_data_akun(int id_data_akun) {
        this.id_data_akun = id_data_akun;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getPosisi_normal() {
        return posisi_normal;
    }

    public void setPosisi_normal(String posisi_normal) {
        this.posisi_normal = posisi_normal;
    }

    public int getSaldo_akhir() {
        return saldo_akhir;
    }

    public void setSaldo_akhir(int saldo_akhir) {
        this.saldo_akhir = saldo_akhir;
    }

    public int getId_neraca_awal() {
        return id_neraca_awal;
    }

    public void setId_neraca_awal(int id_neraca_awal) {
        this.id_neraca_awal = id_neraca_awal;
    }

    public Akun_DataAkun getData_akun() {
        return data_akun;
    }

    public void setData_akun(Akun_DataAkun data_akun) {
        this.data_akun = data_akun;
    }
}
