package com.ardiarahma.sik_bumdesa.networks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 on 10/8/2019.
 */

public class Akun_UpdateKlasfikasiAkun {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nama")
    @Expose
    private String name;

    @SerializedName("id_parent_akun")
    @Expose
    private String id_parent_akun;

    public Akun_UpdateKlasfikasiAkun(int id, String name, String id_parent_akun) {
        this.id = id;
        this.name = name;
        this.id_parent_akun = id_parent_akun;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_parent_akun() {
        return id_parent_akun;
    }

    public void setId_parent_akun(String id_parent_akun) {
        this.id_parent_akun = id_parent_akun;
    }
}
