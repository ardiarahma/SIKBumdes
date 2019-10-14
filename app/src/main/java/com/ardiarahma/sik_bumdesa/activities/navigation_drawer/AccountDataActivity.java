package com.ardiarahma.sik_bumdesa.activities.navigation_drawer;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.RetrofitClient;
import com.ardiarahma.sik_bumdesa.networks.SharedPref;
import com.ardiarahma.sik_bumdesa.networks.adapters.Akun_DataAkunAdapter;
import com.ardiarahma.sik_bumdesa.networks.adapters.Akun_KlasifikasiAdapter;
import com.ardiarahma.sik_bumdesa.networks.models.Akun_DataAkun;
import com.ardiarahma.sik_bumdesa.networks.models.User;
import com.ardiarahma.sik_bumdesa.networks.models.responses.DataAkunResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.KlasifikasiAkunResponse;

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

    ImageButton toolbar_back;

    com.getbase.floatingactionbutton.FloatingActionButton fab1, fab2;
    Dialog dialog;
    SweetAlertDialog vDialog;
    SweetAlertDialog pDialog;

    User user = SharedPref.getInstance(this).getBaseUser();
    String token = "Bearer " + user.getToken();
    String accept = "application/json";

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

        pDialog = new SweetAlertDialog(AccountDataActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#e7a248"));
        pDialog.setTitleText("Tunggu sesaat..");
        pDialog.setCancelable(false);
        pDialog.show();

        list = findViewById(R.id.list);
        adapter = new Akun_DataAkunAdapter(this, akun_dataAkuns);

        Intent intent = getIntent();
        int id_klasifikasi_akun = intent.getIntExtra("classification_id", 0);

        Call<DataAkunResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .data_akun(token, accept, id_klasifikasi_akun);

        call.enqueue(new Callback<DataAkunResponse>() {
            @Override
            public void onResponse(Call<DataAkunResponse> call, Response<DataAkunResponse> response) {
                pDialog.dismissWithAnimation();
                DataAkunResponse dataAkunResponse = response.body();
                Log.d("TAG", "message " + response.body());
                if (response.isSuccessful()){
                    if (dataAkunResponse.getStatus().equals("success")){
                        Log.i("debug", "onResponse : Get Data Akun is Successful");
                        pDialog.dismissWithAnimation();
                        akun_dataAkuns = dataAkunResponse.getAkun_dataAkuns();
                        adapter = new Akun_DataAkunAdapter(AccountDataActivity.this, akun_dataAkuns);
                        layoutManager = new LinearLayoutManager(AccountDataActivity.this, LinearLayoutManager.VERTICAL, false);
                        list.setAdapter(adapter);
                        list.setHasFixedSize(true);
                        list.setLayoutManager(layoutManager);
                        adapter.notifyDataSetChanged();
                    }else {
                        Log.i("debug", "onResponse : Get Data Akun is Failed");
                        pDialog.dismissWithAnimation();
                        Toast.makeText(getApplicationContext(), response.toString()+" ",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DataAkunResponse> call, Throwable t) {
                Log.e("debug", "onFailure : ERROR > " + t.getMessage());
                pDialog.dismissWithAnimation();
                Toast.makeText(AccountDataActivity.this,t.toString(), Toast.LENGTH_LONG).show();
            }
        });


        fab1 = findViewById(R.id.fab_1);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(AccountDataActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.add_child_account);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

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
        final SweetAlertDialog vDialog = new SweetAlertDialog(AccountDataActivity.this, SweetAlertDialog.WARNING_TYPE);
        vDialog.setTitleText("Apakah data sudah benar?");
        vDialog.setConfirmText("Ya, benar");
        vDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                //post data
                SweetAlertDialog sweet_dialog = new SweetAlertDialog(AccountDataActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                sweet_dialog.setTitleText("Akun berhasil ditambahkan");
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


}
