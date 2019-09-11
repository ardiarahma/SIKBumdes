package com.ardiarahma.sik_bumdesa.database.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.database.models.LabaRugi_Pendapatan;
import com.ardiarahma.sik_bumdesa.database.models.Neraca_AsetLancar;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 8/19/2019.
 */

public class LabaRugi_PendapatanAdapter extends RecyclerView.Adapter<LabaRugi_PendapatanAdapter.ViewHolder> {

    Context context;
    private ArrayList<LabaRugi_Pendapatan> labaRugi_pendapatans= new ArrayList<>();

    public LabaRugi_PendapatanAdapter(Context context, ArrayList<LabaRugi_Pendapatan> labaRugi_pendapatans) {
        this.context = context;
        this.labaRugi_pendapatans = labaRugi_pendapatans;
    }

    @Override
    public LabaRugi_PendapatanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_neraca_awal, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LabaRugi_PendapatanAdapter.ViewHolder holder, int position) {
        holder.akun.setText(labaRugi_pendapatans.get(position).getAkun());
        holder.jumlah.setText(String.valueOf(labaRugi_pendapatans.get(position).getJumlah()));
    }

    @Override
    public int getItemCount() {
        return labaRugi_pendapatans.size();
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
