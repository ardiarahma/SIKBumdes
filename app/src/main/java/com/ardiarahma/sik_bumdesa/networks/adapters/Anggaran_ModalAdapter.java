package com.ardiarahma.sik_bumdesa.networks.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.models.Anggaran_Bumdes;
import com.ardiarahma.sik_bumdesa.networks.models.Anggaran_Modal;

import java.util.ArrayList;

public class Anggaran_ModalAdapter extends RecyclerView.Adapter<Anggaran_ModalAdapter.ViewHolder> {

    Context context;
    private ArrayList<Anggaran_Modal> anggaranModals;

    public Anggaran_ModalAdapter(Context context, ArrayList<Anggaran_Modal> anggaranModals) {
        this.context = context;
        this.anggaranModals = anggaranModals;
    }

    @Override
    public Anggaran_ModalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_rencana_anggaran, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Anggaran_ModalAdapter.ViewHolder holder, int position) {
        Anggaran_Modal anggaranModal = this.anggaranModals.get(position);
        holder.textNamaAnggaran.setText(anggaranModal.getNama());
        holder.textJumlahAnggaran.setText(String.valueOf(anggaranModal.getJumlah()));
    }

    @Override
    public int getItemCount() {
        return anggaranModals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textNamaAnggaran, textJumlahAnggaran;

        public ViewHolder(View itemView) {
            super(itemView);

            textNamaAnggaran = itemView.findViewById(R.id.nama_angg);
            textJumlahAnggaran= itemView.findViewById(R.id.jumlah_angg);
        }
    }
}
