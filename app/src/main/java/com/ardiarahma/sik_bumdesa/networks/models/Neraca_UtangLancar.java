package com.ardiarahma.sik_bumdesa.networks.models;

/**
 * Created by Windows 10 on 8/19/2019.
 */

public class Neraca_UtangLancar {

    String akun;
    int jumlah;

    public Neraca_UtangLancar(String akun, int jumlah) {
        this.akun = akun;
        this.jumlah = jumlah;
    }

    public String getAkun() {
        return akun;
    }

    public void setAkun(String akun) {
        this.akun = akun;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
