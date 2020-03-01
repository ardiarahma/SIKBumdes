package com.example.sik_bumdesa.networks.models;

/**
 * Created by Windows 10 on 1/23/2020.
 */

public class Anggaran_Modal {

    String nama;
    int jumlah;

    public Anggaran_Modal(String nama, int jumlah) {
        this.nama = nama;
        this.jumlah = jumlah;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
