package com.example.sik_bumdesa.networks.models.responses;

import com.example.sik_bumdesa.networks.models.GetAllAkun;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetAllAkunResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("akun")
    @Expose
    private ArrayList<GetAllAkun> neracaAwalAllAkuns = null;

    public GetAllAkunResponse(String status, ArrayList<GetAllAkun> neracaAwalAllAkuns) {
        this.status = status;
        this.neracaAwalAllAkuns = neracaAwalAllAkuns;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<GetAllAkun> getNeracaAwalAllAkuns() {
        return neracaAwalAllAkuns;
    }

    public void setNeracaAwalAllAkuns(ArrayList<GetAllAkun> neracaAwalAllAkuns) {
        this.neracaAwalAllAkuns = neracaAwalAllAkuns;
    }
}
