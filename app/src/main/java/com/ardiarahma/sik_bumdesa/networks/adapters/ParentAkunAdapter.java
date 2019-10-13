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
import com.ardiarahma.sik_bumdesa.networks.models.Ekuitas_Modal;
import com.ardiarahma.sik_bumdesa.networks.models.ParentAkun;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 8/19/2019.
 */

public class ParentAkunAdapter extends RecyclerView.Adapter<ParentAkunAdapter.ViewHolder> {

    Context context;
    private ArrayList<ParentAkun> parentAkuns;
            //= new ArrayList<>();

    public ParentAkunAdapter(Context context, ArrayList<ParentAkun> parentAkuns) {
        this.context = context;
        this.parentAkuns = parentAkuns;
    }

    @Override
    public ParentAkunAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_parent_akun, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ParentAkunAdapter.ViewHolder holder, int position) {
        holder.akun.setText(parentAkuns.get(position).getParent_akun());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AccountClassificationActivity.class);
                context.startActivity(intent);
            }
        });
//        holder.id.setText(String.valueOf(parentAkuns.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return parentAkuns.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView akun, id;

        public ViewHolder(View itemView) {
            super(itemView);

            akun = itemView.findViewById(R.id.akun);
            cardView = itemView.findViewById(R.id.card);
//            id = itemView.findViewById(R.id.id);
        }
    }
}