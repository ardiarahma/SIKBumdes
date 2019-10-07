package com.ardiarahma.sik_bumdesa.networks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 on 8/11/2019.
 */

public class User {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("no_telepon")
    @Expose
    private int no_telepon;

    @SerializedName("alamat")
    @Expose
    private String alamat;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("token")
    @Expose
    private String token;

    public User(int id, String nama, int no_telepon, String alamat, String email, String token) {
        this.id = id;
        this.nama = nama;
        this.no_telepon = no_telepon;
        this.alamat = alamat;
        this.email = email;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getNo_telepon() {
        return no_telepon;
    }

    public void setNo_telepon(int no_telepon) {
        this.no_telepon = no_telepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
