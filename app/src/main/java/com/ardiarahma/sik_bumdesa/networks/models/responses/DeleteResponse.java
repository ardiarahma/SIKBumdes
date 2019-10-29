package com.ardiarahma.sik_bumdesa.networks.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 on 10/29/2019.
 */

public class DeleteResponse {

    @SerializedName("status")
    @Expose
    private String status;

    public DeleteResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
