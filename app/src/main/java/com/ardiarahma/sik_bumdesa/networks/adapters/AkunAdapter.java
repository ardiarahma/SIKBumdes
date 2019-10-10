package com.ardiarahma.sik_bumdesa.networks.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.models.AkunExp;
import com.ardiarahma.sik_bumdesa.networks.models.KlasifikasiAkun;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by Windows 10 on 10/10/2019.
 */

public class AkunAdapter extends ExpandableRecyclerViewAdapter<KlasifikasiViewHolder, AkunViewHolder> {

    public AkunAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public KlasifikasiViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_klasifikasi_akun, parent, false);
        return new KlasifikasiViewHolder(v);
    }

    @Override
    public AkunViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_akun, parent, false);
        return new AkunViewHolder(v);
    }

    @Override
    public void onBindChildViewHolder(AkunViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final AkunExp akunExp = (AkunExp) group.getItems().get(childIndex);
        holder.bindViewHolder(akunExp);
    }

    @Override
    public void onBindGroupViewHolder(KlasifikasiViewHolder holder, int flatPosition, ExpandableGroup group) {
        final KlasifikasiAkun klasifikasiAkun = (KlasifikasiAkun) group;
        holder.bindViewHolder(klasifikasiAkun);
    }
}
