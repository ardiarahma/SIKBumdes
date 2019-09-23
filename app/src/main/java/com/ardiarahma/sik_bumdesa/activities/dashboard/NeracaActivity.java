package com.ardiarahma.sik_bumdesa.activities.dashboard;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.database.adapters.LabaRugi_BiayaAdapter;
import com.ardiarahma.sik_bumdesa.database.adapters.LabaRugi_PendapatanAdapter;
import com.ardiarahma.sik_bumdesa.database.adapters.NeracaUmum_AsetLancarAdapter;
import com.ardiarahma.sik_bumdesa.database.adapters.NeracaUmum_AsetTetapAdapter;
import com.ardiarahma.sik_bumdesa.database.models.LabaRugi_Biaya;
import com.ardiarahma.sik_bumdesa.database.models.LabaRugi_Pendapatan;
import com.ardiarahma.sik_bumdesa.database.models.NeracaUmum_AsetLancar;
import com.ardiarahma.sik_bumdesa.database.models.NeracaUmum_AsetTetap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class NeracaActivity extends AppCompatActivity {

    ImageButton toolbar_back;

    DatePickerDialog datePickerDialog;
    ImageButton date_btn;
    SimpleDateFormat monthFormat, yearFormat;
    TextView tv_months, tv_years;

    TextView tv_totalAsetLancar, tv_totalAsetTetap, tv_totalUtangLancar, tv_totalUtangJP, tv_totalEkuitas;
    RecyclerView rv_asetLancar, rv_asetTetap, rv_utangLancar, rv_utangJP, rv_ekuitas;
    NeracaUmum_AsetLancarAdapter asetLancarAdapter;
    NeracaUmum_AsetTetapAdapter asetTetapAdapter;
    ArrayList<NeracaUmum_AsetLancar> asetLancars;
    ArrayList<NeracaUmum_AsetTetap> asetTetaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neraca);

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

        tv_totalAsetLancar= findViewById(R.id.total_asetLancar);
        tv_totalAsetTetap= findViewById(R.id.total_asetTetap);
        tv_totalUtangLancar= findViewById(R.id.total_utangLancar);
        tv_totalUtangJP= findViewById(R.id.total_utangJP);
        tv_totalEkuitas= findViewById(R.id.total_ekuitas);

        asetLancars = new ArrayList<>();
        rv_asetLancar = findViewById(R.id.rv_aset_lancar);
        asetLancarAdapter = new NeracaUmum_AsetLancarAdapter(this, asetLancars);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        rv_asetLancar.setAdapter(asetLancarAdapter);
        rv_asetLancar.setHasFixedSize(true);
        rv_asetLancar.setLayoutManager(linearLayoutManager);
        asetLancarAdapter.notifyDataSetChanged();
        asetLancars.add(new NeracaUmum_AsetLancar("Kas", 50000));
        asetLancars.add(new NeracaUmum_AsetLancar("Kas di Bank", 50000));
    }

    @Override
    protected void onStart() {
        super.onStart();
        showDate();
    }

    public void showDate(){
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                tv_months.setText(monthFormat.format(newDate.getTime()));
                tv_years.setText(yearFormat.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
