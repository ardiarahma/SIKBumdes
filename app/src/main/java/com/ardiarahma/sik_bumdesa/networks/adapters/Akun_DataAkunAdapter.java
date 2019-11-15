package com.ardiarahma.sik_bumdesa.networks.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.ardiarahma.sik_bumdesa.networks.models.Akun_DataAkun;
import com.ardiarahma.sik_bumdesa.networks.models.Akun_KlasfikasiAkun;
import com.ardiarahma.sik_bumdesa.networks.models.Akun_UpdateAkun;
import com.ardiarahma.sik_bumdesa.networks.models.ParentAkun;
import com.ardiarahma.sik_bumdesa.networks.models.User;
import com.ardiarahma.sik_bumdesa.networks.models.responses.DataAkunUpdateResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.DeleteKlasifikasiResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.KlasifikasiAkunResponse;
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
        View view = inflater.inflate(R.layout.item_klasifikasi_akun, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Akun_DataAkunAdapter.ViewHolder holder, int position) {
        holder.akun.setText(akun_dataAkuns.get(position).getName());
//        holder.parent_id = parentAkuns.get(position).getId();
        holder.akun_id = akun_dataAkuns.get(position).getId();
        holder.akun_name = akun_dataAkuns.get(position).getName();

        holder.id.setText(String.valueOf(akun_dataAkuns.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return akun_dataAkuns.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView, edit, delete;
        TextView akun, id;
        int akun_id;
        String akun_name;
        Dialog dialog;
        SweetAlertDialog vDialog;
        Spinner parent_akun, klasifikasi_akun, posisi_normal;
        TextView id_parent, id_klasifikasi, tv_posisi;
        EditText kode_akun, nama_akun;
        private ArrayList<Akun_KlasfikasiAkun> klasifikasiAkuns;
        private ArrayList<ParentAkun> parentAkuns;
        ArrayAdapter<ParentAkun> arrayAdapter;
        ArrayAdapter<Akun_KlasfikasiAkun> akunArrayAdapter;

        User user = SharedPref.getInstance(context).getBaseUser();
        String token = "Bearer " + user.getToken();
        String accept = "application/json";

        public final String[] posisi = new String[]{
                "Debit", "Kredit"
        };


        public ViewHolder(View itemView) {
            super(itemView);
            akun = itemView.findViewById(R.id.akun);
            cardView = itemView.findViewById(R.id.card);
            id = itemView.findViewById(R.id.tvId);
            edit = itemView.findViewById(R.id.b_change);
            delete = itemView.findViewById(R.id.b_delete);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.add_child_account);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    kode_akun = dialog.findViewById(R.id.kode_akun);
                    kode_akun.setVisibility(View.GONE);
                    nama_akun = dialog.findViewById(R.id.nama_akun);
                    klasifikasi_akun = dialog.findViewById(R.id.childAcc_spinner);
                    parent_akun = dialog.findViewById(R.id.parentAcc_spinner);
                    posisi_normal = dialog.findViewById(R.id.posisi_spinner);
                    id_parent = dialog.findViewById(R.id.id_parent);
                    id_klasifikasi = dialog.findViewById(R.id.id_klasifikasi);
                    tv_posisi = dialog.findViewById(R.id.tv_posisi);

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
                            int id_parent_akun = parentAkun.getId();
                            id_parent.setText(String.valueOf(id_parent_akun));
                            //ambil data klasifikasi
                            Call<KlasifikasiAkunResponse> call2 = RetrofitClient
                                    .getInstance()
                                    .getApi()
                                    .klasifikasi_akun(token, accept, id_parent_akun);

                            call2.enqueue(new Callback<KlasifikasiAkunResponse>() {
                                @Override
                                public void onResponse(Call<KlasifikasiAkunResponse> call, Response<KlasifikasiAkunResponse> response) {
                                    KlasifikasiAkunResponse klasifikasiAkunResponse = response.body();
                                    Log.d("TAG", "Response " + response.body());
                                    if (response.isSuccessful()){
                                        if (klasifikasiAkunResponse.getStatus().equals("success")){
                                            klasifikasiAkuns = klasifikasiAkunResponse.getAkun_klasfikasiAkuns();
                                            akunArrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, klasifikasiAkuns);
                                            akunArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                            klasifikasi_akun.setAdapter(akunArrayAdapter);
                                            klasifikasi_akun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                    Akun_KlasfikasiAkun akun_klasfikasiAkun = (Akun_KlasfikasiAkun) parent.getSelectedItem();
                                                    int id_klasifikasi_akun = akun_klasfikasiAkun.getId();
                                                    id_klasifikasi.setText(String.valueOf(id_klasifikasi_akun));
                                                }

                                                @Override
                                                public void onNothingSelected(AdapterView<?> parent) {

                                                }
                                            });
                                        }
                                    }else {
                                        Toast.makeText(context, "Gagal mengambil data parent akun", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<KlasifikasiAkunResponse> call, Throwable t) {
                                    Toast.makeText(context, "Kesalahan terjadi, coba beberapa saat lagi.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, posisi);
//                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.posisi_normal, R.layout.support_simple_spinner_dropdown_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    posisi_normal.setAdapter(adapter);
                    posisi_normal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedItem = String.valueOf(parent.getItemIdAtPosition(position)).toString();
                            tv_posisi.setText(selectedItem);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                    nama_akun.setText(akun_name);

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
                    editAkun();
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
            vDialog.setTitleText("Yakin akan menghapus akun?");
            vDialog.setConfirmText("Ya");
            vDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    deleteAkun();
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

        public void editAkun(){
            int id_klasifikasi_akun = Integer.parseInt(id_klasifikasi.getText().toString());
            String name = nama_akun.getText().toString();
            String posisi = tv_posisi.getText().toString();

            Call<DataAkunUpdateResponse> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .update_akun(token, akun_id, id_klasifikasi_akun, name, posisi);

            call.enqueue(new Callback<DataAkunUpdateResponse>() {
                @Override
                public void onResponse(Call<DataAkunUpdateResponse> call, Response<DataAkunUpdateResponse> response) {
                    DataAkunUpdateResponse dataAkunUpdateResponse= response.body();
                    Log.d("TAG", "Response " + response.body());
                    if (response.isSuccessful()) {
                        if (dataAkunUpdateResponse.getStatus().equals("successsssss")) {
                            SweetAlertDialog sweet_dialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                            sweet_dialog.setTitleText("Akun akun berhasil diubah");
                            sweet_dialog.show();
                            dialog.dismiss();
                            vDialog.dismiss();
                        }
                        refreshEvents(akun_dataAkuns);
                        if (context instanceof AccountClassificationActivity) {
                            ((AccountClassificationActivity)context).loadAllKlasifikasi();
                        }
                    }
                }

                @Override
                public void onFailure(Call<DataAkunUpdateResponse> call, Throwable t) {
                    dialog.dismiss();
                    vDialog.dismiss();
                    Toast.makeText(context, "Kesalahan terjadi, coba beberapa saat lagi.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void deleteAkun(){
            Call<DeleteKlasifikasiResponse> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .delete_akun(token, akun_id);

            call.enqueue(new Callback<DeleteKlasifikasiResponse>() {
                @Override
                public void onResponse(Call<DeleteKlasifikasiResponse> call, Response<DeleteKlasifikasiResponse> response) {
                    DeleteKlasifikasiResponse deleteKlasifikasiResponse = response.body();
                    Log.d("TAG", "Response " + response.body());
                    if (response.isSuccessful()) {
                        if (deleteKlasifikasiResponse.getStatus().equals("Data Deleted successfully.")) {
                            Log.i("debug", "Response success");
                            //delete(getAdapterPosition());
                            SweetAlertDialog sweet_dialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                            sweet_dialog.setTitleText("Akun akun berhasil dihapus");
                            sweet_dialog.show();
                            vDialog.dismiss();
                        }
                        refreshEvents(akun_dataAkuns);
                        if (context instanceof AccountDataActivity) {
                            ((AccountDataActivity)context).loadAllAkun();
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

    public void refreshEvents(List<Akun_DataAkun> akun_dataAkuns) {
        this.akun_dataAkuns.clear();
        this.akun_dataAkuns.addAll(akun_dataAkuns);
        notifyDataSetChanged();
    }
}
