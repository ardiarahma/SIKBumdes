package com.ardiarahma.sik_bumdesa.networks.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.models.LabaRugi_Biaya;

import java.util.ArrayList;

public class LabaRugi_BiayaAdapter extends RecyclerView.Adapter<LabaRugi_BiayaAdapter.ViewHolder> {

    Context context;
    private ArrayList<LabaRugi_Biaya> labaRugiBiayas;

    public LabaRugi_BiayaAdapter(Context context, ArrayList<LabaRugi_Biaya> labaRugiBiayas) {
        this.context = context;
        this.labaRugiBiayas = labaRugiBiayas;
    }

    @Override
    public LabaRugi_BiayaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_laba_rugi, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LabaRugi_BiayaAdapter.ViewHolder holder, int position) {
        LabaRugi_Biaya labaRugiBiaya = labaRugiBiayas.get(position);
        holder.textNamaAkun.setText(labaRugiBiaya.getNama());
        holder.textNilaiAkun.setText(String.valueOf(labaRugiBiaya.getNilai_akun()));
    }

    @Override
    public int getItemCount() {
        return labaRugiBiayas.size();
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
