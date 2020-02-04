package com.ardiarahma.sik_bumdesa.networks.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.models.Jurnal_All;
import com.ardiarahma.sik_bumdesa.networks.models.NeracaAwal_Akun;

import java.util.List;

public class Jurnal_Adapter extends RecyclerView.Adapter<Jurnal_Adapter.CustomViewHolder> {

    List<Jurnal_All> jurnalAlls;

    public Jurnal_Adapter(List<Jurnal_All> jurnalAlls) {
        this.jurnalAlls = jurnalAlls;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.jurnal_details_item, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        final Jurnal_All jurnal_all = jurnalAlls.get(position);
        holder.tv_keterangan.setText(jurnal_all.getKeterangan());
        holder.det_akun.setText(jurnal_all.getNama());
        holder.det_jumlah.setText(String.valueOf(jurnal_all.getJumlah()));
        holder.tv_kwitansi.setText(jurnal_all.getNo_kwitansi());
        holder.tv_posisi.setText(jurnal_all.getPosisi_normal());
        holder.tv_nAkun.setText(String.valueOf(jurnal_all.getId()));
        holder.id_jurnal = jurnal_all.getId_jurnal();

//        holder.card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Dialog dialog = new Dialog(context);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setCancelable(false);
//                dialog.setContentView(R.layout.jurnal_details_item);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                TextView det_ket = dialog.findViewById(R.id.detail_keterangan);
//                TextView det_akun = dialog.findViewById(R.id.detail_akun);
//                TextView det_jumlah = dialog.findViewById(R.id.detail_jumlah);
//                TextView det_nAkun = dialog.findViewById(R.id.detail_nAkun);
//                TextView det_kwitansi = dialog.findViewById(R.id.detail_kwitansi);
//                TextView det_status = dialog.findViewById(R.id.detail_posisi);
//
//                det_ket.setText(jurnal_all.getKeterangan());
//                det_akun.setText(jurnal_all.getNama());
//                det_jumlah.setText(String.valueOf(jurnal_all.getJumlah()));
//                det_nAkun.setText(String.valueOf(jurnal_all.getId()));
//                det_kwitansi.setText(jurnal_all.getNo_kwitansi());
//                det_status.setText(jurnal_all.getPosisi_normal());
//
//                dialog.show();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return jurnalAlls.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        int id_jurnal;
        private TextView tv_keterangan, tv_nAkun, tv_kwitansi, tv_posisi;
        private CardView card;
        Dialog dialog;

        private TextView det_ket, det_akun, det_jumlah, det_nAkun, det_kwitansi, det_status;

        public CustomViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            card = itemView.findViewById(R.id.item_list);
            tv_keterangan = itemView.findViewById(R.id.detail_keterangan);
            det_akun = itemView.findViewById(R.id.detail_akun);
            det_jumlah = itemView.findViewById(R.id.detail_jumlah);
            tv_nAkun = itemView.findViewById(R.id.detail_nAkun);
            tv_kwitansi = itemView.findViewById(R.id.detail_kwitansi);
            tv_posisi = itemView.findViewById(R.id.detail_posisi);

//            card.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog = new Dialog(context);
//                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                    dialog.setCancelable(false);
//                    dialog.setContentView(R.layout.jurnal_details_item);
//                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                    det_ket = dialog.findViewById(R.id.detail_keterangan);
//                    det_akun = dialog.findViewById(R.id.detail_akun);
//                    det_jumlah = dialog.findViewById(R.id.detail_jumlah);
//                    det_nAkun = dialog.findViewById(R.id.detail_nAkun);
//                    det_kwitansi = dialog.findViewById(R.id.detail_kwitansi);
//                    det_status = dialog.findViewById(R.id.detail_posisi);
//
//
//                }
//            });
        }

        @Override
        public void onClick(View view) {
            //
        }
    }

    public void refreshEvents(List<Jurnal_All> jurnalAlls) {
        this.jurnalAlls.clear();
        this.jurnalAlls.addAll(jurnalAlls);
        notifyDataSetChanged();
    }
}
