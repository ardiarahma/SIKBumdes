package com.ardiarahma.sik_bumdesa.activities.dashboard;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.database.adapters.LabaRugi_BiayaAdapter;
import com.ardiarahma.sik_bumdesa.database.adapters.LabaRugi_PendapatanAdapter;
import com.ardiarahma.sik_bumdesa.database.models.LabaRugi_Biaya;
import com.ardiarahma.sik_bumdesa.database.models.LabaRugi_Pendapatan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LabaRugiActivity extends AppCompatActivity {

    ImageButton datepicker;
    EditText jumlah_balance;
    Spinner sp_months, sp_years, sp_account;
    public static final String[] months = new String[]{
            "Januari", "Februari", "Maret", "April", "Mei", "Juni",
            "Juli", "Agustus", "September", "Oktober", "November", "Desember"
    };

    ImageButton toolbar_back;

    Dialog dialog;
    SweetAlertDialog vDialog;
    DatePickerDialog datePickerDialog;
    ImageButton date_btn;
    TextView date;
    SimpleDateFormat dateFormat;

    TextView tv_totalPendapatan, tv_totalBiaya, tv_Laba, tv_totalAll;
    RecyclerView rv_pendapatan, rv_biaya;
    LabaRugi_BiayaAdapter biayaAdapter;
    LabaRugi_PendapatanAdapter pendapatanAdapter;
    ArrayList<LabaRugi_Biaya> labaRugi_biayas;
    ArrayList<LabaRugi_Pendapatan> labaRugi_pendapatans;

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

        sp_months = findViewById(R.id.sp_month);
        sp_years = findViewById(R.id.sp_year);

        //set year
        ArrayList<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1900; i <= thisYear; i++){
            years.add(Integer.toString(i));
        }

        ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, years);
        sp_years.setAdapter(adapter_year);

        //set months
        final ArrayAdapter<String> adapter_month = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, months);
        sp_months.setAdapter(adapter_month);

        rv_pendapatan = findViewById(R.id.rv_pendapatan);
        pendapatanAdapter = new LabaRugi_PendapatanAdapter(LabaRugiActivity.this, labaRugi_pendapatans);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LabaRugiActivity.this, LinearLayoutManager.VERTICAL,
                false);
        rv_pendapatan.setAdapter(pendapatanAdapter);
        rv_pendapatan.setHasFixedSize(true);
        rv_pendapatan.setLayoutManager(linearLayoutManager);
        pendapatanAdapter.notifyDataSetChanged();

        labaRugi_pendapatans = new ArrayList<>();
        labaRugi_pendapatans.add(new LabaRugi_Pendapatan("Pendapatan Wisata", 10000000));
        labaRugi_pendapatans.add(new LabaRugi_Pendapatan("Pendapatan lain", 300000));

        rv_biaya = findViewById(R.id.rv_biaya);
        biayaAdapter = new LabaRugi_BiayaAdapter(LabaRugiActivity.this, labaRugi_biayas);
        LinearLayoutManager linearLayoutManager_1 = new LinearLayoutManager(LabaRugiActivity.this, LinearLayoutManager.VERTICAL,
                false);
        rv_biaya.setAdapter(biayaAdapter);
        rv_biaya.setHasFixedSize(true);
        rv_biaya.setLayoutManager(linearLayoutManager_1);
        biayaAdapter.notifyDataSetChanged();

        labaRugi_biayas = new ArrayList<>();
        labaRugi_biayas.add(new LabaRugi_Biaya("Biaya Gaji", 1000000));
        labaRugi_biayas.add(new LabaRugi_Biaya("Biaya Listrik", 800000));
    }
}
