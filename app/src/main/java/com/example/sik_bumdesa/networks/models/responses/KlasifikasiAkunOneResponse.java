package com.example.sik_bumdesa.networks.models.responses;

import com.example.sik_bumdesa.networks.models.Akun_KlasfikasiAkun;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 on 10/29/2019.
 */

public class KlasifikasiAkunOneResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("parent")
    @Expose
    private Akun_KlasfikasiAkun klasfikasiAkun;

    public KlasifikasiAkunOneResponse(String status, Akun_KlasfikasiAkun klasfikasiAkun) {
        this.status = status;
        this.klasfikasiAkun = klasfikasiAkun;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Akun_KlasfikasiAkun getKlasfikasiAkun() {
        return klasfikasiAkun;
    }

    public void setKlasfikasiAkun(Akun_KlasfikasiAkun klasfikasiAkun) {
        this.klasfikasiAkun = klasfikasiAkun;
    }
}
