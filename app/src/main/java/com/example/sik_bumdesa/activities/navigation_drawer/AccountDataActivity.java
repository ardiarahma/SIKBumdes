package com.example.sik_bumdesa.activities.navigation_drawer;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sik_bumdesa.R;
import com.example.sik_bumdesa.networks.RetrofitClient;
import com.example.sik_bumdesa.networks.SharedPref;
import com.example.sik_bumdesa.networks.adapters.Akun_DataAkunAdapter;
import com.example.sik_bumdesa.networks.models.Akun_DataAkun;
import com.example.sik_bumdesa.networks.models.Akun_KlasfikasiAkun;
import com.example.sik_bumdesa.networks.models.ParentAkun;
import com.example.sik_bumdesa.networks.models.User;
import com.example.sik_bumdesa.networks.models.responses.DataAkunCreateResponse;
import com.example.sik_bumdesa.networks.models.responses.DataAkunResponse;
import com.example.sik_bumdesa.networks.models.responses.KlasifikasiAkunResponse;
import com.example.sik_bumdesa.networks.models.responses.ParentAkunResponse;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountDataActivity extends AppCompatActivity {

    RecyclerView list;
    Akun_DataAkunAdapter adapter;
    private ArrayList<Akun_DataAkun> akun_dataAkuns;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Akun_KlasfikasiAkun> klasifikasiAkuns;
    private ArrayList<ParentAkun> parentAkuns;
    ArrayAdapter<ParentAkun> arrayAdapter;
    ArrayAdapter<Akun_KlasfikasiAkun> akunArrayAdapter;

    ImageButton toolbar_back;

    com.getbase.floatingactionbutton.FloatingActionButton fab1;
    Dialog dialog;
    SweetAlertDialog vDialog;
    SweetAlertDialog pDialog;

    Context context;


    Spinner parent_akun, klasifikasi_akun, posisi_normal;
    TextView id_parent, id_klasifikasi, tv_posisi;
    EditText kode_akun, nama_akun;

    public static final String[] posisi = new String[]{
            "Debit", "Kredit"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar_back = findViewById(R.id.toolbar_back);
        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        list = findViewById(R.id.list);
        adapter = new Akun_DataAkunAdapter(this, akun_dataAkuns);

        fab1 = findViewById(R.id.fab_1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(AccountDataActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.add_child_account);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                kode_akun = dialog.findViewById(R.id.kode_akun);
                nama_akun = dialog.findViewById(R.id.nama_akun);
                klasifikasi_akun = dialog.findViewById(R.id.childAcc_spinner);
                parent_akun = dialog.findViewById(R.id.parentAcc_spinner);
                posisi_normal = dialog.findViewById(R.id.posisi_spinner);
                id_parent = dialog.findViewById(R.id.id_parent);
                id_klasifikasi = dialog.findViewById(R.id.id_klasifikasi);
                tv_posisi = dialog.findViewById(R.id.tv_posisi);

                User user = SharedPref.getInstance(context).getBaseUser();
                String token = "Bearer " + user.getToken();
                String accept = "application/json";

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
                                arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, parentAkuns);
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
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                        ((TextView) parent.getChildAt(0)).setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                        User user = SharedPref.getInstance(context).getBaseUser();
                        String token = "Bearer " + user.getToken();
                        String accept = "application/json";

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
                                if (response.isSuccessful()) {
                                    if (klasifikasiAkunResponse.getStatus().equals("success")) {
                                        klasifikasiAkuns = klasifikasiAkunResponse.getAkun_klasfikasiAkuns();
                                        akunArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, klasifikasiAkuns);
                                        akunArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                        klasifikasi_akun.setAdapter(akunArrayAdapter);
                                        klasifikasi_akun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                                                ((TextView) parent.getChildAt(0)).setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                                                Akun_KlasfikasiAkun akun_klasfikasiAkun = (Akun_KlasfikasiAkun) parent.getSelectedItem();
                                                int id_klasifikasi_akun = akun_klasfikasiAkun.getId();
                                                id_klasifikasi.setText(String.valueOf(id_klasifikasi_akun));
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                    }
                                } else {
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

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, posisi);
//                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.posisi_normal, R.layout.support_simple_spinner_dropdown_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                posisi_normal.setAdapter(adapter);
                posisi_normal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                        ((TextView) parent.getChildAt(0)).setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                        String selectedItem = String.valueOf(parent.getItemIdAtPosition(position)).toString();
                        tv_posisi.setText(selectedItem);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


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
                        validationAccount();
                    }
                });
                dialog.show();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAllAkun();
    }

    public void loadAllAkun() {
        pDialog = new SweetAlertDialog(AccountDataActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#e7a248"));
        pDialog.setTitleText("Tunggu sesaat..");
        pDialog.setCancelable(false);
        pDialog.show();

        User user = SharedPref.getInstance(this).getBaseUser();
        String token = "Bearer " + user.getToken();
        String accept = "application/json";

        Intent intent = getIntent();
        int id_klasifikasi_akun = intent.getIntExtra("classification_id", 0);

        final Call<DataAkunResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .data_akun(token, accept, id_klasifikasi_akun);

        call.enqueue(new Callback<DataAkunResponse>() {
            @Override
            public void onResponse(Call<DataAkunResponse> call, Response<DataAkunResponse> response) {
                pDialog.dismissWithAnimation();
                DataAkunResponse dataAkunResponse = response.body();
                Log.d("TAG", "message " + response.body());
                if (response.isSuccessful()) {
                    if (dataAkunResponse.getStatus().equals("success")) {
                        Log.i("debug", "onResponse : Get Data Akun is Successful");
                        pDialog.dismissWithAnimation();
                        akun_dataAkuns = dataAkunResponse.getAkun_dataAkuns();
                        adapter = new Akun_DataAkunAdapter(AccountDataActivity.this, akun_dataAkuns);
                        layoutManager = new LinearLayoutManager(AccountDataActivity.this, LinearLayoutManager.VERTICAL, false);
                        list.setAdapter(adapter);
                        list.setHasFixedSize(true);
                        list.setLayoutManager(layoutManager);
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.i("debug", "onResponse : Get Data Akun is Failed");
                        pDialog.dismissWithAnimation();
                        Toast.makeText(getApplicationContext(), response.toString() + " ",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DataAkunResponse> call, Throwable t) {
                Log.e("debug", "onFailure : ERROR > " + t.getMessage());
                pDialog.dismissWithAnimation();
                Toast.makeText(AccountDataActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void validationAccount() {
        vDialog = new SweetAlertDialog(AccountDataActivity.this, SweetAlertDialog.WARNING_TYPE);
        vDialog.setTitleText("Apakah data sudah benar?");
        String contentText = "<b>Parent : </b>" + parent_akun.getSelectedItem().toString() + "<br />" + "<b>Klasifikasi Akun : </b>" + klasifikasi_akun.getSelectedItem().toString() +
                "<br />" + "<b>Kode Akun : </b>" + kode_akun.getText().toString() + "<br />" + "<b>Nama Akun : </b>" + nama_akun.getText().toString() + "<br />" + "<b>Posisi Normal : </b>" + posisi_normal.getSelectedItem().toString();
        vDialog.setContentText(contentText);
        vDialog.setConfirmText("Ya, benar");
        vDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                createAkun();
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

    public void createAkun() {
        User user = SharedPref.getInstance(this).getBaseUser();
        String token = "Bearer " + user.getToken();
        String accept = "application/json";

        int id_klasifikasi_akun = Integer.parseInt(id_klasifikasi.getText().toString());
        String code = kode_akun.getText().toString();
        String name = nama_akun.getText().toString();
        String posisi = tv_posisi.getText().toString();

        Call<DataAkunCreateResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .create_akun(token, code, id_klasifikasi_akun, name, posisi);
        call.enqueue(new Callback<DataAkunCreateResponse>() {
            @Override
            public void onResponse(Call<DataAkunCreateResponse> call, Response<DataAkunCreateResponse> response) {
                DataAkunCreateResponse dataAkunCreateResponse = response.body();
                if (response.isSuccessful()) {
                    if (dataAkunCreateResponse.getStatus().equals("success")) {
                        Log.e("debug", "success");
                        dialog.dismiss();
                        vDialog.dismiss();
                        SweetAlertDialog sweet_dialog = new SweetAlertDialog(AccountDataActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                        sweet_dialog.setTitleText("Akun berhasil ditambahkan");
                        sweet_dialog.show();
                        if (adapter != null) {
                            adapter.refreshEvents(akun_dataAkuns);
                        }
                        loadAllAkun();
                    }
                } else {
                    Log.e("debug", "error");
                    vDialog.dismiss();
                    dialog.show();
                    kode_akun.setError("Kode Akun telah digunakan");
                    /*
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        jsonObject = jsonObject.getJSONObject("error");
                        Iterator<String> keys = jsonObject.keys();
                        StringBuilder errors = new StringBuilder();
                        String separator = "";
                        while (keys.hasNext()) {
                            String key = keys.next();
                            JSONArray arr = jsonObject.getJSONArray(key);
                            for (int i = 0; i < arr.length(); i++) {
                                errors.append(separator).append(key).append(" : ").append(arr.getString(i));
                                separator = "\n";
                            }
                        }
                        Toast.makeText(AccountDataActivity.this, errors.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(AccountDataActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                     */

                }
            }

            @Override
            public void onFailure(Call<DataAkunCreateResponse> call, Throwable t) {
                vDialog.dismiss();
                dialog.show();
                Log.e("debug", "failure");
                Log.e("debug", t.toString());
                Toast.makeText(context, "Kesalahan terjadi, coba beberapa saat lagi.", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
