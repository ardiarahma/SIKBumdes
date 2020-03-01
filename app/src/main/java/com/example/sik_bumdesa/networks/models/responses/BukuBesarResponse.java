package com.example.sik_bumdesa.networks.models.responses;

import com.example.sik_bumdesa.networks.models.BukuBesar;
import com.example.sik_bumdesa.networks.models.SaldoAwal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 1/16/2020.
 */

public class BukuBesarResponse {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("saldo_awal")
    @Expose
    private SaldoAwal saldo_awal;

    @SerializedName("buku_besar")
    @Expose
    private ArrayList<BukuBesar> buku_besar = null;

    @SerializedName("total_kredit")
    @Expose
    private int total_kredit;

    @SerializedName("total_debit")
    @Expose
    private int total_debit;

    public BukuBesarResponse(String status, SaldoAwal saldo_awal, ArrayList<BukuBesar> buku_besar, int total_kredit, int total_debit) {
        this.status = status;
        this.saldo_awal = saldo_awal;
        this.buku_besar = buku_besar;
        this.total_kredit = total_kredit;
        this.total_debit = total_debit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SaldoAwal getSaldo_awal() {
        return saldo_awal;
    }

    public void setSaldo_awal(SaldoAwal saldo_awal) {
        this.saldo_awal = saldo_awal;
    }

    public ArrayList<BukuBesar> getBuku_besar() {
        return buku_besar;
    }

    public void setBuku_besar(ArrayList<BukuBesar> buku_besar) {
        this.buku_besar = buku_besar;
    }

    public int getTotal_kredit() {
        return total_kredit;
    }

    public void setTotal_kredit(int total_kredit) {
        this.total_kredit = total_kredit;
    }

    public int getTotal_debit() {
        return total_debit;
    }

    public void setTotal_debit(int total_debit) {
        this.total_debit = total_debit;
    }
}
