package com.ardiarahma.sik_bumdesa.networks.models.responses;

import com.ardiarahma.sik_bumdesa.networks.models.NeracaAwal;
import com.ardiarahma.sik_bumdesa.networks.models.ParentAkun;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 10/1/2019.
 */

public class NeracaAwalResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("neraca_awal")
    @Expose
    private ArrayList<NeracaAwal> neracaAwals = null;

    @SerializedName("total_kredit")
    @Expose
    private int total_kredit;

    @SerializedName("total_debit")
    @Expose
    private int total_debit;

    public NeracaAwalResponse(String status, ArrayList<NeracaAwal> neracaAwals, int total_kredit, int total_debit) {
        this.status = status;
        this.neracaAwals = neracaAwals;
        this.total_kredit = total_kredit;
        this.total_debit = total_debit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<NeracaAwal> getNeracaAwals() {
        return neracaAwals;
    }

    public void setNeracaAwals(ArrayList<NeracaAwal> neracaAwals) {
        this.neracaAwals = neracaAwals;
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
