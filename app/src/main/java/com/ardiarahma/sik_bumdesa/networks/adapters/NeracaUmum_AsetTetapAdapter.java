package com.ardiarahma.sik_bumdesa.networks.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.models.NeracaUmum_AsetLancar;
import com.ardiarahma.sik_bumdesa.networks.models.NeracaUmum_AsetTetap;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 8/19/2019.
 */

public class NeracaUmum_AsetTetapAdapter extends RecyclerView.Adapter<NeracaUmum_AsetTetapAdapter.ViewHolder> {

    Context context;
    private ArrayList<NeracaUmum_AsetTetap> neracaUmum_asetTetaps = new ArrayList<>();

    public NeracaUmum_AsetTetapAdapter(Context context, ArrayList<NeracaUmum_AsetTetap> neracaUmum_asetTetaps) {
        this.context = context;
        this.neracaUmum_asetTetaps = neracaUmum_asetTetaps;
    }

    @Override
    public NeracaUmum_AsetTetapAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_laba_rugi, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NeracaUmum_AsetTetapAdapter.ViewHolder holder, int position) {
        NeracaUmum_AsetTetap neracaUmum_asetTetap = neracaUmum_asetTetaps.get(position);
        holder.akun.setText(neracaUmum_asetTetap.getNama());
        holder.jumlah.setText(String.valueOf(neracaUmum_asetTetap.getNilai_akun()));
    }

    @Override
    public int getItemCount() {
        return neracaUmum_asetTetaps.size();
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
