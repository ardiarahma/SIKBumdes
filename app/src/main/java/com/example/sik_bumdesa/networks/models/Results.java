package com.example.sik_bumdesa.networks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 11/8/2019.
 */

public class Results {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("no_kwitansi")
    @Expose
    private String kwitansi;

    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    @SerializedName("jurnal")
    @Expose
    private ArrayList<Jurnal> jurnals = null;

    public Results(int id, String kwitansi, String keterangan, ArrayList<Jurnal> jurnals) {
        this.id = id;
        this.kwitansi = kwitansi;
        this.keterangan = keterangan;
        this.jurnals = jurnals;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKwitansi() {
        return kwitansi;
    }

    public void setKwitansi(String kwitansi) {
        this.kwitansi = kwitansi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public ArrayList<Jurnal> getJurnals() {
        return jurnals;
    }

    public void setJurnals(ArrayList<Jurnal> jurnals) {
        this.jurnals = jurnals;
    }
}