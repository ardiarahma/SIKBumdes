package com.ardiarahma.sik_bumdesa.networks.models.responses;

import com.ardiarahma.sik_bumdesa.networks.models.LabaRugi_Biaya;
import com.ardiarahma.sik_bumdesa.networks.models.LabaRugi_BiayaLainLain;
import com.ardiarahma.sik_bumdesa.networks.models.LabaRugi_Pendapatan;
import com.ardiarahma.sik_bumdesa.networks.models.LabaRugi_PendapatanLainLain;
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

    @SerializedName("Pendapatan Lain-lain")
    @Expose
    private ArrayList<LabaRugi_PendapatanLainLain> pendapatanLainLains = null;

    @SerializedName("Biaya Lain-lain")
    @Expose
    private ArrayList<LabaRugi_BiayaLainLain> biayaLainLains = null;

    @SerializedName("Total Lain-lain")
    @Expose
    private int totalLainLain;

    @SerializedName("Saldo laba/rugi tahun berjalan")
    @Expose
    private int saldoLabaRugi;

    public LabaRugiResponse(String status, ArrayList<LabaRugi_Pendapatan> labaRugiPendapatans, int totalPendapatan, ArrayList<LabaRugi_Biaya> labaRugiBiayas, int totalBiaya, int labaUsaha, ArrayList<LabaRugi_PendapatanLainLain> pendapatanLainLains, ArrayList<LabaRugi_BiayaLainLain> biayaLainLains, int totalLainLain, int saldoLabaRugi) {
        this.status = status;
        this.labaRugiPendapatans = labaRugiPendapatans;
        this.totalPendapatan = totalPendapatan;
        this.labaRugiBiayas = labaRugiBiayas;
        this.totalBiaya = totalBiaya;
        this.labaUsaha = labaUsaha;
        this.pendapatanLainLains = pendapatanLainLains;
        this.biayaLainLains = biayaLainLains;
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

    public ArrayList<LabaRugi_PendapatanLainLain> getPendapatanLainLains() {
        return pendapatanLainLains;
    }

    public void setPendapatanLainLains(ArrayList<LabaRugi_PendapatanLainLain> pendapatanLainLains) {
        this.pendapatanLainLains = pendapatanLainLains;
    }

    public ArrayList<LabaRugi_BiayaLainLain> getBiayaLainLains() {
        return biayaLainLains;
    }

    public void setBiayaLainLains(ArrayList<LabaRugi_BiayaLainLain> biayaLainLains) {
        this.biayaLainLains = biayaLainLains;
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
