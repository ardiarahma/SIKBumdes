package com.ardiarahma.sik_bumdesa.networks.adapters;

import android.view.View;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.models.AkunExp;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by Windows 10 on 10/10/2019.
 */

public class AkunViewHolder extends ChildViewHolder {
     private TextView mTv;

    public AkunViewHolder(View itemView) {
        super(itemView);
        mTv = itemView.findViewById(R.id.mTv);
    }

    public void bindViewHolder(AkunExp akunExp){
        mTv.setText(akunExp.getName());
    }
}
