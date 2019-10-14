package com.ardiarahma.sik_bumdesa.networks.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 on 10/10/2019.
 */

public class AkunExp {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("id_klasifikasi_akun")
    @Expose
    private int id_klasifikasi_akun;

    @SerializedName("nama")
    @Expose
    private String name;

    @SerializedName("posisi_normal")
    @Expose
    private String posisi_normal;

    public AkunExp(int id, int id_klasifikasi_akun, String name, String posisi_normal) {
        this.id = id;
        this.id_klasifikasi_akun = id_klasifikasi_akun;
        this.name = name;
        this.posisi_normal = posisi_normal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_klasifikasi_akun() {
        return id_klasifikasi_akun;
    }

    public void setId_klasifikasi_akun(int id_klasifikasi_akun) {
        this.id_klasifikasi_akun = id_klasifikasi_akun;
    }

    public String getPosisi_normal() {
        return posisi_normal;
    }

    public void setPosisi_normal(String posisi_normal) {
        this.posisi_normal = posisi_normal;
    }
}
