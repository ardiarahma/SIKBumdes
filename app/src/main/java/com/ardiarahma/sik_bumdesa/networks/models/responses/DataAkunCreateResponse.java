package com.ardiarahma.sik_bumdesa.networks.models.responses;

import com.ardiarahma.sik_bumdesa.networks.models.Akun_DataAkun;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 10/14/2019.
 */

public class DataAkunCreateResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("result")
    @Expose
    private ArrayList<Akun_DataAkun> akun_dataAkuns = null;

    public DataAkunCreateResponse(String status, ArrayList<Akun_DataAkun> akun_dataAkuns) {
        this.status = status;
        this.akun_dataAkuns = akun_dataAkuns;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Akun_DataAkun> getAkun_dataAkuns() {
        return akun_dataAkuns;
    }

    public void setAkun_dataAkuns(ArrayList<Akun_DataAkun> akun_dataAkuns) {
        this.akun_dataAkuns = akun_dataAkuns;
    }
}
