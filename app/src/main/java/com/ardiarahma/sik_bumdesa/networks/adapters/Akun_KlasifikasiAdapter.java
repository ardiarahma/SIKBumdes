package com.ardiarahma.sik_bumdesa.networks.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.activities.navigation_drawer.AccountClassificationActivity;
import com.ardiarahma.sik_bumdesa.networks.models.Akun_KlasfikasiAkun;
import com.ardiarahma.sik_bumdesa.networks.models.ParentAkun;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 8/19/2019.
 */

public class Akun_KlasifikasiAdapter extends RecyclerView.Adapter<Akun_KlasifikasiAdapter.ViewHolder> {

    Context context;
    private ArrayList<Akun_KlasfikasiAkun> akun_klasfikasiAkuns = new ArrayList<>();

    public Akun_KlasifikasiAdapter(Context context, ArrayList<Akun_KlasfikasiAkun> akun_klasfikasiAkuns) {
        this.context = context;
        this.akun_klasfikasiAkuns = akun_klasfikasiAkuns;
    }

    @Override
    public Akun_KlasifikasiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_parent_akun, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Akun_KlasifikasiAdapter.ViewHolder holder, int position) {
        holder.akun.setText(akun_klasfikasiAkuns.get(position).getName());
        holder.klasifikasi_id = akun_klasfikasiAkuns.get(position).getId();
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AccountClassificationActivity.class);
                intent.putExtra("classification_id", holder.klasifikasi_id);
                context.startActivity(intent);
            }
        });

//        holder.id.setText(String.valueOf(parentAkuns.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return akun_klasfikasiAkuns.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView akun;
        int klasifikasi_id;

        public ViewHolder(View itemView) {
            super(itemView);

            akun = itemView.findViewById(R.id.akun);
            cardView = itemView.findViewById(R.id.card);
//            id = itemView.findViewById(R.id.id);
        }
    }
}
