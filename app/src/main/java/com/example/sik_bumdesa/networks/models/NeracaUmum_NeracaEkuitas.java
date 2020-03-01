package com.example.sik_bumdesa.networks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 on 11/22/2019.
 */

public class NeracaUmum_NeracaEkuitas {

    @SerializedName("Modal disetor")
    @Expose
    private Ekuitas_Modal ekuitas_modal;

    @SerializedName("Saldo laba ditahan")
    @Expose
    private Ekuitas_SaldoDitahan ekuitas_saldoDitahan;

    @SerializedName("Saldo laba tahun berjalan")
    @Expose
    private int ekuitas_saldojalan;

    public NeracaUmum_NeracaEkuitas(Ekuitas_Modal ekuitas_modal, Ekuitas_SaldoDitahan ekuitas_saldoDitahan, int ekuitas_saldojalan) {
        this.ekuitas_modal = ekuitas_modal;
        this.ekuitas_saldoDitahan = ekuitas_saldoDitahan;
        this.ekuitas_saldojalan = ekuitas_saldojalan;
    }

    public Ekuitas_Modal getEkuitas_modal() {
        return ekuitas_modal;
    }

    public void setEkuitas_modal(Ekuitas_Modal ekuitas_modal) {
        this.ekuitas_modal = ekuitas_modal;
    }

    public Ekuitas_SaldoDitahan getEkuitas_saldoDitahan() {
        return ekuitas_saldoDitahan;
    }

    public void setEkuitas_saldoDitahan(Ekuitas_SaldoDitahan ekuitas_saldoDitahan) {
        this.ekuitas_saldoDitahan = ekuitas_saldoDitahan;
    }

    public int getEkuitas_saldojalan() {
        return ekuitas_saldojalan;
    }

    public void setEkuitas_saldojalan(int ekuitas_saldojalan) {
        this.ekuitas_saldojalan = ekuitas_saldojalan;
    }
}
