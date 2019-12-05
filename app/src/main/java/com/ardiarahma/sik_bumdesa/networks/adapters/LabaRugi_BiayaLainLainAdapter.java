package com.ardiarahma.sik_bumdesa.networks.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.models.LabaRugi_Biaya;
import com.ardiarahma.sik_bumdesa.networks.models.LabaRugi_BiayaLainLain;
import com.ardiarahma.sik_bumdesa.networks.models.LabaRugi_PendapatanLainLain;

import java.util.ArrayList;

public class LabaRugi_BiayaLainLainAdapter extends RecyclerView.Adapter<LabaRugi_BiayaLainLainAdapter.ViewHolder> {

    Context context;
    private ArrayList<LabaRugi_BiayaLainLain> biayaLainLains;

    public LabaRugi_BiayaLainLainAdapter(Context context, ArrayList<LabaRugi_BiayaLainLain> biayaLainLains) {
        this.context = context;
        this.biayaLainLains = biayaLainLains;
    }

    @Override
    public LabaRugi_BiayaLainLainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_laba_rugi, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LabaRugi_BiayaLainLainAdapter.ViewHolder holder, int position) {
        LabaRugi_BiayaLainLain labaRugiLainLain = biayaLainLains.get(position);
        holder.textNamaAkun.setText(labaRugiLainLain.getNama());
        holder.textNilaiAkun.setText(String.valueOf(labaRugiLainLain.getNilai_akun()));
    }

    @Override
    public int getItemCount() {
        return biayaLainLains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textNamaAkun, textNilaiAkun;

        public ViewHolder(View itemView) {
            super(itemView);

            textNamaAkun = itemView.findViewById(R.id.textNamaAkun);
            textNilaiAkun = itemView.findViewById(R.id.textNilaiAkun);
        }
    }
}
