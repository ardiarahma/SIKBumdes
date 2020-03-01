package com.example.sik_bumdesa.networks.models.responses;

import com.example.sik_bumdesa.networks.models.Jurnal_All;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JurnalResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("jurnal")
    @Expose
    private ArrayList<Jurnal_All> jurnalAlls = null;

    @SerializedName("total_kredit")
    @Expose
    private String total_kredit;

    @SerializedName("total_debit")
    @Expose
    private String total_debit;

    public JurnalResponse(String status, ArrayList<Jurnal_All> jurnalAlls, String total_kredit, String total_debit) {
        this.status = status;
        this.jurnalAlls = jurnalAlls;
        this.total_kredit = total_kredit;
        this.total_debit = total_debit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Jurnal_All> getJurnalAlls() {
        return jurnalAlls;
    }

    public void setJurnalAlls(ArrayList<Jurnal_All> jurnalAlls) {
        this.jurnalAlls = jurnalAlls;
    }

    public String getTotal_kredit() {
        return total_kredit;
    }

    public void setTotal_kredit(String total_kredit) {
        this.total_kredit = total_kredit;
    }

    public String getTotal_debit() {
        return total_debit;
    }

    public void setTotal_debit(String total_debit) {
        this.total_debit = total_debit;
    }
}
