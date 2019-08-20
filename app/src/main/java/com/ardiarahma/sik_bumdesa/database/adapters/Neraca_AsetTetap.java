package com.ardiarahma.sik_bumdesa.database.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.database.models.Aset;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 8/19/2019.
 */

public class Neraca_AsetTetap extends RecyclerView.Adapter<Neraca_AsetTetap.ViewHolder> {

    Context context;
    private ArrayList<Aset> asets = new ArrayList<>();

    public Neraca_AsetTetap(Context context, ArrayList<Aset> asets) {
        this.context = context;
        this.asets = asets;
    }

    @Override
    public Neraca_AsetTetap.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_neraca_awal, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Neraca_AsetTetap.ViewHolder holder, int position) {
        holder.akun.setText(asets.get(position).getAkun());
        holder.jumlah.setText(asets.get(position).getJumlah());
    }

    @Override
    public int getItemCount() {
        return 0;
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
