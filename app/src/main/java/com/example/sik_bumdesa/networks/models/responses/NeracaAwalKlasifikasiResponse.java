package com.example.sik_bumdesa.networks.models.responses;

import com.example.sik_bumdesa.networks.models.NeracaAwal_Klasifikasi;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NeracaAwalKlasifikasiResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("klasifikasi_akun")
    @Expose
    private ArrayList<NeracaAwal_Klasifikasi> neracaAwalKlasifikasis = null;

    public NeracaAwalKlasifikasiResponse(String status, ArrayList<NeracaAwal_Klasifikasi> neracaAwalKlasifikasis) {
        this.status = status;
        this.neracaAwalKlasifikasis = neracaAwalKlasifikasis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<NeracaAwal_Klasifikasi> getNeracaAwalKlasifikasis() {
        return neracaAwalKlasifikasis;
    }

    public void setNeracaAwalKlasifikasis(ArrayList<NeracaAwal_Klasifikasi> neracaAwalKlasifikasis) {
        this.neracaAwalKlasifikasis = neracaAwalKlasifikasis;
    }
}
