package com.ardiarahma.sik_bumdesa.networks.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.models.Jurnal_All;
import com.ardiarahma.sik_bumdesa.networks.models.NeracaAwal_Akun;

import java.util.List;

public class Jurnal_Adapter extends RecyclerView.Adapter<Jurnal_Adapter.CustomViewHolder> {

    List<Jurnal_All> jurnalAlls;

    public Jurnal_Adapter(List<Jurnal_All> jurnalAlls) {
        this.jurnalAlls = jurnalAlls;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_jurnal, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Jurnal_All jurnal_all = jurnalAlls.get(position);
        holder.tv_keterangan.setText(jurnal_all.getKeterangan());
        holder.tv_kwitansi.setText(jurnal_all.getNo_kwitansi());
        holder.tv_posisi.setText(jurnal_all.getPosisi_normal());
        holder.tv_nAkun.setText(String.valueOf(jurnal_all.getId()));
    }

    @Override
    public int getItemCount() {
        return jurnalAlls.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tv_keterangan, tv_nAkun, tv_kwitansi, tv_posisi;

        public CustomViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_keterangan = itemView.findViewById(R.id.tv_keterangan);
            tv_nAkun = itemView.findViewById(R.id.tv_nAkun);
            tv_kwitansi = itemView.findViewById(R.id.tv_kwitansi);
            tv_posisi = itemView.findViewById(R.id.tv_posisi);
        }

        @Override
        public void onClick(View view) {
            //
        }
    }

    public void refreshEvents(List<Jurnal_All> jurnalAlls) {
        this.jurnalAlls.clear();
        this.jurnalAlls.addAll(jurnalAlls);
        notifyDataSetChanged();
    }
}
