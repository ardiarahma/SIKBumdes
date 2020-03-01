package com.example.sik_bumdesa.networks.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sik_bumdesa.R;
import com.example.sik_bumdesa.networks.models.NeracaUmum_LiabilitasLancar;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 8/19/2019.
 */

public class NeracaUmum_LiabilitasLancarAdapter extends RecyclerView.Adapter<NeracaUmum_LiabilitasLancarAdapter.ViewHolder> {

    Context context;
    private ArrayList<NeracaUmum_LiabilitasLancar> neracaUmum_liabilitasLancars;

    public NeracaUmum_LiabilitasLancarAdapter(Context context, ArrayList<NeracaUmum_LiabilitasLancar> neracaUmum_liabilitasLancars) {
        this.context = context;
        this.neracaUmum_liabilitasLancars = neracaUmum_liabilitasLancars;
    }

    @Override
    public NeracaUmum_LiabilitasLancarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_laba_rugi, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NeracaUmum_LiabilitasLancarAdapter.ViewHolder holder, int position) {
        NeracaUmum_LiabilitasLancar neracaUmum_liabilitasLancar = neracaUmum_liabilitasLancars.get(position);
        holder.akun.setText(neracaUmum_liabilitasLancar.getNama());
        holder.jumlah.setText(String.valueOf(neracaUmum_liabilitasLancar.getNilai_akun()));
    }

    @Override
    public int getItemCount() {
        return neracaUmum_liabilitasLancars.size();
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
