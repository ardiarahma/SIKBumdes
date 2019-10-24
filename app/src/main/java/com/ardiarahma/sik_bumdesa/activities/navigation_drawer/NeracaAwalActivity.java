package com.ardiarahma.sik_bumdesa.activities.navigation_drawer;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.activities.MainActivity;
import com.ardiarahma.sik_bumdesa.networks.RetrofitClient;
import com.ardiarahma.sik_bumdesa.networks.SharedPref;
import com.ardiarahma.sik_bumdesa.networks.adapters.Neraca_AsetLancarAdapter;
import com.ardiarahma.sik_bumdesa.networks.adapters.Neraca_AsetTetapAdapter;
import com.ardiarahma.sik_bumdesa.networks.models.NeracaAwal;
import com.ardiarahma.sik_bumdesa.networks.models.Neraca_AsetLancar;
import com.ardiarahma.sik_bumdesa.networks.models.Neraca_AsetTetap;
import com.ardiarahma.sik_bumdesa.networks.models.Neraca_UtangLancar;
import com.ardiarahma.sik_bumdesa.networks.models.User;
import com.ardiarahma.sik_bumdesa.networks.models.responses.NeracaAwalResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NeracaAwalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar = null;

    User user = SharedPref.getInstance(this).getBaseUser();
    String token = "Bearer " + user.getToken();
    String accept = "application/json";

    TextView date;
    ImageButton datepicker;
    EditText jumlah_balance;
    Spinner sp_account;

    com.getbase.floatingactionbutton.FloatingActionButton fab1;

    RecyclerView rv_aset_tetap, rv_aset_lancar, rv_utang_lancar, rv_utang_jp, rv_ekuitas,
            rv_pendapatan, rv_pendapatan_2, rv_biaya, rv_biaya_2;
    Neraca_AsetTetapAdapter adapter_asetTetap;
    Neraca_AsetLancarAdapter adapter_asetLancar;
    ArrayList<NeracaAwal> neracaAwals;
    ArrayList<NeracaAwal> neracaAwalAsetLancer;
    ArrayList<NeracaAwal> neracaAwalAsetTetap;

    private final int asetLancar = 11;
    private final int asetTetap = 12;

    Dialog dialog, year_dialog;
    SweetAlertDialog vDialog, pDialog;

    DatePickerDialog datePickerDialog;
    ImageButton balance_date;
    TextView tv_months, tv_years;
    SimpleDateFormat dateFormat, monthFormat, yearFormat;

    TextView nav_company_name, nav_company_email;

    Calendar newDate = Calendar.getInstance(TimeZone.getDefault());
    int tahun = newDate.get(Calendar.YEAR);
//    int bulan = newDate.get(Calendar.MONTH);

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
        setContentView(R.layout.activity_neraca_awal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        Calendar newDate = Calendar.getInstance(TimeZone.getDefault());
//        final int tahun = newDate.get(Calendar.YEAR);
//        tv_years.setText(String.valueOf(tahun));

//        View headerView = navigationView.getHeaderView(1);
//        nav_company_name = headerView.findViewById(R.id.nav_company_name);
//        nav_company_email = headerView.findViewById(R.id.nav_company_email);
//        nav_company_name.setText(user.getNama());
//        nav_company_email.setText(user.getEmail());

        pDialog = new SweetAlertDialog(NeracaAwalActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#e7a248"));
        pDialog.setTitleText("Tunggu sesaat..");
        pDialog.setCancelable(false);
        pDialog.show();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        balance_date = findViewById(R.id.balance_date);
        balance_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                createDatePicker().show();
                year_dialog = new Dialog(NeracaAwalActivity.this);
                year_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                year_dialog.setCancelable(false);
                year_dialog.setContentView(R.layout.show_tahun);

                tv_years.setText(String.valueOf(tahun));
                final NumberPicker nopicker = year_dialog.findViewById(R.id.year_picker);

                nopicker.setMaxValue(tahun+50);
                nopicker.setMinValue(tahun-50);
                nopicker.setWrapSelectorWheel(false);
                nopicker.setValue(tahun);
                nopicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

                Button year_cancel = year_dialog.findViewById(R.id.cancel_button);
                year_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        year_dialog.dismiss();
                    }
                });

                Button save_year = year_dialog.findViewById(R.id.save_button);
                save_year.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_years.setText(String.valueOf(nopicker.getValue()));
                        year_dialog.dismiss();
                    }
                });
                year_dialog.show();
            }
        });

        tv_months = findViewById(R.id.month);
        tv_years = findViewById(R.id.year);

        fab1 = findViewById(R.id.fab_1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(NeracaAwalActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.add_balance);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                sp_account = dialog.findViewById(R.id.nama_akun);
                date = dialog.findViewById(R.id.date);
                datepicker = dialog.findViewById(R.id.date_btn);
                jumlah_balance = dialog.findViewById(R.id.jumlah_balance);

                ArrayAdapter<String> account_adapter = new ArrayAdapter<String>(NeracaAwalActivity.this, R.layout.support_simple_spinner_dropdown_item, akun);
                account_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                sp_account.setAdapter(account_adapter);

                dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

                datepicker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDatePicker();
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
                        validationAccount();
                    }
                });
                dialog.show();
            }
        });

        neracaAwalAsetLancer = new ArrayList<>();
        neracaAwalAsetTetap = new ArrayList<>();
        rv_aset_lancar = findViewById(R.id.rv_aset_lancar);
//        adapter_asetLancar = new Neraca_AsetLancarAdapter(this, neracaAwals);
        rv_aset_tetap = findViewById(R.id.rv_aset_tetap);
//        adapter_asetTetap= new Neraca_AsetTetapAdapter(this, neracaAwals);

//        neracaAsetLancars = new ArrayList<>();
//        neracaAsetLancars.add(new Neraca_AsetLancar("Kas", 1000000));
//        neracaAsetLancars.add(new Neraca_AsetLancar("Kas di Bank", 2000000));
//        neracaAsetLancars.add(new Neraca_AsetLancar("Piutang Dagang", 1000000));
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
//                false);
//        rv_aset_lancar.setAdapter(adapter_asetLancar);
//        rv_aset_lancar.setHasFixedSize(true);
//        rv_aset_lancar.setLayoutManager(linearLayoutManager);
//        adapter_asetLancar.notifyDataSetChanged();
//
//        neracaAsetTetaps = new ArrayList<>();
//        neracaAsetTetaps.add(new Neraca_AsetTetap("Gedung", 3000000));
//        LinearLayoutManager linearLayoutManager_1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
//                false);
//        rv_aset_tetap.setAdapter(adapter_asetTetap);
//        rv_aset_tetap.setHasFixedSize(true);
//        rv_aset_tetap.setLayoutManager(linearLayoutManager_1);
//        adapter_asetTetap.notifyDataSetChanged();

        adapter_asetLancar = new Neraca_AsetLancarAdapter(this, neracaAwalAsetLancer);
        adapter_asetTetap= new Neraca_AsetTetapAdapter(this, neracaAwalAsetTetap);
        rv_aset_lancar.setAdapter(adapter_asetLancar);
        rv_aset_tetap.setAdapter(adapter_asetTetap);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Calendar newDate = Calendar.getInstance(TimeZone.getDefault());
        int tahun = newDate.get(Calendar.YEAR);
        tv_years.setText(String.valueOf(tahun));
        getNeracaAwal();
    }

    public void setAdapter(){

    }

    public void getNeracaAwal(){
        int tahun_param = Integer.parseInt(tv_years.getText().toString());

        Call<NeracaAwalResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .neraca_awal(token, accept, tahun_param);
        call.enqueue(new Callback<NeracaAwalResponse>() {
            @Override
            public void onResponse(Call<NeracaAwalResponse> call, Response<NeracaAwalResponse> response) {
                pDialog.dismissWithAnimation();
                NeracaAwalResponse neracaAwalResponse = response.body();
                if (response.isSuccessful()){
                    if (neracaAwalResponse.getStatus().equals("success")){
                        neracaAwals = neracaAwalResponse.getNeracaAwals();

                        adapter_asetTetap = new Neraca_AsetTetapAdapter(NeracaAwalActivity.this, neracaAwals);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        rv_aset_tetap.setLayoutManager(layoutManager);
                        adapter_asetTetap.filterID(asetTetap);
                        rv_aset_tetap.setAdapter(adapter_asetTetap);

                        /*

                        for (int i = 0; i < neracaAwals.size(); i++){
                            NeracaAwal neracaAwal = neracaAwals.get(i);
                            if (neracaAwal.getKode_klasifikasi() == asetLancar){
                                rv_aset_lancar.setAdapter(adapter_asetLancar);
                                adapter_asetLancar.addItem(neracaAwalAsetLancer);

                            }else if (neracaAwal.getKode_klasifikasi() == asetTetap){
                                rv_aset_tetap.setAdapter(adapter_asetTetap);
                            }
                        }
                        */
//                        setAdapter();
                    }
                }else {
                    pDialog.dismissWithAnimation();
                    Toast.makeText(NeracaAwalActivity.this, "Gagal mengambil data parent akun", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<NeracaAwalResponse> call, Throwable t) {
                pDialog.dismissWithAnimation();
                Toast.makeText(NeracaAwalActivity.this, "Kesalahan terjadi, coba beberapa saat lagi.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void postNeracaAwal(){

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

    public void validationAccount(){
        final SweetAlertDialog vDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        vDialog.setTitleText("Apakah data sudah benar?");
        vDialog.setConfirmText("Ya, benar");
        vDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                SweetAlertDialog sweet_dialog = new SweetAlertDialog(NeracaAwalActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                sweet_dialog.setTitleText("Neraca Awal berhasil ditambahkan");
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_home:
                Intent intent = new Intent(NeracaAwalActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_akun:
                Intent intent1 = new Intent(NeracaAwalActivity.this, AccountActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_neraca:
                Intent intent2 = new Intent(NeracaAwalActivity.this, NeracaAwalActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_rab:
                Intent intent3 = new Intent(NeracaAwalActivity.this, AnggaranActivity.class);
                startActivity(intent3);
                break;
            case R.id.nav_settings:
                Intent intent4 = new Intent(NeracaAwalActivity.this, UserActivity.class);
                break;
            case R.id.logout:
                logoutConfirmation();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logoutConfirmation(){

    }
}
