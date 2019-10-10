package com.ardiarahma.sik_bumdesa.activities;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.RetrofitClient;
import com.ardiarahma.sik_bumdesa.networks.models.responses.RegisterResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    TextInputLayout tilName, tilAddress, tilPhone, tilEmail, tilPassword;
    Button registration;
    Context mContext;
    ProgressDialog loading;

    SweetAlertDialog vDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        tilName = findViewById(R.id.companyLayout);
        tilAddress = findViewById(R.id.addressLayout);
        tilPhone = findViewById(R.id.phoneLayout);
        tilEmail = findViewById(R.id.emailLayout);
        tilPassword = findViewById(R.id.passwordLayout);
        registration = findViewById(R.id.registration);
        mContext = this;

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateCompName() | !validationAdrress() | !validatePhone() | !validateEmail() | !validatePassword()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("company_name", tilName.getEditText().getText().toString());
                    bundle.putString("company_address", tilAddress.getEditText().getText().toString());
                    bundle.putString("company_telp", tilPhone.getEditText().getText().toString());
                    bundle.putString("company_email", tilEmail.getEditText().getText().toString());
                    Fragment fragment = new Fragment();
                    fragment.setArguments(bundle);
                    return;
                }

                validationRegistration();


//                SweetAlertDialog loading = new SweetAlertDialog(RegistrationActivity.this, SweetAlertDialog.PROGRESS_TYPE);
//                loading.getProgressHelper().setBarColor(Color.parseColor("#e7a248"));
//                loading.setTitleText("Memuat");
//                loading.setCancelable(true);
//
//                SweetAlertDialog success = new SweetAlertDialog(RegistrationActivity.this, SweetAlertDialog.SUCCESS_TYPE);
//                success.setTitleText("Terdaftar!");
//                success.setContentText("Akun berhasil dibuat");

//                loading.show();
//                success.show();
            }
        });
    }

    public void validationRegistration() {
        final SweetAlertDialog vDialog = new SweetAlertDialog(RegistrationActivity.this, SweetAlertDialog.WARNING_TYPE);
        vDialog.setTitleText("Apakah data sudah benar?");
        vDialog.setConfirmText("Ya, benar");
        vDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                vDialog.dismissWithAnimation();
                loading = ProgressDialog.show(mContext, null, "Mohon tunggu...", true, false);
                registerUser();
            }
        });
        vDialog.setCancelButton("Belum", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                vDialog.dismissWithAnimation();
            }
        }).show();
    }

    private boolean validateCompName() {
        String company_name = tilName.getEditText().getText().toString().trim();

        if (company_name.isEmpty()) {
            tilName.setError("Nama perusahaan harus diisi");
            return false;
        } else {
            tilName.setError(null);
            return true;
        }
    }

    private boolean validationAdrress() {
        String address = tilAddress.getEditText().getText().toString().trim();

        if (address.isEmpty()) {
            tilAddress.setError("Alamat perusahaan harus diisi");
            return false;
        } else {
            tilAddress.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        String phone = tilPhone.getEditText().getText().toString().trim();

        if (phone.isEmpty()) {
            tilPhone.setError("Nomor telepon harus diisi");
            return false;
        } else {
            tilPhone.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String email = tilEmail.getEditText().getText().toString().trim();

        if (email.isEmpty()) {
            tilEmail.setError("Email harus diisi");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            loading.dismiss();
            tilEmail.setError("Masukkan email yang benar");
            tilEmail.requestFocus();
            return false;
        } else {
            tilEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String password = tilPassword.getEditText().getText().toString().trim();

        if (password.isEmpty()) {
            tilPassword.setError("Password harus diisi");
            return false;
        } else if (password.length() < 8) {
//            loading.dismiss();
            tilPassword.setError("Password harus berisi minimal 8 karakter");
            tilPassword.requestFocus();
            return false;
        } else {
            tilPassword.setError(null);
            return true;
        }
    }

    public void registerUser(){
        String name = tilName.getEditText().getText().toString();
        String address = tilAddress.getEditText().getText().toString();
        String phone = tilPhone.getEditText().getText().toString();
        String email = tilEmail.getEditText().getText().toString();
        String password = tilPassword.getEditText().getText().toString();

        Call<RegisterResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .register(name, address, phone, email, password);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse = response.body();
                Log.d("TAG", "message " + response.body());
                if (response.isSuccessful()){
                    if (registerResponse.getStatus().equals("success")){
                        SweetAlertDialog sweet_dialog = new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE);
                        sweet_dialog.setTitleText("Pengguna berhasil didaftarkan");
                        sweet_dialog.show();
                        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }else {
                        try {
                            String errorBody = response.errorBody().string();
                            JSONObject jsonObject = new JSONObject(errorBody.trim());
                            jsonObject = jsonObject.getJSONObject("errors");
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
                            Toast.makeText(mContext, errors.toString(), Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
                loading.dismiss();
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                loading.dismiss();
                Toast.makeText(mContext, "Kesalahan terjadi. Silakan coba beberapa saat lagi.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
