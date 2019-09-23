package com.ardiarahma.sik_bumdesa.database.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.database.models.Ekuitas_Saldo;
import com.ardiarahma.sik_bumdesa.database.models.LabaRugi_Pendapatan;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 8/19/2019.
 */

public class Ekuitas_SaldoAdapter extends RecyclerView.Adapter<Ekuitas_SaldoAdapter.ViewHolder> {

    Context context;
    private ArrayList<Ekuitas_Saldo> ekuitas_saldos = new ArrayList<>();

    public Ekuitas_SaldoAdapter(Context context, ArrayList<Ekuitas_Saldo> ekuitas_saldos) {
        this.context = context;
        this.ekuitas_saldos = ekuitas_saldos;
    }

    @Override
    public Ekuitas_SaldoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_neraca_awal, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Ekuitas_SaldoAdapter.ViewHolder holder, int position) {
        holder.akun.setText(ekuitas_saldos.get(position).getAkun());
        holder.jumlah.setText(String.valueOf(ekuitas_saldos.get(position).getJumlah()));
    }

    @Override
    public int getItemCount() {
        return ekuitas_saldos.size();
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
