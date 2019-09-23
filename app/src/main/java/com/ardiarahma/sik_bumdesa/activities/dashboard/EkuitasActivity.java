package com.ardiarahma.sik_bumdesa.activities.dashboard;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.database.adapters.Ekuitas_ModalAdapter;
import com.ardiarahma.sik_bumdesa.database.adapters.Ekuitas_SaldoAdapter;
import com.ardiarahma.sik_bumdesa.database.adapters.LabaRugi_PendapatanAdapter;
import com.ardiarahma.sik_bumdesa.database.models.Ekuitas_Modal;
import com.ardiarahma.sik_bumdesa.database.models.Ekuitas_Saldo;
import com.ardiarahma.sik_bumdesa.database.models.LabaRugi_Pendapatan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class EkuitasActivity extends AppCompatActivity {

    RecyclerView rv_modal, rv_saldo;
    Ekuitas_ModalAdapter ekuitas_modalAdapter;
    Ekuitas_SaldoAdapter ekuitas_saldoAdapter;
    ArrayList<Ekuitas_Modal> ekuitas_modals;
    ArrayList<Ekuitas_Saldo> ekuitas_saldos;

    TextView tv_totalAll;

    ImageButton toolbar_back;

    DatePickerDialog datePickerDialog;
    ImageButton date_btn;
    SimpleDateFormat monthFormat, yearFormat;
    TextView tv_months, tv_years;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekuitas);

        toolbar_back = findViewById(R.id.toolbar_back);
        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tv_totalAll = findViewById(R.id.total_sum);

        ekuitas_modals = new ArrayList<>();
        rv_modal = findViewById(R.id.rv_modal);
        ekuitas_modalAdapter = new Ekuitas_ModalAdapter(this, ekuitas_modals);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        rv_modal.setAdapter(ekuitas_modalAdapter);
        rv_modal.setHasFixedSize(true);
        rv_modal.setLayoutManager(linearLayoutManager);
        ekuitas_modalAdapter.notifyDataSetChanged();
        ekuitas_modals.add(new Ekuitas_Modal("Modal Disetor", 0));

        ekuitas_saldos = new ArrayList<>();
        rv_saldo = findViewById(R.id.rv_saldo);
        ekuitas_saldoAdapter= new Ekuitas_SaldoAdapter(this, ekuitas_saldos);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        rv_saldo.setAdapter(ekuitas_saldoAdapter);
        rv_saldo.setHasFixedSize(true);
        rv_saldo.setLayoutManager(linearLayoutManager1);
        ekuitas_saldoAdapter.notifyDataSetChanged();
        ekuitas_saldos.add(new Ekuitas_Saldo("Saldo Laba Ditahan", 0));
        ekuitas_saldos.add(new Ekuitas_Saldo("Saldo Laba Periode Berjalan", 16000000));

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
