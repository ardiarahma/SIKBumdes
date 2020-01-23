package com.ardiarahma.sik_bumdesa.networks.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.models.Anggaran_Belanja;
import com.ardiarahma.sik_bumdesa.networks.models.Anggaran_Bumdes;

import java.util.ArrayList;

public class Anggaran_BelanjaAdapter extends RecyclerView.Adapter<Anggaran_BelanjaAdapter.ViewHolder> {

    Context context;
    private ArrayList<Anggaran_Belanja> anggaranBelanjas;

    public Anggaran_BelanjaAdapter(Context context, ArrayList<Anggaran_Belanja> anggaranBelanjas) {
        this.context = context;
        this.anggaranBelanjas = anggaranBelanjas;
    }

    @Override
    public Anggaran_BelanjaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_rencana_anggaran, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Anggaran_BelanjaAdapter.ViewHolder holder, int position) {
        Anggaran_Belanja anggaranBelanja = this.anggaranBelanjas.get(position);
        holder.textNamaAnggaran.setText(anggaranBelanja.getNama());
        holder.textJumlahAnggaran.setText(String.valueOf(anggaranBelanja.getJumlah()));
    }

    @Override
    public int getItemCount() {
        return anggaranBelanjas.size();
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
