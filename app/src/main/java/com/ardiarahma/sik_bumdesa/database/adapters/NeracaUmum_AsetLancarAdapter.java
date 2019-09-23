package com.ardiarahma.sik_bumdesa.database.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.database.models.LabaRugi_Pendapatan;
import com.ardiarahma.sik_bumdesa.database.models.NeracaUmum_AsetLancar;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 8/19/2019.
 */

public class NeracaUmum_AsetLancarAdapter extends RecyclerView.Adapter<NeracaUmum_AsetLancarAdapter.ViewHolder> {

    Context context;
    private ArrayList<NeracaUmum_AsetLancar> neracaUmum_asetLancars= new ArrayList<>();

    public NeracaUmum_AsetLancarAdapter(Context context, ArrayList<NeracaUmum_AsetLancar> neracaUmum_asetLancars) {
        this.context = context;
        this.neracaUmum_asetLancars = neracaUmum_asetLancars;
    }

    @Override
    public NeracaUmum_AsetLancarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_neraca_awal, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NeracaUmum_AsetLancarAdapter.ViewHolder holder, int position) {
        holder.akun.setText(neracaUmum_asetLancars.get(position).getAkun());
        holder.jumlah.setText(String.valueOf(neracaUmum_asetLancars.get(position).getJumlah()));
    }

    @Override
    public int getItemCount() {
        return neracaUmum_asetLancars.size();
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
