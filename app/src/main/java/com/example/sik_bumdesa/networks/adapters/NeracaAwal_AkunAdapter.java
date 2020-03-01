package com.example.sik_bumdesa.networks.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sik_bumdesa.R;
import com.example.sik_bumdesa.networks.models.NeracaAwal_Akun;

import java.util.List;

public class NeracaAwal_AkunAdapter extends RecyclerView.Adapter<NeracaAwal_AkunAdapter.CustomViewHolder> {

    List<NeracaAwal_Akun> neracaAwalAkuns;

    public NeracaAwal_AkunAdapter(List<NeracaAwal_Akun> neracaAwalAkuns) {
        this.neracaAwalAkuns = neracaAwalAkuns;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_neraca_awal_akun, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        NeracaAwal_Akun neracaAwalAkun = neracaAwalAkuns.get(position);
        holder.textAkun.setText(neracaAwalAkun.getAkunName());
        holder.textJumlah.setText(neracaAwalAkun.getAkunTotal());
        holder.id = neracaAwalAkuns.get(position).getParentId();
    }

    @Override
    public int getItemCount() {
        return neracaAwalAkuns.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textAkun, textJumlah;
        private int id;
        private ImageView btnDropDown;

        public CustomViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textAkun = itemView.findViewById(R.id.textAkun);
            textJumlah = itemView.findViewById(R.id.textJumlah);
            btnDropDown = itemView.findViewById(R.id.btnDropDown);
        }

        @Override
        public void onClick(View view) {
            //
        }
    }

    public void refreshEvents(List<NeracaAwal_Akun> neracaAwalAkuns) {
        this.neracaAwalAkuns.clear();
        this.neracaAwalAkuns.addAll(neracaAwalAkuns);
        notifyDataSetChanged();
    }
}
