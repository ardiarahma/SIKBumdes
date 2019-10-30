package com.ardiarahma.sik_bumdesa.networks.models.responses;

import com.ardiarahma.sik_bumdesa.networks.models.Akun_KlasfikasiAkun;
import com.ardiarahma.sik_bumdesa.networks.models.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class KlasifikasiAkunCreateResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("result")
    @Expose
    private ArrayList<Akun_KlasfikasiAkun> akun_klasfikasiAkun;

    public KlasifikasiAkunCreateResponse(String status, ArrayList<Akun_KlasfikasiAkun> akun_klasfikasiAkun) {
        this.status = status;
        this.akun_klasfikasiAkun = akun_klasfikasiAkun;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Akun_KlasfikasiAkun> getAkun_klasfikasiAkun() {
        return akun_klasfikasiAkun;
    }

    public void setAkun_klasfikasiAkun(ArrayList<Akun_KlasfikasiAkun> akun_klasfikasiAkun) {
        this.akun_klasfikasiAkun = akun_klasfikasiAkun;
    }
}
