package com.ardiarahma.sik_bumdesa.networks.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.models.Ekuitas_Modal;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 8/19/2019.
 */

public class Ekuitas_ModalAdapter extends RecyclerView.Adapter<Ekuitas_ModalAdapter.ViewHolder> {

    Context context;
    private ArrayList<Ekuitas_Modal> ekuitas_modals= new ArrayList<>();

    public Ekuitas_ModalAdapter(Context context, ArrayList<Ekuitas_Modal> ekuitas_modals) {
        this.context = context;
        this.ekuitas_modals = ekuitas_modals;
    }

    @Override
    public Ekuitas_ModalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_neraca_awal, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Ekuitas_ModalAdapter.ViewHolder holder, int position) {
        holder.akun.setText(ekuitas_modals.get(position).getAkun());
        holder.jumlah.setText(String.valueOf(ekuitas_modals.get(position).getJumlah()));
    }

    @Override
    public int getItemCount() {
        return ekuitas_modals.size();
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
