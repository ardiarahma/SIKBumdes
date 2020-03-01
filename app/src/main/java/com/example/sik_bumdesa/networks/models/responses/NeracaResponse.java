package com.example.sik_bumdesa.networks.models.responses;

import com.example.sik_bumdesa.networks.models.NeracaUmum_LiabilitasJangkaPanjang;
import com.example.sik_bumdesa.networks.models.NeracaUmum_LiabilitasLancar;
import com.example.sik_bumdesa.networks.models.NeracaUmum_NeracaEkuitas;
import com.example.sik_bumdesa.networks.models.NeracaUmum_AsetLancar;
import com.example.sik_bumdesa.networks.models.NeracaUmum_AsetTetap;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 11/22/2019.
 */

public class NeracaResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("Aset Lancar")
    @Expose
    private ArrayList<NeracaUmum_AsetLancar> neracaUmum_asetLancars = null;

    @SerializedName("Total Aset Lancar")
    @Expose
    private int totalAsetLancar;

    @SerializedName("Biaya")
    @Expose
    private ArrayList<NeracaUmum_AsetTetap> neracaUmum_asetTetaps = null;

    @SerializedName("Total Aset Tetap")
    @Expose
    private int totalAsetTetap;

    @SerializedName("Liabilitas Lancar")
    @Expose
    private ArrayList<NeracaUmum_LiabilitasLancar> neracaUmum_liabilitasLancars = null;

    @SerializedName("Total Liabilitas Lancar")
    @Expose
    private int totalLiabilitas_lancar;

    @SerializedName("Liabilitas Jangka Panjang")
    @Expose
    private ArrayList<NeracaUmum_LiabilitasJangkaPanjang> neracaUmum_liabilitasJangkaPanjangs = null;

    @SerializedName("Total Liabilitas Jangka Panjang")
    @Expose
    private int totalLiabilitas_JP;

    @SerializedName("EKUITAS")
    @Expose
    private NeracaUmum_NeracaEkuitas neracaUmumNeracaEkuitas;

    @SerializedName("Total Aset")
    @Expose
    private int totalAset;

    @SerializedName("Total Liabilitas")
    @Expose
    private int totalLiabilitas;

    @SerializedName("Total Ekuitas")
    @Expose
    private int totalEkuitas;

    @SerializedName("Total Liabilitas dan Ekuitas")
    @Expose
    private int totalLiaEku;

    public NeracaResponse(String status, ArrayList<NeracaUmum_AsetLancar> neracaUmum_asetLancars, int totalAsetLancar, ArrayList<NeracaUmum_AsetTetap> neracaUmum_asetTetaps, int totalAsetTetap, ArrayList<NeracaUmum_LiabilitasLancar> neracaUmum_liabilitasLancars, int totalLiabilitas_lancar, ArrayList<NeracaUmum_LiabilitasJangkaPanjang> neracaUmum_liabilitasJangkaPanjangs, int totalLiabilitas_JP, NeracaUmum_NeracaEkuitas neracaUmumNeracaEkuitas, int totalAset, int totalLiabilitas, int totalEkuitas, int totalLiaEku) {
        this.status = status;
        this.neracaUmum_asetLancars = neracaUmum_asetLancars;
        this.totalAsetLancar = totalAsetLancar;
        this.neracaUmum_asetTetaps = neracaUmum_asetTetaps;
        this.totalAsetTetap = totalAsetTetap;
        this.neracaUmum_liabilitasLancars = neracaUmum_liabilitasLancars;
        this.totalLiabilitas_lancar = totalLiabilitas_lancar;
        this.neracaUmum_liabilitasJangkaPanjangs = neracaUmum_liabilitasJangkaPanjangs;
        this.totalLiabilitas_JP = totalLiabilitas_JP;
        this.neracaUmumNeracaEkuitas = neracaUmumNeracaEkuitas;
        this.totalAset = totalAset;
        this.totalLiabilitas = totalLiabilitas;
        this.totalEkuitas = totalEkuitas;
        this.totalLiaEku = totalLiaEku;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<NeracaUmum_AsetLancar> getNeracaUmum_asetLancars() {
        return neracaUmum_asetLancars;
    }

    public void setNeracaUmum_asetLancars(ArrayList<NeracaUmum_AsetLancar> neracaUmum_asetLancars) {
        this.neracaUmum_asetLancars = neracaUmum_asetLancars;
    }

    public int getTotalAsetLancar() {
        return totalAsetLancar;
    }

    public void setTotalAsetLancar(int totalAsetLancar) {
        this.totalAsetLancar = totalAsetLancar;
    }

    public ArrayList<NeracaUmum_AsetTetap> getNeracaUmum_asetTetaps() {
        return neracaUmum_asetTetaps;
    }

    public void setNeracaUmum_asetTetaps(ArrayList<NeracaUmum_AsetTetap> neracaUmum_asetTetaps) {
        this.neracaUmum_asetTetaps = neracaUmum_asetTetaps;
    }

    public int getTotalAsetTetap() {
        return totalAsetTetap;
    }

    public void setTotalAsetTetap(int totalAsetTetap) {
        this.totalAsetTetap = totalAsetTetap;
    }

    public ArrayList<NeracaUmum_LiabilitasLancar> getNeracaUmum_liabilitasLancars() {
        return neracaUmum_liabilitasLancars;
    }

    public void setNeracaUmum_liabilitasLancars(ArrayList<NeracaUmum_LiabilitasLancar> neracaUmum_liabilitasLancars) {
        this.neracaUmum_liabilitasLancars = neracaUmum_liabilitasLancars;
    }

    public int getTotalLiabilitas_lancar() {
        return totalLiabilitas_lancar;
    }

    public void setTotalLiabilitas_lancar(int totalLiabilitas_lancar) {
        this.totalLiabilitas_lancar = totalLiabilitas_lancar;
    }

    public ArrayList<NeracaUmum_LiabilitasJangkaPanjang> getNeracaUmum_liabilitasJangkaPanjangs() {
        return neracaUmum_liabilitasJangkaPanjangs;
    }

    public void setNeracaUmum_liabilitasJangkaPanjangs(ArrayList<NeracaUmum_LiabilitasJangkaPanjang> neracaUmum_liabilitasJangkaPanjangs) {
        this.neracaUmum_liabilitasJangkaPanjangs = neracaUmum_liabilitasJangkaPanjangs;
    }

    public int getTotalLiabilitas_JP() {
        return totalLiabilitas_JP;
    }

    public void setTotalLiabilitas_JP(int totalLiabilitas_JP) {
        this.totalLiabilitas_JP = totalLiabilitas_JP;
    }

    public NeracaUmum_NeracaEkuitas getNeracaUmumNeracaEkuitas() {
        return neracaUmumNeracaEkuitas;
    }

    public void setNeracaUmumNeracaEkuitas(NeracaUmum_NeracaEkuitas neracaUmumNeracaEkuitas) {
        this.neracaUmumNeracaEkuitas = neracaUmumNeracaEkuitas;
    }

    public int getTotalAset() {
        return totalAset;
    }

    public void setTotalAset(int totalAset) {
        this.totalAset = totalAset;
    }

    public int getTotalLiabilitas() {
        return totalLiabilitas;
    }

    public void setTotalLiabilitas(int totalLiabilitas) {
        this.totalLiabilitas = totalLiabilitas;
    }

    public int getTotalEkuitas() {
        return totalEkuitas;
    }

    public void setTotalEkuitas(int totalEkuitas) {
        this.totalEkuitas = totalEkuitas;
    }

    public int getTotalLiaEku() {
        return totalLiaEku;
    }

    public void setTotalLiaEku(int totalLiaEku) {
        this.totalLiaEku = totalLiaEku;
    }
}
