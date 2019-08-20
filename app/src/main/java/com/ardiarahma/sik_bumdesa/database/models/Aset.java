package com.ardiarahma.sik_bumdesa.database.models;

/**
 * Created by Windows 10 on 8/19/2019.
 */

public class Aset {

    String akun;
    int jumlah;

    public Aset(String akun, int jumlah) {
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
