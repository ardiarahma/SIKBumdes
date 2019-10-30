package com.ardiarahma.sik_bumdesa.networks.adapters;

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

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.activities.navigation_drawer.NeracaAwalActivity;
import com.ardiarahma.sik_bumdesa.networks.RetrofitClient;
import com.ardiarahma.sik_bumdesa.networks.SharedPref;
import com.ardiarahma.sik_bumdesa.networks.models.NeracaAwal_Akun;
import com.ardiarahma.sik_bumdesa.networks.models.NeracaAwal_Klasifikasi;
import com.ardiarahma.sik_bumdesa.networks.models.NeracaAwal_Parent;
import com.ardiarahma.sik_bumdesa.networks.models.User;
import com.ardiarahma.sik_bumdesa.networks.models.responses.NeracaAwalAkunResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NeracaAwal_KlasifikasiAdapter extends RecyclerView.Adapter<NeracaAwal_KlasifikasiAdapter.CustomViewHolder> {

    List<NeracaAwal_Klasifikasi> neracaAwalKlasifikasis;
    Context mContext;

    public NeracaAwal_KlasifikasiAdapter(List<NeracaAwal_Klasifikasi> neracaAwalKlasifikasis, Context mContext) {
        this.neracaAwalKlasifikasis = neracaAwalKlasifikasis;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_neraca_awal_klasifikasi, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        NeracaAwal_Klasifikasi neracaAwalKlasifikasi = neracaAwalKlasifikasis.get(position);
        holder.textKlasifikasi.setText(neracaAwalKlasifikasi.getKlasifikasiName());
        holder.id = neracaAwalKlasifikasis.get(position).getKlasifikasiId();
    }

    @Override
    public int getItemCount() {
        return neracaAwalKlasifikasis.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textKlasifikasi, textEmpty;
        private int id;
        private ImageView btnDropDown;
        private ArrayList<NeracaAwal_Akun> neracaAwalAkunArrayList;
        private RecyclerView rv_akun;
        private NeracaAwal_AkunAdapter akunAdapter;
        private View viewKlasifikasi;
        User user = SharedPref.getInstance(mContext).getBaseUser();
        String token = "Bearer " + user.getToken();

        public CustomViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textKlasifikasi = itemView.findViewById(R.id.textKlasifikasi);
            textEmpty = itemView.findViewById(R.id.textEmpty);
            btnDropDown = itemView.findViewById(R.id.btnDropDown);
            rv_akun = itemView.findViewById(R.id.rv_akun);
            btnDropDown.setOnClickListener(this);
            viewKlasifikasi = itemView.findViewById(R.id.viewKlasifikasi);
            btnDropDown.setOnClickListener(this);
            viewKlasifikasi.setOnClickListener(this);
            textKlasifikasi.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int id = getLayoutPosition();
            int aid = getAdapterPosition();

            //Log.d("positions", id + " " + aid);

            NeracaAwal_Klasifikasi neracaAwal_klasifikasi = neracaAwalKlasifikasis.get(id);

            if (neracaAwal_klasifikasi.isChildrenVisible()) {
                neracaAwal_klasifikasi.setChildrenVisible(false);
                btnDropDown.setImageResource(R.drawable.ic_drop_down);
                textEmpty.setVisibility(View.GONE);
                rv_akun.setVisibility(View.GONE);
            } else {
                neracaAwal_klasifikasi.setChildrenVisible(true);
                btnDropDown.setImageResource(R.drawable.ic_drop_up);
                rv_akun.setVisibility(View.VISIBLE);
                loadAkun();
            }
        }

        public void loadAkun() {
            int tahun_param = Integer.parseInt(NeracaAwalActivity.tv_years.getText().toString());

            Call<NeracaAwalAkunResponse> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .neracaAkun(token, id, tahun_param);

            call.enqueue(new Callback<NeracaAwalAkunResponse>() {
                @Override
                public void onResponse(Call<NeracaAwalAkunResponse> call, Response<NeracaAwalAkunResponse> response) {
                    NeracaAwalAkunResponse neracaAwalAkunResponse = response.body();
                    if (response.isSuccessful()) {
                        if (neracaAwalAkunResponse.getStatus().equals("success")) {
                            neracaAwalAkunArrayList = neracaAwalAkunResponse.getNeracaAwalAkuns();
                            akunAdapter = new NeracaAwal_AkunAdapter(neracaAwalAkunArrayList);
                            RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(itemView.getContext());
                            rv_akun.setLayoutManager(eLayoutManager);
                            rv_akun.setItemAnimator(new DefaultItemAnimator());
                            rv_akun.setAdapter(akunAdapter);
                            if (eLayoutManager.getItemCount() == 0) {
                                rv_akun.setVisibility(View.GONE);
                                textEmpty.setVisibility(View.VISIBLE);
                            } else {
                                textEmpty.setVisibility(View.GONE);
                                rv_akun.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<NeracaAwalAkunResponse> call, Throwable t) {
                    Toast.makeText(mContext, "Kesalahan terjadi, coba beberapa saat lagi.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void refreshEvents(List<NeracaAwal_Klasifikasi> neracaAwalKlasifikasis) {
        this.neracaAwalKlasifikasis.clear();
        this.neracaAwalKlasifikasis.addAll(neracaAwalKlasifikasis);
        notifyDataSetChanged();
    }
}
