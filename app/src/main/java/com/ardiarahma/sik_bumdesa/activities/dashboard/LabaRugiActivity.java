package com.ardiarahma.sik_bumdesa.activities.dashboard;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.activities.MonthYearPickerDialog;
import com.ardiarahma.sik_bumdesa.networks.RetrofitClient;
import com.ardiarahma.sik_bumdesa.networks.SharedPref;
import com.ardiarahma.sik_bumdesa.networks.adapters.LabaRugi_BiayaAdapter;
import com.ardiarahma.sik_bumdesa.networks.adapters.LabaRugi_LainLainAdapter;
import com.ardiarahma.sik_bumdesa.networks.adapters.LabaRugi_PendapatanAdapter;
import com.ardiarahma.sik_bumdesa.networks.models.LabaRugi_Biaya;
import com.ardiarahma.sik_bumdesa.networks.models.LabaRugi_LainLain;
import com.ardiarahma.sik_bumdesa.networks.models.LabaRugi_Pendapatan;
import com.ardiarahma.sik_bumdesa.networks.models.User;
import com.ardiarahma.sik_bumdesa.networks.models.responses.LabaRugiResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LabaRugiActivity extends AppCompatActivity {

    ImageButton toolbar_back;

    DatePickerDialog datePickerDialog;
    ImageButton date_btn;
    SimpleDateFormat monthFormat, yearFormat;
    TextView tv_months, tv_years;

    TextView tv_totalPendapatan, tv_totalBiaya, tv_Laba, tv_totalLainnya, tv_totalAll;
    RecyclerView rv_pendapatan, rv_biaya, rv_lain_lainnya;
    LabaRugi_BiayaAdapter biayaAdapter;
    LabaRugi_PendapatanAdapter pendapatanAdapter;
    LabaRugi_LainLainAdapter lainLainAdapter;
    ArrayList<LabaRugi_Biaya> labaRugi_biayas;
    ArrayList<LabaRugi_Pendapatan> labaRugi_pendapatans;
    ArrayList<LabaRugi_LainLain> labaRugi_lainLains;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laba);

        toolbar_back = findViewById(R.id.toolbar_back);
        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        monthFormat = new SimpleDateFormat("MM", Locale.US);
        yearFormat = new SimpleDateFormat("yyyy", Locale.US);
        tv_months = findViewById(R.id.month);
        tv_years = findViewById(R.id.year);

        date_btn = findViewById(R.id.date_btn);
        date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });

        tv_totalPendapatan = findViewById(R.id.total_pendapatan);
        tv_totalBiaya = findViewById(R.id.total_biaya);
        tv_totalAll = findViewById(R.id.total_sum);
        tv_Laba = findViewById(R.id.total_labausaha);
        tv_totalLainnya = findViewById(R.id.total_lain_lainnya);

        rv_pendapatan = findViewById(R.id.rv_pendapatan);
        rv_biaya = findViewById(R.id.rv_biaya);
        rv_lain_lainnya = findViewById(R.id.rv_lain_lainnya);
    }

    @Override
    protected void onStart() {
        super.onStart();
        showDate();
    }

    public void showDate(){
        MonthYearPickerDialog pd = new MonthYearPickerDialog();
        pd.setListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

                tv_months.setText(String.valueOf(selectedMonth));
                tv_years.setText(String.valueOf(selectedYear));
                loadLabaRugi();
            }
        });
        pd.show(getFragmentManager(), "MonthYearPickerDialog");
//        Calendar calendar = Calendar.getInstance();
//
//        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                Calendar newDate = Calendar.getInstance();
//                newDate.set(year, month, dayOfMonth);
//                tv_months.setText(monthFormat.format(newDate.getTime()));
//                tv_years.setText(yearFormat.format(newDate.getTime()));
//            }
//        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//        datePickerDialog.show();
    }

    public void loadLabaRugi() {
        User user = SharedPref.getInstance(this).getBaseUser();
        String token = "Bearer " + user.getToken();
        int month = Integer.parseInt(tv_months.getText().toString());
        int year = Integer.parseInt(tv_years.getText().toString());

        Call<LabaRugiResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .labaRugi(token, month, year);

        call.enqueue(new Callback<LabaRugiResponse>() {
            @Override
            public void onResponse(Call<LabaRugiResponse> call, Response<LabaRugiResponse> response) {
                LabaRugiResponse labaRugiResponse = response.body();
                if (response.isSuccessful()) {
                    if (labaRugiResponse.getStatus().equals("success")) {
                        tv_totalPendapatan.setText(String.valueOf(labaRugiResponse.getTotalPendapatan()));
                        tv_totalBiaya.setText(String.valueOf(labaRugiResponse.getTotalBiaya()));
                        tv_Laba.setText(String.valueOf(labaRugiResponse.getLabaUsaha()));
                        tv_totalLainnya.setText(String.valueOf(labaRugiResponse.getTotalLainLain()));
                        tv_totalAll.setText(String.valueOf(labaRugiResponse.getSaldoLabaRugi()));
                        labaRugi_biayas = labaRugiResponse.getLabaRugiBiayas();
                        biayaAdapter = new LabaRugi_BiayaAdapter(LabaRugiActivity.this, labaRugi_biayas);
                        RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getApplicationContext());
                        rv_biaya.setLayoutManager(eLayoutManager);
                        rv_biaya.setItemAnimator(new DefaultItemAnimator());
                        rv_biaya.setAdapter(biayaAdapter);
                        labaRugi_pendapatans = labaRugiResponse.getLabaRugiPendapatans();
                        pendapatanAdapter = new LabaRugi_PendapatanAdapter(LabaRugiActivity.this, labaRugi_pendapatans);
                        RecyclerView.LayoutManager eLayoutManager2 = new LinearLayoutManager(getApplicationContext());
                        rv_pendapatan.setLayoutManager(eLayoutManager2);
                        rv_pendapatan.setItemAnimator(new DefaultItemAnimator());
                        rv_pendapatan.setAdapter(pendapatanAdapter);
                        labaRugi_lainLains = labaRugiResponse.getLabaRugiLainLains();
                        lainLainAdapter = new LabaRugi_LainLainAdapter(LabaRugiActivity.this, labaRugi_lainLains);
                        RecyclerView.LayoutManager eLayoutManager3 = new LinearLayoutManager(getApplicationContext());
                        rv_lain_lainnya.setLayoutManager(eLayoutManager3);
                        rv_lain_lainnya.setItemAnimator(new DefaultItemAnimator());
                        rv_lain_lainnya.setAdapter(lainLainAdapter);
                    }

                }
            }

            @Override
            public void onFailure(Call<LabaRugiResponse> call, Throwable t) {
                Toast.makeText(LabaRugiActivity.this, "Kesalahan terjadi, coba beberapa saat lagi.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
