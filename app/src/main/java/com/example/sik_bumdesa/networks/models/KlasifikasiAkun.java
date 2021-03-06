package com.example.sik_bumdesa.networks.models;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by Windows 10 on 10/8/2019.
 */

public class KlasifikasiAkun extends ExpandableGroup {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nama")
    @Expose
    private String name;

    @SerializedName("id_parent_akun")
    @Expose
    private int id_parent_akun;

    public KlasifikasiAkun(String title, List items) {
        super(title, items);
    }

    public KlasifikasiAkun(String title, List items, int id, String name, int id_parent_akun) {
        super(title, items);
        this.id = id;
        this.name = name;
        this.id_parent_akun = id_parent_akun;
    }

    public KlasifikasiAkun(Parcel in, int id, String name, int id_parent_akun) {
        super(in);
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

    public int getId_parent_akun() {
        return id_parent_akun;
    }

    public void setId_parent_akun(int id_parent_akun) {
        this.id_parent_akun = id_parent_akun;
    }
}
