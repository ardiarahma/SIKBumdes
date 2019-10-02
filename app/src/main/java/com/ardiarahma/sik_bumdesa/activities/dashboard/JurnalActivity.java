package com.ardiarahma.sik_bumdesa.activities.dashboard;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.adapters.JurnalViewPagerAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class JurnalActivity extends AppCompatActivity {

    ImageButton toolbar_back;

    private ViewPager viewPager;
    private JurnalViewPagerAdapter pagerAdapter;

    com.getbase.floatingactionbutton.FloatingActionButton fab1;

    Dialog dialog;
    SweetAlertDialog vDialog;

    DatePickerDialog datePickerDialog;
    ImageButton date_btn, jurnal_date;
    TextView date;
    SimpleDateFormat dateFormat, monthFormat, yearFormat;

    TextView tv_keterangan, tv_jumlah, tv_kwitansi;
    Spinner debit_spinner, kredit_spinner;

    TextView tv_months, tv_years;
    Spinner sp_months, sp_years;
    public static final String[] months = new String[]{
            "Januari", "Februari", "Maret", "April", "Mei", "Juni",
            "Juli", "Agustus", "September", "Oktober", "November", "Desember"
    };
    public static final String[] akun = new String[]{
            "Kas", "Kas di Bank", "Piutang Dagang", " Sewa Dibayar Dimuka", "Aset Lainnya", "Utang Dagang",
            "Utang Gaji", "Utang Bank", "Obligasi", "Modal Disetor", "Saldo Laba Ditahan", "Saldo Laba Tahun Berjalan",
            "Pendapatan Wisata", "Pendapatan Homestay", "Pendapatan Resto", "Pendapatan Event", "Biaya Gaji", "Biaya Listrik, Air, dan Telepon",
            "Biaya Administrasi dan Umum", "Biaya Pemasaran", "Biaya Perlengkapan Kantor", "Biaya Sewa", "Biaya Asuransi", "Biaya Penyusutan Gedung",
            "Biaya Penyusutan Kendaraan", "Biaya Penyusutan Peralatan Kantor", "Pendapatan Lain-lain",
            "Biaya Lain-lain"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jurnal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar_back = findViewById(R.id.toolbar_back);
        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        sp_months = findViewById(R.id.sp_month);
//        sp_years = findViewById(R.id.sp_year);
        tv_months = findViewById(R.id.month);
        tv_years = findViewById(R.id.year);

        monthFormat = new SimpleDateFormat("MM", Locale.US);
        yearFormat = new SimpleDateFormat("yyyy", Locale.US);

        jurnal_date = findViewById(R.id.jurnal_date);
        jurnal_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showJurnalDate();
            }
        });

        viewPager = findViewById(R.id.viewpager);
        pagerAdapter = new JurnalViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

//        //set year
//        ArrayList<String> years = new ArrayList<String>();
//        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
//        for (int i = 2000; i <= thisYear; i++){
//            years.add(Integer.toString(i));
//        }
//        ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, years);
//        sp_years.setAdapter(adapter_year);
//
//        //set months
//        final ArrayAdapter<String> adapter_month = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, months);
//        sp_months.setAdapter(adapter_month);

        fab1 = findViewById(R.id.fab_1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(JurnalActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.add_jurnal);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                tv_keterangan = dialog.findViewById(R.id.tv_keterangan);
                tv_jumlah = dialog.findViewById(R.id.tv_jumlah);
                tv_kwitansi = dialog.findViewById(R.id.tv_kwitansi);
                debit_spinner = dialog.findViewById(R.id.debit_spinner);
                kredit_spinner = dialog.findViewById(R.id.kredit_spinner);
                date_btn = dialog.findViewById(R.id.date_btn);
                date = dialog.findViewById(R.id.date);

                dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

                date_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDatePicker();
                    }
                });

                ArrayAdapter<String> debit_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, akun);
                debit_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                debit_spinner.setAdapter(debit_adapter);

                ArrayAdapter<String> kredit_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, akun);
                kredit_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                kredit_spinner.setAdapter(kredit_adapter);


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
                        validationJurnal();
                    }
                });
                dialog.show();
            }
        });
    }

    public void showDatePicker(){
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                date.setText(dateFormat.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void showJurnalDate(){
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

    public void validationJurnal(){
        final SweetAlertDialog vDialog = new SweetAlertDialog(JurnalActivity.this, SweetAlertDialog.WARNING_TYPE);
        vDialog.setTitleText("Apakah data sudah benar?");
        vDialog.setConfirmText("Ya, benar");
        vDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                SweetAlertDialog sweet_dialog = new SweetAlertDialog(JurnalActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                sweet_dialog.setTitleText("Jurnal debit berhasil ditambahkan");
                sweet_dialog.show();
                dialog.dismiss();
                vDialog.dismissWithAnimation();
            }
        });
        vDialog.setCancelButton("Belum", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                vDialog.dismissWithAnimation();
                dialog.show();
            }
        }).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        showJurnalDate();
    }
}
