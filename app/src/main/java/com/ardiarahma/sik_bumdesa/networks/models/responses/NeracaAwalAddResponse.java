package com.ardiarahma.sik_bumdesa.networks.models.responses;

import com.ardiarahma.sik_bumdesa.networks.models.NeracaAwal_Add;
import com.ardiarahma.sik_bumdesa.networks.models.NeracaAwal_Parent;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NeracaAwalAddResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("result")
    @Expose
    private NeracaAwal_Add neracaAwalAdd;

    public NeracaAwalAddResponse(String status, NeracaAwal_Add neracaAwalAdd) {
        this.status = status;
        this.neracaAwalAdd = neracaAwalAdd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public NeracaAwal_Add getNeracaAwalAdd() {
        return neracaAwalAdd;
    }

    public void setNeracaAwalAdd(NeracaAwal_Add neracaAwalAdd) {
        this.neracaAwalAdd = neracaAwalAdd;
    }
}
