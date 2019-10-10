package com.ardiarahma.sik_bumdesa.networks.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Windows 10 on 10/10/2019.
 */

public class AkunExp implements Parcelable {

    private final String name;

    public AkunExp (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    protected AkunExp(Parcel in) {
        name = in.readString();
    }

    public static final Creator<AkunExp> CREATOR = new Creator<AkunExp>() {
        @Override
        public AkunExp createFromParcel(Parcel in) {
            return new AkunExp(in);
        }

        @Override
        public AkunExp[] newArray(int size) {
            return new AkunExp[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
