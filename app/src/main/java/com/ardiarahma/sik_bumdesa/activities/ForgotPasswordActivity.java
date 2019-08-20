package com.ardiarahma.sik_bumdesa.activities;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import com.ardiarahma.sik_bumdesa.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    TextInputLayout tilEmail;
    Button forgot_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        tilEmail = findViewById(R.id.emailLayout);
        forgot_pass = findViewById(R.id.forgot_pass);

        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateEmail(){
        String email = tilEmail.getEditText().getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            loading.dismiss();
            tilEmail.setError("Masukkan email yang benar");
            tilEmail.requestFocus();
            return false;
        }else {
            tilEmail.setError(null);
            return true;
        }
    }
}
