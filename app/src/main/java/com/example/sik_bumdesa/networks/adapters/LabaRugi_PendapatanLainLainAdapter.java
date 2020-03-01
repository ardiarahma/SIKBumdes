package com.example.sik_bumdesa.networks.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sik_bumdesa.R;
import com.example.sik_bumdesa.networks.models.LabaRugi_PendapatanLainLain;

import java.util.ArrayList;

public class LabaRugi_PendapatanLainLainAdapter extends RecyclerView.Adapter<LabaRugi_PendapatanLainLainAdapter.ViewHolder> {

    Context context;
    private ArrayList<LabaRugi_PendapatanLainLain> pendapatanLainLains;

    public LabaRugi_PendapatanLainLainAdapter(Context context, ArrayList<LabaRugi_PendapatanLainLain> pendapatanLainLains) {
        this.context = context;
        this.pendapatanLainLains = pendapatanLainLains;
    }

    @Override
    public LabaRugi_PendapatanLainLainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_laba_rugi, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LabaRugi_PendapatanLainLainAdapter.ViewHolder holder, int position) {
        LabaRugi_PendapatanLainLain labaRugiLainLain = pendapatanLainLains.get(position);
        holder.textNamaAkun.setText(labaRugiLainLain.getNama());
        holder.textNilaiAkun.setText(String.valueOf(labaRugiLainLain.getNilai_akun()));
    }

    @Override
    public int getItemCount() {
        return pendapatanLainLains.size();
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
