package com.example.sik_bumdesa.networks.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sik_bumdesa.R;
import com.example.sik_bumdesa.networks.models.NeracaUmum_AsetLancar;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 8/19/2019.
 */

public class NeracaUmum_AsetLancarAdapter extends RecyclerView.Adapter<NeracaUmum_AsetLancarAdapter.ViewHolder> {

    Context context;
    private ArrayList<NeracaUmum_AsetLancar> neracaUmum_asetLancars;

    public NeracaUmum_AsetLancarAdapter(Context context, ArrayList<NeracaUmum_AsetLancar> neracaUmum_asetLancars) {
        this.context = context;
        this.neracaUmum_asetLancars = neracaUmum_asetLancars;
    }

    @Override
    public NeracaUmum_AsetLancarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_laba_rugi, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NeracaUmum_AsetLancarAdapter.ViewHolder holder, int position) {
        NeracaUmum_AsetLancar neracaUmum_asetLancar = neracaUmum_asetLancars.get(position);
        holder.akun.setText(neracaUmum_asetLancar.getNama());
        holder.jumlah.setText(String.valueOf(neracaUmum_asetLancar.getNilai_akun()));
    }

    @Override
    public int getItemCount() {
        return neracaUmum_asetLancars.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView akun, jumlah;

        public ViewHolder(View itemView) {
            super(itemView);

            akun = itemView.findViewById(R.id.textNamaAkun);
            jumlah = itemView.findViewById(R.id.textNilaiAkun);
        }
    }
}
