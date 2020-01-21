package com.ardiarahma.sik_bumdesa.networks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 on 1/16/2020.
 */

public class BukuBesar {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("tanggal")
    @Expose
    private String tanggal;

    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    @SerializedName("Saldo_Awal")
    @Expose
    private String saldo_awal;

    @SerializedName("Debit")
    @Expose
    private int debit;

    @SerializedName("Kredit")
    @Expose
    private int kredit;

    @SerializedName("Saldo")
    @Expose
    private int saldo;

    public BukuBesar(int id, String tanggal, String keterangan, String saldo_awal, int debit, int kredit, int saldo) {
        this.id = id;
        this.tanggal = tanggal;
        this.keterangan = keterangan;
        this.saldo_awal = saldo_awal;
        this.debit = debit;
        this.kredit = kredit;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getSaldo_awal() {
        return saldo_awal;
    }

    public void setSaldo_awal(String saldo_awal) {
        this.saldo_awal = saldo_awal;
    }

    public int getDebit() {
        return debit;
    }

    public void setDebit(int debit) {
        this.debit = debit;
    }

    public int getKredit() {
        return kredit;
    }

    public void setKredit(int kredit) {
        this.kredit = kredit;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
