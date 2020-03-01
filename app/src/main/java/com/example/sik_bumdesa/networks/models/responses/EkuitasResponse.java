package com.example.sik_bumdesa.networks.models.responses;

import com.example.sik_bumdesa.networks.models.Ekuitas_Modal;
import com.example.sik_bumdesa.networks.models.Ekuitas_Saldo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 on 11/15/2019.
 */

public class EkuitasResponse {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("MODAL AWAL")
    @Expose
    private Ekuitas_Modal ekuitas_modals;

    @SerializedName("SALDO LABA")
    @Expose
    private Ekuitas_Saldo ekuitas_saldos;

    @SerializedName("TOTAL EKUITAS AKHIR PERIODE")
    @Expose
    private int totalEkuitas;

    public EkuitasResponse(String status, Ekuitas_Modal ekuitas_modals, Ekuitas_Saldo ekuitas_saldos, int totalEkuitas) {
        this.status = status;
        this.ekuitas_modals = ekuitas_modals;
        this.ekuitas_saldos = ekuitas_saldos;
        this.totalEkuitas = totalEkuitas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Ekuitas_Modal getEkuitas_modals() {
        return ekuitas_modals;
    }

    public void setEkuitas_modals(Ekuitas_Modal ekuitas_modals) {
        this.ekuitas_modals = ekuitas_modals;
    }

    public Ekuitas_Saldo getEkuitas_saldos() {
        return ekuitas_saldos;
    }

    public void setEkuitas_saldos(Ekuitas_Saldo ekuitas_saldos) {
        this.ekuitas_saldos = ekuitas_saldos;
    }

    public int getTotalEkuitas() {
        return totalEkuitas;
    }

    public void setTotalEkuitas(int totalEkuitas) {
        this.totalEkuitas = totalEkuitas;
    }
}
