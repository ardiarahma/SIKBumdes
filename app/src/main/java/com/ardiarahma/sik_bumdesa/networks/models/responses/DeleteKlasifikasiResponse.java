package com.ardiarahma.sik_bumdesa.networks.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteKlasifikasiResponse {

    @SerializedName("success")
    @Expose
    private String success;

    public DeleteKlasifikasiResponse(String success) {
        this.success = success;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
