package com.ardiarahma.sik_bumdesa.networks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LabaRugi_PendapatanLainLain {
    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("no_akun")
    @Expose
    private int no_akun;

    @SerializedName("nilai_akun")
    @Expose
    private int nilai_akun;

    public LabaRugi_PendapatanLainLain(String nama, int no_akun, int nilai_akun) {
        this.nama = nama;
        this.no_akun = no_akun;
        this.nilai_akun = nilai_akun;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getNo_akun() {
        return no_akun;
    }

    public void setNo_akun(int no_akun) {
        this.no_akun = no_akun;
    }

    public int getNilai_akun() {
        return nilai_akun;
    }

    public void setNilai_akun(int nilai_akun) {
        this.nilai_akun = nilai_akun;
    }
}
