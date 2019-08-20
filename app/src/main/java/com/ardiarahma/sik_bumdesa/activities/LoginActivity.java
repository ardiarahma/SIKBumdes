package com.ardiarahma.sik_bumdesa.activities;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ardiarahma.sik_bumdesa.R;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout tilEmail, tilPassword;
    Button login;
    TextView registration, forgot_pass;

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
                if (!validateEmail() | !validatePassword()){
                    return;
                }

                String input = "Email : " + tilEmail.getEditText().getText().toString() + "\n";
                input += "Pass : " + tilPassword.getEditText().getText().toString();

                Toast.makeText(LoginActivity.this, input, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
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


    private boolean validateEmail(){
        String email = tilEmail.getEditText().getText().toString().trim();

        if (email.isEmpty()){
            tilEmail.setError("Email harus diisi");
            return false;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            loading.dismiss();
            tilEmail.setError("Masukkan email yang benar");
            tilEmail.requestFocus();
            return false;
        }else {
            tilEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){
        String password = tilPassword.getEditText().getText().toString().trim();

        if (password.isEmpty()){
            tilPassword.setError("Password harus diisi");
            return false;
        }else if (password.length() < 8) {
//            loading.dismiss();
            tilPassword.setError("Password harus berisi minimal 8 karakter");
            tilPassword.requestFocus();
            return false;
        }else {
            tilPassword.setError(null);
            return true;
        }
    }
}
