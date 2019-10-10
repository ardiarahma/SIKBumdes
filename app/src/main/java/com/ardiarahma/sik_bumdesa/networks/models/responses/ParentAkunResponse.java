package com.ardiarahma.sik_bumdesa.networks.models.responses;

import com.ardiarahma.sik_bumdesa.networks.models.ParentAkun;
import com.ardiarahma.sik_bumdesa.networks.models.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 10/1/2019.
 */

public class ParentAkunResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("parent")
    @Expose
    private ArrayList<ParentAkun> parentAkuns = null;;

    public ParentAkunResponse(String status, ArrayList<ParentAkun> parentAkuns) {
        this.status = status;
        this.parentAkuns = parentAkuns;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<ParentAkun> getParentAkuns() {
        return parentAkuns;
    }

    public void setParentAkuns(ArrayList<ParentAkun> parentAkuns) {
        this.parentAkuns = parentAkuns;
    }
}
