package com.ardiarahma.sik_bumdesa.networks.models.responses;

import com.ardiarahma.sik_bumdesa.networks.models.Akun_KlasfikasiAkun;
import com.ardiarahma.sik_bumdesa.networks.models.KlasifikasiAkun;
import com.ardiarahma.sik_bumdesa.networks.models.ParentAkun;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 10/1/2019.
 */

public class KlasifikasiAkunResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("klasifikasi_akun")
    @Expose
    private ArrayList<Akun_KlasfikasiAkun> akun_klasfikasiAkuns = null;

    public KlasifikasiAkunResponse(String status, ArrayList<Akun_KlasfikasiAkun> akun_klasfikasiAkuns) {
        this.status = status;
        this.akun_klasfikasiAkuns = akun_klasfikasiAkuns;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Akun_KlasfikasiAkun> getAkun_klasfikasiAkuns() {
        return akun_klasfikasiAkuns;
    }

    public void setAkun_klasfikasiAkuns(ArrayList<Akun_KlasfikasiAkun> akun_klasfikasiAkuns) {
        this.akun_klasfikasiAkuns = akun_klasfikasiAkuns;
    }
}
