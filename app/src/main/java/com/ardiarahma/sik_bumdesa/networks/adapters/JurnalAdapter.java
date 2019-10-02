package com.ardiarahma.sik_bumdesa.networks.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.models.Jurnal;

import java.util.List;

/**
 * Created by Windows 10 on 9/4/2019.
 */

public class JurnalAdapter extends RecyclerView.Adapter<JurnalAdapter.ViewHolder> {

    Context context;
    List<Jurnal> jurnals;

    public JurnalAdapter(Context context, List<Jurnal> jurnals) {
        this.context = context;
        this.jurnals = jurnals;
    }

    @Override
    public JurnalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.jurnal_details_item, parent, false);
        ViewHolder vHolder = new ViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(JurnalAdapter.ViewHolder holder, int position) {
        holder.keterang.setText(jurnals.get(position).getKeterangan());
        holder.akun.setText(jurnals.get(position).getNo_akun());
        holder.kwitansi.setText(jurnals.get(position).getNo_kwitansi());
        holder.posisi.setText(jurnals.get(position).getPosisi_normal());
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView keterang, akun, kwitansi, posisi;

        public ViewHolder(View itemView) {
            super(itemView);
            keterang = itemView.findViewById(R.id.tv_keterangan);
            akun = itemView.findViewById(R.id.tv_nAkun);
            kwitansi = itemView.findViewById(R.id.tv_kwitansi);
            posisi = itemView.findViewById(R.id.tv_posisi);
        }
    }
}
