package com.ardiarahma.sik_bumdesa.networks.models.responses;

import com.ardiarahma.sik_bumdesa.networks.models.NeracaAwal_Akun;
import com.ardiarahma.sik_bumdesa.networks.models.NeracaAwal_Klasifikasi;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NeracaAwalAkunResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("neraca_awal")
    @Expose
    private ArrayList<ArrayList<NeracaAwal_Akun>> neracaAwalAkuns = null;

    public NeracaAwalAkunResponse(String status, ArrayList<ArrayList<NeracaAwal_Akun>> neracaAwalAkuns) {
        this.status = status;
        this.neracaAwalAkuns = neracaAwalAkuns;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<ArrayList<NeracaAwal_Akun>> getNeracaAwalAkuns() {
        return neracaAwalAkuns;
    }

    public void setNeracaAwalAkuns(ArrayList<ArrayList<NeracaAwal_Akun>> neracaAwalAkuns) {
        this.neracaAwalAkuns = neracaAwalAkuns;
    }
}
