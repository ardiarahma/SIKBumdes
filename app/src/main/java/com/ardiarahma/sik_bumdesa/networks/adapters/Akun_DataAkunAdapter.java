package com.ardiarahma.sik_bumdesa.networks.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.activities.navigation_drawer.AccountClassificationActivity;
import com.ardiarahma.sik_bumdesa.activities.navigation_drawer.AccountDataActivity;
import com.ardiarahma.sik_bumdesa.networks.models.Akun_DataAkun;
import com.ardiarahma.sik_bumdesa.networks.models.Akun_KlasfikasiAkun;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Windows 10 on 8/19/2019.
 */

public class Akun_DataAkunAdapter extends RecyclerView.Adapter<Akun_DataAkunAdapter.ViewHolder> {

    Context context;
    private ArrayList<Akun_DataAkun> akun_dataAkuns = new ArrayList<>();

    public Akun_DataAkunAdapter(Context context, ArrayList<Akun_DataAkun> akun_dataAkuns) {
        this.context = context;
        this.akun_dataAkuns = akun_dataAkuns;
    }

    @Override
    public Akun_DataAkunAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_klasifikasi_akun, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Akun_DataAkunAdapter.ViewHolder holder, int position) {
        holder.akun.setText(akun_dataAkuns.get(position).getName());
//        holder.parent_id = parentAkuns.get(position).getId();

        holder.id.setText(String.valueOf(akun_dataAkuns.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return akun_dataAkuns.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView, edit, delete;
        TextView akun, id;
        int klasifikasi_id;
        String klasifikasi_nama;
        Dialog dialog;
        SweetAlertDialog vDialog;
        Spinner parent_akun, klasifikasi_akun, posisi_normal;
        TextView id_parent, id_klasifikasi, tv_posisi;
        EditText kode_akun, nama_akun;

        public ViewHolder(View itemView) {
            super(itemView);
            akun = itemView.findViewById(R.id.akun);
            cardView = itemView.findViewById(R.id.card);
            id = itemView.findViewById(R.id.tvId);
            edit = itemView.findViewById(R.id.b_change);
            delete = itemView.findViewById(R.id.b_delete);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.add_child_account);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    kode_akun = dialog.findViewById(R.id.kode_akun);
                    nama_akun = dialog.findViewById(R.id.nama_akun);
                    klasifikasi_akun = dialog.findViewById(R.id.childAcc_spinner);
                    parent_akun = dialog.findViewById(R.id.parentAcc_spinner);
                    posisi_normal = dialog.findViewById(R.id.posisi_spinner);
                    id_parent = dialog.findViewById(R.id.id_parent);
                    id_klasifikasi = dialog.findViewById(R.id.id_klasifikasi);
                    tv_posisi = dialog.findViewById(R.id.tv_posisi);
                }
            });
        }
    }
}
