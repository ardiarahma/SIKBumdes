package com.ardiarahma.sik_bumdesa.database.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.database.models.Neraca_AsetLancar;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 8/19/2019.
 */

public class Neraca_AsetLancarAdapter extends RecyclerView.Adapter<Neraca_AsetLancarAdapter.ViewHolder> {

    Context context;
    private ArrayList<Neraca_AsetLancar> neracaAsetLancars = new ArrayList<>();

    public Neraca_AsetLancarAdapter(Context context, ArrayList<Neraca_AsetLancar> neracaAsetLancars) {
        this.context = context;
        this.neracaAsetLancars = neracaAsetLancars;
    }

    @Override
    public Neraca_AsetLancarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_neraca_awal, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Neraca_AsetLancarAdapter.ViewHolder holder, int position) {
        holder.akun.setText(neracaAsetLancars.get(position).getAkun());
        holder.jumlah.setText(String.valueOf(neracaAsetLancars.get(position).getJumlah()));
    }

    @Override
    public int getItemCount() {
        return neracaAsetLancars.size();
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
