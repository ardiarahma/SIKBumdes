package com.ardiarahma.sik_bumdesa.networks.models.responses;

import com.ardiarahma.sik_bumdesa.networks.models.NeracaAwal_Akun;
import com.ardiarahma.sik_bumdesa.networks.models.NeracaAwal_AllAkun;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NeracaAwalAllAkunResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("akun")
    @Expose
    private ArrayList<NeracaAwal_AllAkun> neracaAwalAllAkuns = null;

    public NeracaAwalAllAkunResponse(String status, ArrayList<NeracaAwal_AllAkun> neracaAwalAllAkuns) {
        this.status = status;
        this.neracaAwalAllAkuns = neracaAwalAllAkuns;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<NeracaAwal_AllAkun> getNeracaAwalAllAkuns() {
        return neracaAwalAllAkuns;
    }

    public void setNeracaAwalAllAkuns(ArrayList<NeracaAwal_AllAkun> neracaAwalAllAkuns) {
        this.neracaAwalAllAkuns = neracaAwalAllAkuns;
    }
}
