package com.ardiarahma.sik_bumdesa.activities.dashboard;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.RetrofitClient;
import com.ardiarahma.sik_bumdesa.networks.SharedPref;
import com.ardiarahma.sik_bumdesa.networks.adapters.JurnalViewPagerAdapter;
import com.ardiarahma.sik_bumdesa.networks.models.Akun_DataAkun;
import com.ardiarahma.sik_bumdesa.networks.models.GetAllAkun;
import com.ardiarahma.sik_bumdesa.networks.models.User;
import com.ardiarahma.sik_bumdesa.networks.models.responses.GetAllAkunResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JurnalActivity extends AppCompatActivity {

    ImageButton toolbar_back;

    private ViewPager viewPager;
    private JurnalViewPagerAdapter pagerAdapter;
    private int pYear, pMonth, pDay;
    TextView textDate, datePost;

    com.getbase.floatingactionbutton.FloatingActionButton fab1;

    ArrayList<GetAllAkun> neracaAwalAllAkuns;
    ArrayAdapter<GetAllAkun> allAkunArrayAdapter;

    Dialog dialog, dialog1;
    SweetAlertDialog vDialog, pDialog;

    DatePickerDialog datePickerDialog;
    ImageButton datepicker, jurnal_date;
    TextView date;
    SimpleDateFormat dateFormat, monthFormat, yearFormat;

    TextView tv_keterangan, tv_jumlah, tv_kwitansi, getKwitansi;
    Spinner debit_spinner, kredit_spinner, status_spinner_1, status_spinner_2;
    TextView akun_id_1, status_id_1, akun_id_2, status_id_2;

    TextView tv_months, tv_years;
    Spinner sp_months, sp_years;

    private ArrayList<Akun_DataAkun> dataAkuns;
    ArrayAdapter<Akun_DataAkun> dataAkunArrayAdapter;

    public static final String[] posisi = new String[]{
            "Debit", "Kredit"
    };

    /*
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
    */

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
                status_spinner_1 = dialog.findViewById(R.id.status_spinner);
                textDate = dialog.findViewById(R.id.date);
                datePost = dialog.findViewById(R.id.datePost);
                datepicker = dialog.findViewById(R.id.date_btn);
                akun_id_1 = dialog.findViewById(R.id.id_debit);
                status_id_1 = dialog.findViewById(R.id.id_status);

                Button add_jurnal = dialog.findViewById(R.id.add_jkredit);
                add_jurnal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addAnotherJurnal();
                        dialog.hide();
                    }
                });


                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                String currentDate = sdf.format(new Date().getTime());
                String currentDate2 = sdf2.format(new Date().getTime());
                textDate.setText(currentDate);
                datePost.setText(currentDate2);

                datepicker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDatePicker();
                    }
                });

                loadAllAkunDebit();

                debit_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#8c8c8c"));
                        ((TextView) parent.getChildAt(0)).setTextSize(16);
                        ((TextView) parent.getChildAt(0)).setGravity(Gravity.END);
                        GetAllAkun AllAkun = (GetAllAkun) parent.getSelectedItem();
                        akun_id_1.setText(String.valueOf(AllAkun.getAkunId()));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, posisi);
//                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.posisi_normal, R.layout.support_simple_spinner_dropdown_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                status_spinner_1.setAdapter(adapter);
                status_spinner_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#8c8c8c"));
                        ((TextView) parent.getChildAt(0)).setTextSize(16);
                        ((TextView) parent.getChildAt(0)).setGravity(Gravity.END);
                        String selectedItem = String.valueOf(parent.getItemIdAtPosition(position)).toString();
                        if (selectedItem.equals("Debit")){
                            status_id_1.setText("d");
                        }else if (selectedItem.equals("Kredit")){
                            status_id_1.setText("k");
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

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

    public void showDatePicker() {
        final Calendar c = Calendar.getInstance();
        pYear = c.get(Calendar.YEAR);
        pMonth = c.get(Calendar.MONTH);
        pDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener pDateSetListener = new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                pYear = year;
                pMonth = monthOfYear + 1;
                pDay = dayOfMonth;

                String fm = "" + pMonth;
                String fd = "" + pDay;
                if (pMonth < 10) {
                    fm = "0" + pMonth;
                }
                if (pDay < 10) {
                    fd = "0" + pDay;
                }
                textDate.setText(fd + "/" + fm + "/" + pYear);

                datePost.setText(pYear + "-" + fm + "-" + fd);
            }
        };
        DatePickerDialog dialog = new DatePickerDialog(JurnalActivity.this, pDateSetListener, pYear, pMonth, pDay);
        dialog.getDatePicker().setMaxDate(new Date().getTime());
        dialog.show();
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
                createJurnal();
//                SweetAlertDialog sweet_dialog = new SweetAlertDialog(JurnalActivity.this, SweetAlertDialog.SUCCESS_TYPE);
//                sweet_dialog.setTitleText("Jurnal debit berhasil ditambahkan");
//                sweet_dialog.show();
//                dialog.dismiss();
//                vDialog.dismissWithAnimation();
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

    public void validationAnotherJurnal(){
        final SweetAlertDialog vDialog = new SweetAlertDialog(JurnalActivity.this, SweetAlertDialog.WARNING_TYPE);
        vDialog.setTitleText("Apakah data sudah benar?");
        vDialog.setConfirmText("Ya, benar");
        vDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                createAnotherJurnal();
//                SweetAlertDialog sweet_dialog = new SweetAlertDialog(JurnalActivity.this, SweetAlertDialog.SUCCESS_TYPE);
//                sweet_dialog.setTitleText("Jurnal debit berhasil ditambahkan");
//                sweet_dialog.show();
//                dialog.dismiss();
//                vDialog.dismissWithAnimation();
            }
        });
        vDialog.setCancelButton("Belum", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                vDialog.dismissWithAnimation();
                dialog1.show();
            }
        }).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Calendar newDate = Calendar.getInstance(TimeZone.getDefault());
//        int tahun = newDate.get(Calendar.YEAR);
//        tv_years.setText(String.valueOf(tahun));
        //getNeracaAwal();
//        loadParent();
    }

    public void loadAllAkunDebit() {
        User user = SharedPref.getInstance(this).getBaseUser();
        String token = "Bearer " + user.getToken();
        Call<GetAllAkunResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getAllAkun(token);

        call.enqueue(new Callback<GetAllAkunResponse>() {
            @Override
            public void onResponse(Call<GetAllAkunResponse> call, Response<GetAllAkunResponse> response) {
//                pDialog.dismissWithAnimation();
                GetAllAkunResponse getAllAkunResponse = response.body();
                if (response.isSuccessful()) {
                    if (getAllAkunResponse.getStatus().equals("success")) {
                        neracaAwalAllAkuns = getAllAkunResponse.getNeracaAwalAllAkuns();
                        allAkunArrayAdapter = new ArrayAdapter<>(JurnalActivity.this, android.R.layout.simple_spinner_item, neracaAwalAllAkuns);
                        allAkunArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        debit_spinner.setAdapter(allAkunArrayAdapter);
//                        kredit_spinner.setAdapter(allAkunArrayAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllAkunResponse> call, Throwable t) {
                pDialog.dismissWithAnimation();
                Toast.makeText(JurnalActivity.this, "Kesalahan terjadi, coba beberapa saat lagi.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadAllAkunKredit() {
        User user = SharedPref.getInstance(this).getBaseUser();
        String token = "Bearer " + user.getToken();
        Call<GetAllAkunResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getAllAkun(token);

        call.enqueue(new Callback<GetAllAkunResponse>() {
            @Override
            public void onResponse(Call<GetAllAkunResponse> call, Response<GetAllAkunResponse> response) {
//                pDialog.dismissWithAnimation();
                GetAllAkunResponse getAllAkunResponse = response.body();
                if (response.isSuccessful()) {
                    if (getAllAkunResponse.getStatus().equals("success")) {
                        neracaAwalAllAkuns = getAllAkunResponse.getNeracaAwalAllAkuns();
                        allAkunArrayAdapter = new ArrayAdapter<>(JurnalActivity.this, android.R.layout.simple_spinner_item, neracaAwalAllAkuns);
                        allAkunArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                        debit_spinner.setAdapter(allAkunArrayAdapter);
                        kredit_spinner.setAdapter(allAkunArrayAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllAkunResponse> call, Throwable t) {
                pDialog.dismissWithAnimation();
                Toast.makeText(JurnalActivity.this, "Kesalahan terjadi, coba beberapa saat lagi.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addAnotherJurnal(){
        dialog1 = new Dialog(JurnalActivity.this);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setCancelable(false);
        dialog1.setContentView(R.layout.add_jurnal_kredit);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        tv_jumlah = dialog1.findViewById(R.id.tv_jumlah);
        getKwitansi = dialog1.findViewById(R.id.getKwitansi);
        kredit_spinner = dialog1.findViewById(R.id.kredit_spinner);
        status_spinner_2 = dialog1.findViewById(R.id.status_spinner);
        datepicker = dialog1.findViewById(R.id.date_btn);
        textDate = dialog1.findViewById(R.id.date);
        datePost = dialog1.findViewById(R.id.datePost);
        akun_id_2 = dialog1.findViewById(R.id.id_kredit);
        status_id_2 = dialog1.findViewById(R.id.id_status);

        String isiKwitansi = tv_kwitansi.getText().toString().trim();
        getKwitansi.setText(isiKwitansi);

        loadAllAkunKredit();

        kredit_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#8c8c8c"));
                ((TextView) parent.getChildAt(0)).setTextSize(16);
                ((TextView) parent.getChildAt(0)).setGravity(Gravity.END);
                GetAllAkun AllAkun = (GetAllAkun) parent.getSelectedItem();
                akun_id_2.setText(String.valueOf(AllAkun.getAkunId()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, posisi);
//                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.posisi_normal, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status_spinner_2.setAdapter(adapter);
        status_spinner_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#8c8c8c"));
                ((TextView) parent.getChildAt(0)).setTextSize(16);
                ((TextView) parent.getChildAt(0)).setGravity(Gravity.END);
                String selectedItem = String.valueOf(parent.getItemIdAtPosition(position)).toString();
                if (selectedItem.equals("Debit")){
                    status_id_2.setText("d");
                }else if (selectedItem.equals("Kredit")){
                    status_id_2.setText("k");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button dCancel = dialog1.findViewById(R.id.cancel_button);
        dCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
                dialog.show();
            }
        });

        Button dSave = dialog1.findViewById(R.id.save_button);
        dSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.hide();
                validationAnotherJurnal();
            }
        });

        dialog1.show();
    }

    public void createJurnal(){
        User user = SharedPref.getInstance(this).getBaseUser();
        String token = "Bearer " + user.getToken();

    }

    public void createAnotherJurnal(){
        User user = SharedPref.getInstance(this).getBaseUser();
        String token = "Bearer " + user.getToken();

    }
}
