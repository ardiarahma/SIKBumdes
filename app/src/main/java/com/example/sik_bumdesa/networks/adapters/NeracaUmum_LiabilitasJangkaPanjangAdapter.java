package com.example.sik_bumdesa.networks.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sik_bumdesa.R;
import com.example.sik_bumdesa.networks.models.NeracaUmum_LiabilitasJangkaPanjang;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 8/19/2019.
 */

public class NeracaUmum_LiabilitasJangkaPanjangAdapter extends RecyclerView.Adapter<NeracaUmum_LiabilitasJangkaPanjangAdapter.ViewHolder> {

    Context context;
    private ArrayList<NeracaUmum_LiabilitasJangkaPanjang> neracaUmum_liabilitasJangkaPanjangs;

    public NeracaUmum_LiabilitasJangkaPanjangAdapter(Context context, ArrayList<NeracaUmum_LiabilitasJangkaPanjang> neracaUmum_liabilitasJangkaPanjangs) {
        this.context = context;
        this.neracaUmum_liabilitasJangkaPanjangs = neracaUmum_liabilitasJangkaPanjangs;
    }

    @Override
    public NeracaUmum_LiabilitasJangkaPanjangAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_laba_rugi, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NeracaUmum_LiabilitasJangkaPanjangAdapter.ViewHolder holder, int position) {
        NeracaUmum_LiabilitasJangkaPanjang neracaUmum_liabilitasJangkaPanjang = neracaUmum_liabilitasJangkaPanjangs.get(position);
        holder.akun.setText(neracaUmum_liabilitasJangkaPanjang.getNama());
        holder.jumlah.setText(String.valueOf(neracaUmum_liabilitasJangkaPanjang.getNilai_akun()));
    }

    @Override
    public int getItemCount() {
        return neracaUmum_liabilitasJangkaPanjangs.size();
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
