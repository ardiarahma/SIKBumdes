package com.ardiarahma.sik_bumdesa.networks.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.models.Anggaran_Bumdes;

import java.util.ArrayList;

public class Anggaran_BumdesAdapter extends RecyclerView.Adapter<Anggaran_BumdesAdapter.ViewHolder> {

    Context context;
    private ArrayList<Anggaran_Bumdes> anggaranBumdes;

    public Anggaran_BumdesAdapter(Context context, ArrayList<Anggaran_Bumdes> anggaranBumdes) {
        this.context = context;
        this.anggaranBumdes = anggaranBumdes;
    }

    @Override
    public Anggaran_BumdesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_rencana_anggaran, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Anggaran_BumdesAdapter.ViewHolder holder, int position) {
        Anggaran_Bumdes anggaranBumdes = this.anggaranBumdes.get(position);
        holder.textNamaAnggaran.setText(anggaranBumdes.getNama());
        holder.textJumlahAnggaran.setText(String.valueOf(anggaranBumdes.getJumlah()));
    }

    @Override
    public int getItemCount() {
        return anggaranBumdes.size();
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
