package com.ardiarahma.sik_bumdesa.networks.adapters;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.models.KlasifikasiAkun;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

/**
 * Created by Windows 10 on 10/10/2019.
 */

public class KlasifikasiViewHolder extends GroupViewHolder {

    private TextView mTv;
    private CardView cardView;

    public KlasifikasiViewHolder(View itemView) {
        super(itemView);
        mTv = itemView.findViewById(R.id.klasifikasi_akun);
        cardView = itemView.findViewById(R.id.card);
    }

    public void setKlasifikasi_akun(String klasifikasi_akun) {
        mTv.setText(klasifikasi_akun);
    }
}
