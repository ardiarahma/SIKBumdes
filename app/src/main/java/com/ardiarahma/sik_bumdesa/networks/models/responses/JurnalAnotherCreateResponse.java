package com.ardiarahma.sik_bumdesa.networks.models.responses;

import com.ardiarahma.sik_bumdesa.networks.models.Results;
import com.ardiarahma.sik_bumdesa.networks.models.Results_1;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 on 11/12/2019.
 */

public class JurnalAnotherCreateResponse {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("result")
    @Expose
    private Results result;

    public JurnalAnotherCreateResponse(String status, Results result) {
        this.status = status;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Results getResult() {
        return result;
    }

    public void setResult(Results result) {
        this.result = result;
    }
}
