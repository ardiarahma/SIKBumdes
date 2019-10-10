package com.ardiarahma.sik_bumdesa.networks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 10/7/2019.
 */

public class ParentAkun {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nama")
    @Expose
    private String parent_akun;

    public ParentAkun(int id, String parent_akun) {
        this.id = id;
        this.parent_akun = parent_akun;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParent_akun() {
        return parent_akun;
    }

    public void setParent_akun(String parent_akun) {
        this.parent_akun = parent_akun;
    }
}
