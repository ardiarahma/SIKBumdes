package com.ardiarahma.sik_bumdesa.networks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 11/12/2019.
 */

public class Results_1 {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("no_kwitansi")
    @Expose
    private String kwitansi;

    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    @SerializedName("jurnal")
    @Expose
    private ArrayList<Jurnal> jurnals = null;


}
