package com.ardiarahma.sik_bumdesa.networks.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Windows 10 on 10/10/2019.
 */

public class AkunExp {


    private String name;

    public AkunExp (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
