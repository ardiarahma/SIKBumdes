package com.ardiarahma.sik_bumdesa.networks.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.models.AkunExp;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by Windows 10 on 10/13/2019.
 */

public class KlasifikasiAkunAdapter extends ExpandableRecyclerViewAdapter<KlasifikasiViewHolder, AkunViewHolder> {

    public KlasifikasiAkunAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public KlasifikasiViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_klasifikasi_akun, parent, false);
        return new KlasifikasiViewHolder(v);
    }

    @Override
    public AkunViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_akun, parent, false);
        return new AkunViewHolder(v);
    }

    @Override
    public void onBindChildViewHolder(AkunViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        AkunExp akunExp = (AkunExp) group.getItems().get(childIndex);

        holder.setAkunName(akunExp.getName());
    }

    @Override
    public void onBindGroupViewHolder(KlasifikasiViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setKlasifikasi_akun(group.getTitle());
    }
}
