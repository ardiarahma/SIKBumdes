package com.ardiarahma.sik_bumdesa.database.models;

/**
 * Created by Windows 10 on 8/29/2019.
 */

public class Jurnal {

    private int id;
    private String keterangan;
    private int no_akun;
    private int no_kwitansi;
    private String posisi_normal;
    private String date;

    public Jurnal(int id, String keterangan, int no_akun, int no_kwitansi, String posisi_normal, String date) {
        this.id = id;
        this.keterangan = keterangan;
        this.no_akun = no_akun;
        this.no_kwitansi = no_kwitansi;
        this.posisi_normal = posisi_normal;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getNo_akun() {
        return no_akun;
    }

    public void setNo_akun(int no_akun) {
        this.no_akun = no_akun;
    }

    public int getNo_kwitansi() {
        return no_kwitansi;
    }

    public void setNo_kwitansi(int no_kwitansi) {
        this.no_kwitansi = no_kwitansi;
    }

    public String getPosisi_normal() {
        return posisi_normal;
    }

    public void setPosisi_normal(String posisi_normal) {
        this.posisi_normal = posisi_normal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
