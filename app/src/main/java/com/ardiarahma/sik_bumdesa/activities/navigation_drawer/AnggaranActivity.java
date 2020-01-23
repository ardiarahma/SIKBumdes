package com.ardiarahma.sik_bumdesa.activities.navigation_drawer;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.activities.LoginActivity;
import com.ardiarahma.sik_bumdesa.activities.MainActivity;
import com.ardiarahma.sik_bumdesa.activities.MonthYearPickerDialog;
import com.ardiarahma.sik_bumdesa.networks.SharedPref;
import com.ardiarahma.sik_bumdesa.networks.adapters.Anggaran_BelanjaAdapter;
import com.ardiarahma.sik_bumdesa.networks.adapters.Anggaran_BumdesAdapter;
import com.ardiarahma.sik_bumdesa.networks.adapters.Anggaran_ModalAdapter;
import com.ardiarahma.sik_bumdesa.networks.models.Anggaran_Belanja;
import com.ardiarahma.sik_bumdesa.networks.models.Anggaran_Bumdes;
import com.ardiarahma.sik_bumdesa.networks.models.Anggaran_Modal;
import com.ardiarahma.sik_bumdesa.networks.models.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AnggaranActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar = null;
    Context context = this;

    TextView nav_company_name, nav_company_email;

    User user = SharedPref.getInstance(this).getBaseUser();
    String token = "Bearer " + user.getToken();
    String accept = "application/json";

    ImageButton date_btn;
    SimpleDateFormat monthFormat, yearFormat;
    TextView tv_months, tv_years;

    RecyclerView rv_bumdes, rv_modal, rv_pembelanjaan;
    ArrayList<Anggaran_Bumdes> anggaranBumdes;
    ArrayList<Anggaran_Modal> anggaranModals;
    ArrayList<Anggaran_Belanja> anggaranBelanjas;
    Anggaran_BumdesAdapter bumdesAdapter;
    Anggaran_ModalAdapter modalAdapter;
    Anggaran_BelanjaAdapter belanjaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anggaran);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        View headerView = navigationView.getHeaderView(0);
//        nav_company_name = headerView.findViewById(R.id.nav_company_name);
//        nav_company_email = headerView.findViewById(R.id.nav_company_email);
//        nav_company_name.setText(user.getNama());
//        nav_company_email.setText(user.getEmail());
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

        loadData();

        rv_bumdes = findViewById(R.id.rv_pendapatan_bumdes);
        rv_modal = findViewById(R.id.rv_penyertaan_modal);
        rv_pembelanjaan = findViewById(R.id.rv_belanja);

        bumdesAdapter = new Anggaran_BumdesAdapter(AnggaranActivity.this, anggaranBumdes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AnggaranActivity.this);
        rv_bumdes.setLayoutManager(layoutManager);
        rv_bumdes.setAdapter(bumdesAdapter);

        modalAdapter = new Anggaran_ModalAdapter(AnggaranActivity.this, anggaranModals);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(AnggaranActivity.this);
        rv_modal.setLayoutManager(layoutManager1);
        rv_modal.setAdapter(modalAdapter);

        belanjaAdapter = new Anggaran_BelanjaAdapter(AnggaranActivity.this, anggaranBelanjas);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(AnggaranActivity.this);
        rv_pembelanjaan.setLayoutManager(layoutManager2);
        rv_pembelanjaan.setAdapter(belanjaAdapter);
    }

    public void loadData(){
        anggaranBumdes = new ArrayList<>();
        anggaranBumdes.add(new Anggaran_Bumdes("Pendapatan Wisata", 0000));
        anggaranBumdes.add(new Anggaran_Bumdes("Pendapatan Homestay", 0000));
        anggaranBumdes.add(new Anggaran_Bumdes("Pendapatan Resto", 0000));
        anggaranBumdes.add(new Anggaran_Bumdes("Pendapatan Event", 0000));

        anggaranModals = new ArrayList<>();
        anggaranModals.add(new Anggaran_Modal("Penyertaan Modal Desa", 000));
        anggaranModals.add(new Anggaran_Modal("Penyertaan Modal Masyarakat", 000));
        anggaranModals.add(new Anggaran_Modal("Penyertaan Modal Pihak Ketiga", 000));

        anggaranBelanjas = new ArrayList<>();
        anggaranBelanjas.add(new Anggaran_Belanja("Belanja Pegawai", 000));
        anggaranBelanjas.add(new Anggaran_Belanja("Belanja Barang dan Jasa", 000));
        anggaranBelanjas.add(new Anggaran_Belanja("Belanja Modal", 000));
        anggaranBelanjas.add(new Anggaran_Belanja("Belanja Pembiayaan", 000));
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
//                loadEkuitas();
            }
        });
        pd.show(getFragmentManager(), "MonthYearPickerDialog");
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.anggaran, menu);
//        return true;
//    }

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
                Intent intent = new Intent(AnggaranActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_akun:
                Intent intent1 = new Intent(AnggaranActivity.this, AccountActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_neraca:
                Intent intent2 = new Intent(AnggaranActivity.this, NeracaAwalActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_rab:
                Intent intent3 = new Intent(AnggaranActivity.this, AnggaranActivity.class);
                startActivity(intent3);
                break;
            case R.id.nav_settings:
                Intent intent4 = new Intent(AnggaranActivity.this, UserActivity.class);
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
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setTitleText("Konfirmasi");
        sweetAlertDialog.setConfirmText("Keluar");
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                SharedPref.getInstance(context).clear();
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(context, "Anda berhasil keluar", Toast.LENGTH_SHORT).show();
            }
        });
        sweetAlertDialog.setCancelButton("Batal", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
            }
        }).show();
    }
}
