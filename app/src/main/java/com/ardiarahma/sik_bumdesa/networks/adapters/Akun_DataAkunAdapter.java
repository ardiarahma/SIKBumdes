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
import com.ardiarahma.sik_bumdesa.activities.navigation_drawer.AccountDataActivity;
import com.ardiarahma.sik_bumdesa.networks.models.Akun_DataAkun;
import com.ardiarahma.sik_bumdesa.networks.models.Akun_KlasfikasiAkun;

import java.util.ArrayList;

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
        View view = inflater.inflate(R.layout.item_parent_akun, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Akun_DataAkunAdapter.ViewHolder holder, int position) {
        holder.akun.setText(akun_dataAkuns.get(position).getName());
//        holder.parent_id = parentAkuns.get(position).getId();
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AccountDataActivity.class);
//                intent.putExtra("parent_id", holder.parent_id);
                context.startActivity(intent);
            }
        });

        holder.id.setText(String.valueOf(akun_dataAkuns.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return akun_dataAkuns.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView akun, id;

        public ViewHolder(View itemView) {
            super(itemView);

            akun = itemView.findViewById(R.id.akun);
            cardView = itemView.findViewById(R.id.card);
            id = itemView.findViewById(R.id.tvId);
        }
    }
}
