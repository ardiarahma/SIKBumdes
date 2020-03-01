package com.example.sik_bumdesa.networks.models.responses;

import com.example.sik_bumdesa.networks.models.NeracaAwal_Parent;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NeracaAwalParentResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("parent")
    @Expose
    private ArrayList<NeracaAwal_Parent> neracaAwalParents = null;

    @SerializedName("total_debit")
    @Expose
    private int totalDebit;

    @SerializedName("total_kredit")
    @Expose
    private int totalKredit;

    public NeracaAwalParentResponse(String status, ArrayList<NeracaAwal_Parent> neracaAwalParents, int totalDebit, int totalKredit) {
        this.status = status;
        this.neracaAwalParents = neracaAwalParents;
        this.totalDebit = totalDebit;
        this.totalKredit = totalKredit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<NeracaAwal_Parent> getNeracaAwalParents() {
        return neracaAwalParents;
    }

    public void setNeracaAwalParents(ArrayList<NeracaAwal_Parent> neracaAwalParents) {
        this.neracaAwalParents = neracaAwalParents;
    }

    public int getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(int totalDebit) {
        this.totalDebit = totalDebit;
    }

    public int getTotalKredit() {
        return totalKredit;
    }

    public void setTotalKredit(int totalKredit) {
        this.totalKredit = totalKredit;
    }
}
