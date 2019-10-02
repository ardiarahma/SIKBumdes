package com.ardiarahma.sik_bumdesa.networks.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.models.Neraca_AsetTetap;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 8/19/2019.
 */

public class Neraca_AsetTetapAdapter extends RecyclerView.Adapter<Neraca_AsetTetapAdapter.ViewHolder> {

    Context context;
    private ArrayList<Neraca_AsetTetap> neracaAsetTetaps= new ArrayList<>();

    public Neraca_AsetTetapAdapter(Context context, ArrayList<Neraca_AsetTetap> neracaAsetTetaps) {
        this.context = context;
        this.neracaAsetTetaps = neracaAsetTetaps;
    }

    @Override
    public Neraca_AsetTetapAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_neraca_awal, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Neraca_AsetTetapAdapter.ViewHolder holder, int position) {
        holder.akun.setText(neracaAsetTetaps.get(position).getAkun());
        holder.jumlah.setText(String.valueOf(neracaAsetTetaps.get(position).getJumlah()));
    }

    @Override
    public int getItemCount() {
        return neracaAsetTetaps.size();
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
