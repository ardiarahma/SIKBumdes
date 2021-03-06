package com.example.sik_bumdesa.activities.navigation_drawer;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sik_bumdesa.R;
import com.example.sik_bumdesa.activities.MainActivity;
import com.example.sik_bumdesa.networks.RetrofitClient;
import com.example.sik_bumdesa.networks.SharedPref;
import com.example.sik_bumdesa.networks.adapters.NeracaAwal_ParentAdapter;
import com.example.sik_bumdesa.networks.models.GetAllAkun;
import com.example.sik_bumdesa.networks.models.NeracaAwal_Parent;
import com.example.sik_bumdesa.networks.models.User;
import com.example.sik_bumdesa.networks.models.responses.NeracaAwalAddResponse;
import com.example.sik_bumdesa.networks.models.responses.GetAllAkunResponse;
import com.example.sik_bumdesa.networks.models.responses.NeracaAwalParentResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    ImageButton datepicker;

    com.getbase.floatingactionbutton.FloatingActionButton fab1;

    private ArrayList<NeracaAwal_Parent> neracaAwalParentArrayList;
    private RecyclerView rv_parent;
    private NeracaAwal_ParentAdapter parentAdapter;
    private TextView textDebit, textKredit;
    private int pYear, pMonth, pDay;

    ArrayList<GetAllAkun> neracaAwalAllAkuns;
    ArrayAdapter<GetAllAkun> allAkunArrayAdapter;

    EditText jumlah_balance;
    Spinner spNamaAkun;
    TextView selectedId, textDate, datePost;

    Dialog dialog, year_dialog;
    SweetAlertDialog vDialog;

    DatePickerDialog datePickerDialog;
    ImageButton balance_date;
    TextView tv_months;
    public static TextView tv_years;
    SimpleDateFormat dateFormat, monthFormat, yearFormat;

    Calendar newDate = Calendar.getInstance(TimeZone.getDefault());
    int tahun = newDate.get(Calendar.YEAR);

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

                nopicker.setMaxValue(tahun + 50);
                nopicker.setMinValue(tahun - 50);
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

                spNamaAkun = dialog.findViewById(R.id.spNamaAkun);
                selectedId = dialog.findViewById(R.id.selectedId);
                textDate = dialog.findViewById(R.id.date);
                datePost = dialog.findViewById(R.id.datePost);
                datepicker = dialog.findViewById(R.id.date_btn);
                jumlah_balance = dialog.findViewById(R.id.jumlah_balance);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                String currentDate = sdf.format(new Date().getTime());
                String currentDate2 = sdf2.format(new Date().getTime());
                textDate.setText(currentDate);
                datePost.setText(currentDate2);

                loadAllAkun();

                spNamaAkun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#8c8c8c"));
                        ((TextView) parent.getChildAt(0)).setTextSize(16);
                        ((TextView) parent.getChildAt(0)).setGravity(Gravity.END);
                        GetAllAkun neracaAwalAllAkun = (GetAllAkun) parent.getSelectedItem();
                        selectedId.setText(String.valueOf(neracaAwalAllAkun.getAkunId()));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

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

        rv_parent = findViewById(R.id.rv_parent);
        textDebit = findViewById(R.id.textDebit);
        textKredit = findViewById(R.id.textKredit);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Calendar newDate = Calendar.getInstance(TimeZone.getDefault());
        int tahun = newDate.get(Calendar.YEAR);
        tv_years.setText(String.valueOf(tahun));
        //getNeracaAwal();
        loadParent();
    }

    public void loadParent() {
        final SweetAlertDialog pDialog = new SweetAlertDialog(NeracaAwalActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#e7a248"));
        pDialog.setTitleText("Tunggu sesaat..");
        pDialog.setCancelable(false);
        pDialog.show();

        User user = SharedPref.getInstance(this).getBaseUser();
        String token = "Bearer " + user.getToken();
        int tahun_param = Integer.parseInt(tv_years.getText().toString());

        Call<NeracaAwalParentResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .neracaParent(token, tahun_param);

        call.enqueue(new Callback<NeracaAwalParentResponse>() {
            @Override
            public void onResponse(Call<NeracaAwalParentResponse> call, Response<NeracaAwalParentResponse> response) {
                pDialog.dismissWithAnimation();
                NeracaAwalParentResponse neracaAwalParentResponse = response.body();
                if (response.isSuccessful()) {
                    if (neracaAwalParentResponse.getStatus().equals("success")) {
                        textDebit.setText(String.valueOf(neracaAwalParentResponse.getTotalDebit()));
                        textKredit.setText(String.valueOf(neracaAwalParentResponse.getTotalKredit()));
                        neracaAwalParentArrayList = neracaAwalParentResponse.getNeracaAwalParents();
                        parentAdapter = new NeracaAwal_ParentAdapter(neracaAwalParentArrayList, NeracaAwalActivity.this);
                        RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getApplicationContext());
                        rv_parent.setLayoutManager(eLayoutManager);
                        rv_parent.setItemAnimator(new DefaultItemAnimator());
                        rv_parent.setAdapter(parentAdapter);
                    }

                }
            }

            @Override
            public void onFailure(Call<NeracaAwalParentResponse> call, Throwable t) {
                pDialog.dismissWithAnimation();
                Toast.makeText(NeracaAwalActivity.this, "Kesalahan terjadi, coba beberapa saat lagi.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadAllAkun() {
        User user = SharedPref.getInstance(this).getBaseUser();
        String token = "Bearer " + user.getToken();
        Call<GetAllAkunResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getAllAkun(token);

        call.enqueue(new Callback<GetAllAkunResponse>() {
            @Override
            public void onResponse(Call<GetAllAkunResponse> call, Response<GetAllAkunResponse> response) {
                GetAllAkunResponse getAllAkunResponse = response.body();
                if (response.isSuccessful()) {
                    if (getAllAkunResponse.getStatus().equals("success")) {
                        neracaAwalAllAkuns = getAllAkunResponse.getNeracaAwalAllAkuns();
                        allAkunArrayAdapter = new ArrayAdapter<>(NeracaAwalActivity.this, android.R.layout.simple_spinner_item, neracaAwalAllAkuns);
                        allAkunArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spNamaAkun.setAdapter(allAkunArrayAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllAkunResponse> call, Throwable t) {
                Toast.makeText(NeracaAwalActivity.this, "Kesalahan terjadi, coba beberapa saat lagi.", Toast.LENGTH_SHORT).show();
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
        DatePickerDialog dialog = new DatePickerDialog(NeracaAwalActivity.this, pDateSetListener, pYear, pMonth, pDay);
        dialog.getDatePicker().setMaxDate(new Date().getTime());
        dialog.show();
    }

    public void validationAccount() {
        vDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        vDialog.setTitleText("Apakah data sudah benar?");
        vDialog.setConfirmText("Ya, benar");
        vDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                addNeracaAwal();
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

    public void addNeracaAwal() {
        User user = SharedPref.getInstance(this).getBaseUser();
        String token = "Bearer " + user.getToken();
        String totalStr = jumlah_balance.getText().toString().trim();

        if (totalStr.isEmpty()) {
            jumlah_balance.setError("Jumlah harus diisi");
            jumlah_balance.requestFocus();
            return;
        }

        int akunId = Integer.parseInt(selectedId.getText().toString());
        String date = datePost.getText().toString();
        int total = Integer.parseInt(jumlah_balance.getText().toString());

        Call<NeracaAwalAddResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .neracaAdd(token, akunId, date, total);

        call.enqueue(new Callback<NeracaAwalAddResponse>() {
            @Override
            public void onResponse(Call<NeracaAwalAddResponse> call, Response<NeracaAwalAddResponse> response) {
                NeracaAwalAddResponse neracaAwalAddResponse = response.body();
                if (response.isSuccessful()) {
                    if (neracaAwalAddResponse.getStatus().equals("success")) {
                        SweetAlertDialog sweet_dialog = new SweetAlertDialog(NeracaAwalActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                        sweet_dialog.setTitleText("Neraca Awal berhasil ditambahkan");
                        sweet_dialog.show();
                        dialog.dismiss();
                        vDialog.dismissWithAnimation();
                    }
                    if (parentAdapter != null) {
                        parentAdapter.refreshEvents(neracaAwalParentArrayList);
                    }
                    loadParent();
                }
                parentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NeracaAwalAddResponse> call, Throwable t) {
                Toast.makeText(NeracaAwalActivity.this, "Kesalahan terjadi, coba beberapa saat lagi.", Toast.LENGTH_SHORT).show();
            }
        });
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
        switch (id) {
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

    public void logoutConfirmation() {

    }
}
