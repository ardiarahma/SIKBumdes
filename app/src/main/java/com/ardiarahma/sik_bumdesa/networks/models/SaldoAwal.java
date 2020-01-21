package com.ardiarahma.sik_bumdesa.networks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 on 1/16/2020.
 */

public class SaldoAwal {

    @SerializedName("jumlah")
    @Expose
    private String jumlah;

    public SaldoAwal(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }
}
