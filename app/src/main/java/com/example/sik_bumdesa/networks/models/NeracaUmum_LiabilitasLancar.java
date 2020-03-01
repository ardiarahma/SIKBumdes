package com.example.sik_bumdesa.networks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 on 11/22/2019.
 */

public class NeracaUmum_LiabilitasLancar {

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("no_akun")
    @Expose
    private int no_akun;

    @SerializedName("nilai_akun")
    @Expose
    private int nilai_akun;

    public NeracaUmum_LiabilitasLancar(String nama, int no_akun, int nilai_akun) {
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
