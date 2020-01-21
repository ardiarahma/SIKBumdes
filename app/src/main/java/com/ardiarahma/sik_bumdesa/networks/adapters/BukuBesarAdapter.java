package com.ardiarahma.sik_bumdesa.networks.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.models.BukuBesar;
import com.ardiarahma.sik_bumdesa.networks.models.Jurnal_All;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Windows 10 on 1/16/2020.
 */

public class BukuBesarAdapter extends RecyclerView.Adapter<BukuBesarAdapter.CustomViewHolder> {

    List<BukuBesar> bukuBesars;

    public BukuBesarAdapter(ArrayList<BukuBesar> allBukuBesar) {
        this.bukuBesars = allBukuBesar;
    }

    @Override
    public BukuBesarAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bukubesar, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BukuBesarAdapter.CustomViewHolder holder, int position) {
        BukuBesar bukuBesar = bukuBesars.get(position);
        holder.item_date.setText(bukuBesar.getTanggal());
        holder.item_keterangan.setText(bukuBesar.getKeterangan());
        holder.item_debit.setText(String.valueOf(bukuBesar.getDebit()));
        holder.item_kredit.setText(String.valueOf(bukuBesar.getKredit()));
        holder.item_saldo.setText(String.valueOf(bukuBesar.getSaldo()));
    }

    @Override
    public int getItemCount() {
        return bukuBesars.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView item_date, item_keterangan, item_debit, item_kredit, item_saldo;

        public CustomViewHolder(View itemView) {
            super(itemView);

            item_date = itemView.findViewById(R.id.item_date);
            item_keterangan = itemView.findViewById(R.id.item_keterangan);
            item_debit = itemView.findViewById(R.id.item_debit);
            item_kredit = itemView.findViewById(R.id.item_kredit);
            item_saldo = itemView.findViewById(R.id.item_saldo);

        }
    }

    public void refreshEvents(List<BukuBesar> bukuBesars) {
        this.bukuBesars.clear();
        this.bukuBesars.addAll(bukuBesars);
        notifyDataSetChanged();
    }
}
