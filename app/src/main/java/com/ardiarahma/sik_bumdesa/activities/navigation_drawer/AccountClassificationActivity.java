package com.ardiarahma.sik_bumdesa.activities.navigation_drawer;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.RetrofitClient;
import com.ardiarahma.sik_bumdesa.networks.SharedPref;
import com.ardiarahma.sik_bumdesa.networks.adapters.AkunAdapter;
import com.ardiarahma.sik_bumdesa.networks.adapters.Akun_KlasifikasiAdapter;
import com.ardiarahma.sik_bumdesa.networks.adapters.Akun_ParentAdapter;
import com.ardiarahma.sik_bumdesa.networks.adapters.KlasifikasiAkunAdapter;
import com.ardiarahma.sik_bumdesa.networks.models.AkunExp;
import com.ardiarahma.sik_bumdesa.networks.models.Akun_KlasfikasiAkun;
import com.ardiarahma.sik_bumdesa.networks.models.KlasifikasiAkun;
import com.ardiarahma.sik_bumdesa.networks.models.ParentAkun;
import com.ardiarahma.sik_bumdesa.networks.models.User;
import com.ardiarahma.sik_bumdesa.networks.models.responses.DataAkunResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.KlasifikasiAkunResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.ParentAkunResponse;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountClassificationActivity extends AppCompatActivity {

    RecyclerView list;
//    KlasifikasiAkunAdapter adapter;
    Akun_KlasifikasiAdapter adapter;
    Akun_ParentAdapter adapter1;
//    private List<KlasifikasiAkun> klasifikasiAkuns;
//    private List<AkunExp> akunExps;
    private ArrayList<Akun_KlasfikasiAkun> klasifikasiAkuns;
    private ArrayList<ParentAkun> parentAkuns;
    ArrayAdapter<ParentAkun> arrayAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ImageButton toolbar_back;

    FloatingActionButton fab1, fab2;
    Dialog dialog;
    SweetAlertDialog vDialog;
    SweetAlertDialog pDialog;

    Context context;

    User user = SharedPref.getInstance(this).getBaseUser();
    String token = "Bearer " + user.getToken();
    String accept = "application/json";

    Spinner parent_akun;
    TextView id_parent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_classification);

        toolbar_back = findViewById(R.id.toolbar_back);
        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        list = findViewById(R.id.list);
        adapter = new Akun_KlasifikasiAdapter(this, klasifikasiAkuns);

        Intent intent = getIntent();
        int id_parent_akun = intent.getIntExtra("parent_id", 0);

        Call<KlasifikasiAkunResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .klasifikasi_akun(token, accept, id_parent_akun);

        call.enqueue(new Callback<KlasifikasiAkunResponse>() {
            @Override
            public void onResponse(Call<KlasifikasiAkunResponse> call, Response<KlasifikasiAkunResponse> response) {
                pDialog.dismissWithAnimation();
                KlasifikasiAkunResponse klasifikasiAkunResponse = response.body();
                Log.d("TAG", "Response " + response.body());
                if (response.isSuccessful()){
                    if (klasifikasiAkunResponse.getStatus().equals("success")){
                        Log.i("debug", "onResponse : Get Klasifikasi Akun is Successful");
                        pDialog.dismissWithAnimation();
                        klasifikasiAkuns = klasifikasiAkunResponse.getAkun_klasfikasiAkuns();
                        adapter = new Akun_KlasifikasiAdapter(AccountClassificationActivity.this, klasifikasiAkuns);
                        layoutManager = new LinearLayoutManager(
                                AccountClassificationActivity.this, LinearLayoutManager.VERTICAL,false);
                        list.setLayoutManager(layoutManager);
                        list.setHasFixedSize(true);
                        list.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }else {
                        Log.i("debug", "onResponse : Get Parent Akun was Failed");
                        pDialog.dismissWithAnimation();
                    /*    Toast.makeText(getApplicationContext(), "Gagal dalam mengambil data parent akun",
                                Toast.LENGTH_LONG).show();
                    */  Toast.makeText(getApplicationContext(), response.toString()+" ",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<KlasifikasiAkunResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                pDialog.dismissWithAnimation();
                //     Toast.makeText(AccountActivity.this, "Kesalahan terjadi. Silakan coba beberapa saat lagi.", Toast.LENGTH_LONG).show();
                Toast.makeText(AccountClassificationActivity.this,t.toString(), Toast.LENGTH_LONG).show();
            }
        });

        pDialog = new SweetAlertDialog(AccountClassificationActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#e7a248"));
        pDialog.setTitleText("Tunggu sesaat..");
        pDialog.setCancelable(false);
        pDialog.show();

        fab1 = findViewById(R.id.fab_1);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(AccountClassificationActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.add_parent_account);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                parent_akun = dialog.findViewById(R.id.parentAcc_spinner);
                id_parent = dialog.findViewById(R.id.id_parent);

                Call<ParentAkunResponse> call1 = RetrofitClient
                        .getInstance()
                        .getApi()
                        .parent_akun(token, accept);

                call1.enqueue(new Callback<ParentAkunResponse>() {
                    @Override
                    public void onResponse(Call<ParentAkunResponse> call, Response<ParentAkunResponse> response) {
                        if (response.isSuccessful()){
                            ParentAkunResponse parentAkunResponse = response.body();
                            if (parentAkunResponse.getStatus().equals("success")){
                                parentAkuns = parentAkunResponse.getParentAkuns();
                                Log.d("TAG", "Response" + parentAkunResponse.getParentAkuns());
                                arrayAdapter = new ArrayAdapter<ParentAkun>(context, android.R.layout.simple_spinner_item);
                                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                parent_akun.setAdapter(arrayAdapter);
                                parent_akun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        ParentAkun parentAkun = (ParentAkun) parent.getSelectedItem();
                                        int parentakun = parentAkun.getId();
                                        id_parent.setText(String.valueOf(parentakun));
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ParentAkunResponse> call, Throwable t) {
                        Toast.makeText(context, "Gagal mengambil data provinsi", Toast.LENGTH_LONG).show();
                        Log.d("TAG", "Response" + t.toString());
                    }
                });


                EditText nama_klasifikasi = dialog.findViewById(R.id.nama_klasifikasi);
                EditText kode_klasifikasi = dialog.findViewById(R.id.kode_klasifikasi);

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

    public void validationAccount(){
        final SweetAlertDialog vDialog = new SweetAlertDialog(AccountClassificationActivity.this, SweetAlertDialog.WARNING_TYPE);
        vDialog.setTitleText("Apakah data sudah benar?");
        vDialog.setConfirmText("Ya, benar");
        vDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {

                SweetAlertDialog sweet_dialog = new SweetAlertDialog(AccountClassificationActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                sweet_dialog.setTitleText("Klasifikasi akun berhasil ditambahkan");
                sweet_dialog.show();
                dialog.dismiss();
                vDialog.dismiss();
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

    public void getKlasifikasi() {
//        klasifikasiAkuns = new ArrayList<>(6);
//        for (int i = 0; i < 6; i++){
//            List<AkunExp> akunExps = new ArrayList<>(3);
//            akunExps.add(new AkunExp(1110, 11,"Kas", "Debit"));
//            akunExps.add(new AkunExp(1111, 11, "Kas di Bank", "Debit"));
//            akunExps.add(new AkunExp(1120, 11, "Piutang Dagang", "Debit"));
//            klasifikasiAkuns.add(new KlasifikasiAkun("Aset Lancar", akunExps));
//        }

    }
}
