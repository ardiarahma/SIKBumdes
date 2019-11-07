package com.ardiarahma.sik_bumdesa.networks.models.responses;

import com.ardiarahma.sik_bumdesa.networks.models.LabaRugi_Biaya;
import com.ardiarahma.sik_bumdesa.networks.models.LabaRugi_LainLain;
import com.ardiarahma.sik_bumdesa.networks.models.LabaRugi_Pendapatan;
import com.ardiarahma.sik_bumdesa.networks.models.NeracaAwal_Klasifikasi;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LabaRugiResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("Pendapatan")
    @Expose
    private ArrayList<LabaRugi_Pendapatan> labaRugiPendapatans = null;

    @SerializedName("Total Pendapatan")
    @Expose
    private int totalPendapatan;

    @SerializedName("Biaya")
    @Expose
    private ArrayList<LabaRugi_Biaya> labaRugiBiayas = null;

    @SerializedName("Total Biaya")
    @Expose
    private int totalBiaya;

    @SerializedName("Laba Usaha")
    @Expose
    private int labaUsaha;

    @SerializedName("Lain-lain")
    @Expose
    private ArrayList<LabaRugi_LainLain> labaRugiLainLains = null;

    @SerializedName("Total Lain-lain")
    @Expose
    private int totalLainLain;

    @SerializedName("Saldo laba/rugi tahun berjalan")
    @Expose
    private int saldoLabaRugi;

    public LabaRugiResponse(String status, ArrayList<LabaRugi_Pendapatan> labaRugiPendapatans, int totalPendapatan, ArrayList<LabaRugi_Biaya> labaRugiBiayas, int totalBiaya, int labaUsaha, ArrayList<LabaRugi_LainLain> labaRugiLainLains, int totalLainLain, int saldoLabaRugi) {
        this.status = status;
        this.labaRugiPendapatans = labaRugiPendapatans;
        this.totalPendapatan = totalPendapatan;
        this.labaRugiBiayas = labaRugiBiayas;
        this.totalBiaya = totalBiaya;
        this.labaUsaha = labaUsaha;
        this.labaRugiLainLains = labaRugiLainLains;
        this.totalLainLain = totalLainLain;
        this.saldoLabaRugi = saldoLabaRugi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<LabaRugi_Pendapatan> getLabaRugiPendapatans() {
        return labaRugiPendapatans;
    }

    public void setLabaRugiPendapatans(ArrayList<LabaRugi_Pendapatan> labaRugiPendapatans) {
        this.labaRugiPendapatans = labaRugiPendapatans;
    }

    public int getTotalPendapatan() {
        return totalPendapatan;
    }

    public void setTotalPendapatan(int totalPendapatan) {
        this.totalPendapatan = totalPendapatan;
    }

    public ArrayList<LabaRugi_Biaya> getLabaRugiBiayas() {
        return labaRugiBiayas;
    }

    public void setLabaRugiBiayas(ArrayList<LabaRugi_Biaya> labaRugiBiayas) {
        this.labaRugiBiayas = labaRugiBiayas;
    }

    public int getTotalBiaya() {
        return totalBiaya;
    }

    public void setTotalBiaya(int totalBiaya) {
        this.totalBiaya = totalBiaya;
    }

    public int getLabaUsaha() {
        return labaUsaha;
    }

    public void setLabaUsaha(int labaUsaha) {
        this.labaUsaha = labaUsaha;
    }

    public ArrayList<LabaRugi_LainLain> getLabaRugiLainLains() {
        return labaRugiLainLains;
    }

    public void setLabaRugiLainLains(ArrayList<LabaRugi_LainLain> labaRugiLainLains) {
        this.labaRugiLainLains = labaRugiLainLains;
    }

    public int getTotalLainLain() {
        return totalLainLain;
    }

    public void setTotalLainLain(int totalLainLain) {
        this.totalLainLain = totalLainLain;
    }

    public int getSaldoLabaRugi() {
        return saldoLabaRugi;
    }

    public void setSaldoLabaRugi(int saldoLabaRugi) {
        this.saldoLabaRugi = saldoLabaRugi;
    }
}
