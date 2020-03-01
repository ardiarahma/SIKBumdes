package com.example.sik_bumdesa.networks.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sik_bumdesa.R;
import com.example.sik_bumdesa.networks.RetrofitClient;
import com.example.sik_bumdesa.networks.SharedPref;
import com.example.sik_bumdesa.networks.models.NeracaAwal_Klasifikasi;
import com.example.sik_bumdesa.networks.models.NeracaAwal_Parent;
import com.example.sik_bumdesa.networks.models.User;
import com.example.sik_bumdesa.networks.models.responses.NeracaAwalKlasifikasiResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NeracaAwal_ParentAdapter extends RecyclerView.Adapter<NeracaAwal_ParentAdapter.CustomViewHolder> {

    List<NeracaAwal_Parent> neracaAwalParents;
    Context mContext;

    public NeracaAwal_ParentAdapter(List<NeracaAwal_Parent> neracaAwalParents, Context mContext) {
        this.neracaAwalParents = neracaAwalParents;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_neraca_awal_parent, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        NeracaAwal_Parent neracaAwalParent = neracaAwalParents.get(position);
        holder.textParent.setText(neracaAwalParent.getParentName());
        holder.id = neracaAwalParents.get(position).getParentId();
    }

    @Override
    public int getItemCount() {
        return neracaAwalParents.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textParent, textEmpty;
        private int id;
        private ImageView btnDropDown;
        private ArrayList<NeracaAwal_Klasifikasi> neracaAwalKlasifikasiArrayList;
        private RecyclerView rv_klasifikasi;
        private NeracaAwal_KlasifikasiAdapter klasifikasiAdapter;
        User user = SharedPref.getInstance(mContext).getBaseUser();
        String token = "Bearer " + user.getToken();

        public CustomViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textParent = itemView.findViewById(R.id.textParent);
            textEmpty = itemView.findViewById(R.id.textEmpty);
            btnDropDown = itemView.findViewById(R.id.btnDropDown);
            rv_klasifikasi = itemView.findViewById(R.id.rv_klasifikasi);
            btnDropDown.setOnClickListener(this);
            textParent.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int id = getLayoutPosition();
            int aid = getAdapterPosition();

            //Log.d("positions", id + " " + aid);

            NeracaAwal_Parent neracaAwal_parent = neracaAwalParents.get(id);

            if (neracaAwal_parent.isChildrenVisible()) {
                neracaAwal_parent.setChildrenVisible(false);
                btnDropDown.setImageResource(R.drawable.ic_drop_down);
                textEmpty.setVisibility(View.GONE);
                rv_klasifikasi.setVisibility(View.GONE);
            } else {
                neracaAwal_parent.setChildrenVisible(true);
                btnDropDown.setImageResource(R.drawable.ic_drop_up);
                rv_klasifikasi.setVisibility(View.VISIBLE);
                loadKlasifikasi();
            }
        }

        public void loadKlasifikasi() {
            Call<NeracaAwalKlasifikasiResponse> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .neracaKlasifikasi(token, id);

            call.enqueue(new Callback<NeracaAwalKlasifikasiResponse>() {
                @Override
                public void onResponse(Call<NeracaAwalKlasifikasiResponse> call, Response<NeracaAwalKlasifikasiResponse> response) {
                    NeracaAwalKlasifikasiResponse neracaAwalKlasifikasiResponse = response.body();
                    if (response.isSuccessful()) {
                        if (neracaAwalKlasifikasiResponse.getStatus().equals("success")) {
                            neracaAwalKlasifikasiArrayList = neracaAwalKlasifikasiResponse.getNeracaAwalKlasifikasis();
                            klasifikasiAdapter = new NeracaAwal_KlasifikasiAdapter(neracaAwalKlasifikasiArrayList, mContext);
                            RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(itemView.getContext());
                            rv_klasifikasi.setLayoutManager(eLayoutManager);
                            rv_klasifikasi.setItemAnimator(new DefaultItemAnimator());
                            rv_klasifikasi.setAdapter(klasifikasiAdapter);
                            if (eLayoutManager.getItemCount() == 0) {
                                rv_klasifikasi.setVisibility(View.GONE);
                                textEmpty.setVisibility(View.VISIBLE);
                            } else {
                                textEmpty.setVisibility(View.GONE);
                                rv_klasifikasi.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<NeracaAwalKlasifikasiResponse> call, Throwable t) {
                    Toast.makeText(mContext, "Kesalahan terjadi, coba beberapa saat lagi.", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public void refreshEvents(List<NeracaAwal_Parent> neracaAwalParents) {
        this.neracaAwalParents.clear();
        this.neracaAwalParents.addAll(neracaAwalParents);
        notifyDataSetChanged();
    }
}
