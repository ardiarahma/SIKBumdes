package com.ardiarahma.sik_bumdesa.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.RetrofitClient;
import com.ardiarahma.sik_bumdesa.networks.SharedPref;
import com.ardiarahma.sik_bumdesa.networks.models.User;
import com.ardiarahma.sik_bumdesa.networks.models.responses.LoginResponse;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout tilEmail, tilPassword;
    Button login;
    TextView registration, forgot_pass;

    SweetAlertDialog pDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tilEmail = findViewById(R.id.emailLayout);
        tilPassword = findViewById(R.id.passwordLayout);
        login = findViewById(R.id.login);
        registration = findViewById(R.id.register);
        forgot_pass = findViewById(R.id.forget_pass);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#e7a248"));
                pDialog.setTitleText("Tunggu sesaat..");
                pDialog.setCancelable(false);
                pDialog.show();
                loginUser();

            }
        });

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    public void loginUser(){

        String email = tilEmail.getEditText().getText().toString().trim();
        if (email.isEmpty()){
            pDialog.dismiss();
            tilEmail.setError("Email harus diisi");
            tilEmail.requestFocus();
            return;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            pDialog.dismiss();
            tilEmail.setError("Masukkan email yang benar");
            tilEmail.requestFocus();
            return;
        }

        String password = tilPassword.getEditText().getText().toString().trim();
        if (password.isEmpty()){
            pDialog.dismiss();
            tilPassword.setError("Password harus diisi");
            tilPassword.requestFocus();
            return;
        }else if (password.length() < 8) {
            pDialog.dismiss();
            tilPassword.setError("Password harus berisi minimal 8 karakter");
            tilPassword.requestFocus();
            return;
        }



        Call<LoginResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .login(email, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                pDialog.dismissWithAnimation();
                LoginResponse loginResponse = response.body();
                Log.d("TAG", "message " + response.body());
                if (response.isSuccessful()){
                    if (loginResponse.getStatus().equals("sukses")){
                        Log.i("debug", "onResponse : Login Successful");
                        pDialog.dismissWithAnimation();
                        SharedPref.getInstance(LoginActivity.this).saveBaseUser(loginResponse.getUser());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else {
                        Log.i("debug", "onResponse : Login Failed");
                        pDialog.dismissWithAnimation();
//                        Toast.makeText(LoginActivity.this, "Kesalahan terjadi. Coba beberapa saat lagi.", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                pDialog.dismissWithAnimation();
                Toast.makeText(LoginActivity.this, "Kesalahan terjadi. Silakan coba beberapa saat lagi.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onStart(){
        super.onStart();
        if (SharedPref.getInstance(this).isLoggedIn()){
            User user = SharedPref.getInstance(this).getBaseUser();
            //ngecek usernya
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


//    private boolean validateEmail(){
//
//    }
//
//    private boolean validatePassword(){
//
//    }
}
