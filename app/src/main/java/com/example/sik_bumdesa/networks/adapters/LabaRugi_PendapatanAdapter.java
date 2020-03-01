package com.example.sik_bumdesa.networks.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sik_bumdesa.R;
import com.example.sik_bumdesa.networks.models.LabaRugi_Pendapatan;

import java.util.ArrayList;

public class LabaRugi_PendapatanAdapter extends RecyclerView.Adapter<LabaRugi_PendapatanAdapter.ViewHolder> {

    Context context;
    private ArrayList<LabaRugi_Pendapatan> labaRugiPendapatans;

    public LabaRugi_PendapatanAdapter(Context context, ArrayList<LabaRugi_Pendapatan> labaRugiPendapatans) {
        this.context = context;
        this.labaRugiPendapatans = labaRugiPendapatans;
    }

    @Override
    public LabaRugi_PendapatanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_laba_rugi, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LabaRugi_PendapatanAdapter.ViewHolder holder, int position) {
        LabaRugi_Pendapatan labaRugiPendapatan = labaRugiPendapatans.get(position);
        holder.textNamaAkun.setText(labaRugiPendapatan.getNama());
        holder.textNilaiAkun.setText(String.valueOf(labaRugiPendapatan.getNilai_akun()));
    }

    @Override
    public int getItemCount() {
        return labaRugiPendapatans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textNamaAkun, textNilaiAkun;

        public ViewHolder(View itemView) {
            super(itemView);

            textNamaAkun = itemView.findViewById(R.id.textNamaAkun);
            textNilaiAkun = itemView.findViewById(R.id.textNilaiAkun);
        }
    }
}
