package com.example.sik_bumdesa.networks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 on 8/19/2019.
 */

public class Ekuitas_Saldo {

    @SerializedName("0")
    @Expose
    public Ekuitas_SaldoDitahan saldo_ditahan;

    @SerializedName("Saldo laba tahun berjalan")
    @Expose
    private int saldo_berjalan;

    public Ekuitas_Saldo(Ekuitas_SaldoDitahan saldo_ditahan, int saldo_berjalan) {
        this.saldo_ditahan = saldo_ditahan;
        this.saldo_berjalan = saldo_berjalan;
    }

    public Ekuitas_SaldoDitahan getSaldo_ditahan() {
        return saldo_ditahan;
    }

    public void setSaldo_ditahan(Ekuitas_SaldoDitahan saldo_ditahan) {
        this.saldo_ditahan = saldo_ditahan;
    }

    public int getSaldo_berjalan() {
        return saldo_berjalan;
    }

    public void setSaldo_berjalan(int saldo_berjalan) {
        this.saldo_berjalan = saldo_berjalan;
    }
}
