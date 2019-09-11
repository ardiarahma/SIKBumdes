package com.ardiarahma.sik_bumdesa.database.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.database.models.LabaRugi_Biaya;
import com.ardiarahma.sik_bumdesa.database.models.LabaRugi_Pendapatan;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 8/19/2019.
 */

public class LabaRugi_BiayaAdapter extends RecyclerView.Adapter<LabaRugi_BiayaAdapter.ViewHolder> {

    Context context;
    private ArrayList<LabaRugi_Biaya> labaRugi_biayas = new ArrayList<>();

    public LabaRugi_BiayaAdapter(Context context, ArrayList<LabaRugi_Biaya> labaRugi_pendapatans) {
        this.context = context;
        this.labaRugi_biayas = labaRugi_biayas;
    }

    @Override
    public LabaRugi_BiayaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_neraca_awal, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LabaRugi_BiayaAdapter.ViewHolder holder, int position) {
        holder.akun.setText(labaRugi_biayas.get(position).getAkun());
        holder.jumlah.setText(String.valueOf(labaRugi_biayas.get(position).getJumlah()));
    }

    @Override
    public int getItemCount() {
        return labaRugi_biayas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView akun, jumlah;

        public ViewHolder(View itemView) {
            super(itemView);

            akun = itemView.findViewById(R.id.akun);
            jumlah = itemView.findViewById(R.id.jumlah);
        }
    }
}
