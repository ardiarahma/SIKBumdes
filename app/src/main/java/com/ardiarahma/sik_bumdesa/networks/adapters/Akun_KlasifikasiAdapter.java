package com.ardiarahma.sik_bumdesa.networks.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.activities.navigation_drawer.AccountClassificationActivity;
import com.ardiarahma.sik_bumdesa.activities.navigation_drawer.AccountDataActivity;
import com.ardiarahma.sik_bumdesa.networks.RetrofitClient;
import com.ardiarahma.sik_bumdesa.networks.SharedPref;
import com.ardiarahma.sik_bumdesa.networks.models.Akun_KlasfikasiAkun;
import com.ardiarahma.sik_bumdesa.networks.models.ParentAkun;
import com.ardiarahma.sik_bumdesa.networks.models.User;
import com.ardiarahma.sik_bumdesa.networks.models.responses.DeleteKlasifikasiResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.DeleteResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.KlasifikasiAkunCreateResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.KlasifikasiAkunUpdateResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.ParentAkunResponse;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Windows 10 on 8/19/2019.
 */

public class Akun_KlasifikasiAdapter extends RecyclerView.Adapter<Akun_KlasifikasiAdapter.ViewHolder> {

    Context context;
    private ArrayList<Akun_KlasfikasiAkun> akun_klasfikasiAkuns = new ArrayList<>();
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Akun_KlasifikasiAdapter(Context context, ArrayList<Akun_KlasfikasiAkun> akun_klasfikasiAkuns) {
        this.context = context;
        this.akun_klasfikasiAkuns = akun_klasfikasiAkuns;
    }

    @Override
    public Akun_KlasifikasiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_klasifikasi_akun, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Akun_KlasifikasiAdapter.ViewHolder holder, int position) {
        Akun_KlasfikasiAkun akun_klasfikasiAkun = akun_klasfikasiAkuns.get(position);
        holder.akun.setText(akun_klasfikasiAkun.getName());
        holder.id.setText(String.valueOf(akun_klasfikasiAkun.getId()));
        holder.klasifikasi_nama = akun_klasfikasiAkuns.get(position).getName();
        holder.klasifikasi_id = akun_klasfikasiAkuns.get(position).getId();
        /*
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AccountDataActivity.class);
                intent.putExtra("classification_nama", holder.klasifikasi_nama);
                intent.putExtra("classification_id", holder.klasifikasi_id);
                context.startActivity(intent);
            }
        });
        */
    }

    @Override
    public int getItemCount() {
        return akun_klasfikasiAkuns.size();
    }

    public void delete(int position) {
        akun_klasfikasiAkuns.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView, edit, delete;
        TextView akun, id;
        int klasifikasi_id;
        String klasifikasi_nama;
        Dialog dialog;
        SweetAlertDialog vDialog;
        SweetAlertDialog pDialog;
        Spinner parent_akun;
        TextView id_parent, id_klasifikasi;
        EditText nama_klasifikasi, kode_klasifikasi;
        Akun_KlasifikasiAdapter adapter;
        private ArrayList<ParentAkun> parentAkuns;
        ArrayAdapter<ParentAkun> arrayAdapter;

        User user = SharedPref.getInstance(context).getBaseUser();
        String token = "Bearer " + user.getToken();
        String accept = "application/json";

        public ViewHolder(View itemView) {
            super(itemView);

            akun = itemView.findViewById(R.id.akun);
            cardView = itemView.findViewById(R.id.card);
//            itemView.setOnCreateContextMenuListener(this);
            id = itemView.findViewById(R.id.tvId);
            edit = itemView.findViewById(R.id.b_change);
            delete = itemView.findViewById(R.id.b_delete);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.add_parent_account);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    kode_klasifikasi = dialog.findViewById(R.id.kode_klasifikasi);
                    kode_klasifikasi.setVisibility(View.GONE);
                    parent_akun = dialog.findViewById(R.id.parentAcc_spinner);
                    id_parent = dialog.findViewById(R.id.id_parent);

                    Call<ParentAkunResponse> call1 = RetrofitClient
                            .getInstance()
                            .getApi()
                            .parent_akun(token, accept);

                    call1.enqueue(new Callback<ParentAkunResponse>() {
                        @Override
                        public void onResponse(Call<ParentAkunResponse> call, Response<ParentAkunResponse> response) {
                            ParentAkunResponse parentAkunResponse = response.body();
                            Log.d("TAG", "Response " + response.body());
                            if (response.isSuccessful()) {
                                if (parentAkunResponse.getStatus().equals("success")) {
                                    parentAkuns = parentAkunResponse.getParentAkuns();
                                    arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, parentAkuns);
                                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    parent_akun.setAdapter(arrayAdapter);
                                }
                            } else {
                                Toast.makeText(context, "Gagal mengambil data parent akun", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ParentAkunResponse> call, Throwable t) {
                            Toast.makeText(context, "Kesalahan terjadi, coba beberapa saat lagi.", Toast.LENGTH_SHORT).show();
                        }
                    });

                    parent_akun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            ParentAkun parentAkun = (ParentAkun) parent.getSelectedItem();
                            id_parent.setText(String.valueOf(parentAkun.getId()));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    nama_klasifikasi = dialog.findViewById(R.id.nama_klasifikasi);
                    nama_klasifikasi.setText(klasifikasi_nama);

                    Button dCancel = dialog.findViewById(R.id.cancel_button);
                    dCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    Button dSave = dialog.findViewById(R.id.save_button);
                    dSave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.hide();
                            validationUpdateAccount();
                        }
                    });
                    dialog.show();
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validationDelete();
                }
            });
        }


        public void validationUpdateAccount() {
            vDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
            vDialog.setTitleText("Apakah data sudah benar?");
            vDialog.setConfirmText("Ya, benar");
            vDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    editKlasifikasi();
                }
            });
            vDialog.setCancelButton("Belum", new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    vDialog.dismiss();
                    dialog.show();
                }
            }).show();
        }

        public void validationDelete() {
            vDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
            vDialog.setTitleText("Yakin akan menghapus klasifikasi akun?");
            vDialog.setConfirmText("Ya");
            vDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    deleteKlasifikasi();
                }
            });
            vDialog.setCancelButton("Tidak", new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    vDialog.dismiss();
//                    dialog.show();
                }
            }).show();
        }

        public void editKlasifikasi() {
            int id_parent_akun = Integer.parseInt(id_parent.getText().toString());
            String name = nama_klasifikasi.getText().toString();

            Call<KlasifikasiAkunUpdateResponse> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .update_klasifikasi_akun(token, klasifikasi_id, name, id_parent_akun);

            call.enqueue(new Callback<KlasifikasiAkunUpdateResponse>() {
                @Override
                public void onResponse(Call<KlasifikasiAkunUpdateResponse> call, Response<KlasifikasiAkunUpdateResponse> response) {
                    KlasifikasiAkunUpdateResponse klasifikasiUpdateResponse = response.body();
                    Log.d("TAG", "Response " + response.body());
                    if (response.isSuccessful()) {
                        if (klasifikasiUpdateResponse.getStatus().equals("success")) {
                            SweetAlertDialog sweet_dialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                            sweet_dialog.setTitleText("Klasifikasi akun berhasil diubah");
                            sweet_dialog.show();
                            dialog.dismiss();
                            vDialog.dismiss();
                        }
                        refreshEvents(akun_klasfikasiAkuns);
                        if (context instanceof AccountClassificationActivity) {
                            ((AccountClassificationActivity)context).loadAllKlasifikasi();
                        }
                    }
                }

                @Override
                public void onFailure(Call<KlasifikasiAkunUpdateResponse> call, Throwable t) {
                    dialog.dismiss();
                    vDialog.dismiss();
                    Toast.makeText(context, "Kesalahan terjadi, coba beberapa saat lagi.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void deleteKlasifikasi() {
            Call<DeleteKlasifikasiResponse> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .delete_klasifikasi(token, klasifikasi_id);

            call.enqueue(new Callback<DeleteKlasifikasiResponse>() {
                @Override
                public void onResponse(Call<DeleteKlasifikasiResponse> call, Response<DeleteKlasifikasiResponse> response) {
                    DeleteKlasifikasiResponse deleteKlasifikasiResponse = response.body();
                    Log.d("TAG", "Response " + response.body());
                    if (response.isSuccessful()) {
                        if (deleteKlasifikasiResponse.getSuccess().equals("Data Deleted successfully.")) {
                            Log.i("debug", "Response success");
                            //delete(getAdapterPosition());
                            SweetAlertDialog sweet_dialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                            sweet_dialog.setTitleText("Klasifikasi akun berhasil dihapus");
                            sweet_dialog.show();
                            vDialog.dismiss();
                        }
                        refreshEvents(akun_klasfikasiAkuns);
                        if (context instanceof AccountClassificationActivity) {
                            ((AccountClassificationActivity)context).loadAllKlasifikasi();
                        }
                    }
                }

                @Override
                public void onFailure(Call<DeleteKlasifikasiResponse> call, Throwable t) {
                    vDialog.dismiss();
                    Toast.makeText(context, "Kesalahan terjadi, coba beberapa saat lagi.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void refreshEvents(List<Akun_KlasfikasiAkun> akun_klasfikasiAkuns) {
        this.akun_klasfikasiAkuns.clear();
        this.akun_klasfikasiAkuns.addAll(akun_klasfikasiAkuns);
        notifyDataSetChanged();
    }
}
