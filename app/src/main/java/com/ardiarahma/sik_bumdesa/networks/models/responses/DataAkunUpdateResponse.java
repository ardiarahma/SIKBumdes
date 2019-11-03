package com.ardiarahma.sik_bumdesa.networks.models.responses;

import com.ardiarahma.sik_bumdesa.networks.models.Akun_UpdateKlasfikasiAkun;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 on 10/31/2019.
 */

public class DataAkunUpdateResponse {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("result")
    @Expose
    private Akun_UpdateKlasfikasiAkun akun_updateKlasfikasiAkun;

    public DataAkunUpdateResponse(String status, Akun_UpdateKlasfikasiAkun akun_updateKlasfikasiAkun) {
        this.status = status;
        this.akun_updateKlasfikasiAkun = akun_updateKlasfikasiAkun;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Akun_UpdateKlasfikasiAkun getAkun_updateKlasfikasiAkun() {
        return akun_updateKlasfikasiAkun;
    }

    public void setAkun_updateKlasfikasiAkun(Akun_UpdateKlasfikasiAkun akun_updateKlasfikasiAkun) {
        this.akun_updateKlasfikasiAkun = akun_updateKlasfikasiAkun;
    }
}
