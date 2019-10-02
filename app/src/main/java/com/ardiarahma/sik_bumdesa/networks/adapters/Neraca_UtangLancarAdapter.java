package com.ardiarahma.sik_bumdesa.database.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.database.models.Neraca_AsetTetap;
import com.ardiarahma.sik_bumdesa.database.models.Neraca_UtangLancar;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 8/19/2019.
 */

public class Neraca_UtangLancarAdapter extends RecyclerView.Adapter<Neraca_UtangLancarAdapter.ViewHolder> {

    Context context;
    private ArrayList<Neraca_UtangLancar> neraca_utangLancars= new ArrayList<>();

    public Neraca_UtangLancarAdapter(Context context, ArrayList<Neraca_UtangLancar> neraca_utangLancars) {
        this.context = context;
        this.neraca_utangLancars= neraca_utangLancars;
    }

    @Override
    public Neraca_UtangLancarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_neraca_awal, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Neraca_UtangLancarAdapter.ViewHolder holder, int position) {
        holder.akun.setText(neraca_utangLancars.get(position).getAkun());
        holder.jumlah.setText(String.valueOf(neraca_utangLancars.get(position).getJumlah()));
    }

    @Override
    public int getItemCount() {
        return neraca_utangLancars.size();
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
