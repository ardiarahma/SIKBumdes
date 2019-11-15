package com.ardiarahma.sik_bumdesa.networks.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteKlasifikasiResponse {

    @SerializedName("status")
    @Expose
    private String status;

    public DeleteKlasifikasiResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
