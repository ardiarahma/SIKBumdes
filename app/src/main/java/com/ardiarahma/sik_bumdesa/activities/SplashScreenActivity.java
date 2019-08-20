package com.ardiarahma.sik_bumdesa.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ardiarahma.sik_bumdesa.R;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;

public class SplashScreenActivity extends AppCompatActivity {

    RoundedHorizontalProgressBar mAnimateProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mAnimateProgress = findViewById(R.id.progress_bar_1);
        mAnimateProgress.animateProgress(2000, 0, 100);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000 );

    }
}
