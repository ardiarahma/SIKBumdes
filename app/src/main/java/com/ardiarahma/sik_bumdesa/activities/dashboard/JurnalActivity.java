package com.ardiarahma.sik_bumdesa.activities.dashboard;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.activities.MonthYearPickerDialog;
import com.ardiarahma.sik_bumdesa.activities.navigation_drawer.NeracaAwalActivity;
import com.ardiarahma.sik_bumdesa.networks.RetrofitClient;
import com.ardiarahma.sik_bumdesa.networks.SharedPref;
import com.ardiarahma.sik_bumdesa.networks.adapters.JurnalViewPagerAdapter;
import com.ardiarahma.sik_bumdesa.networks.adapters.Jurnal_Adapter;
import com.ardiarahma.sik_bumdesa.networks.models.Akun_DataAkun;
import com.ardiarahma.sik_bumdesa.networks.models.GetAllAkun;
import com.ardiarahma.sik_bumdesa.networks.models.Jurnal;
import com.ardiarahma.sik_bumdesa.networks.models.Jurnal_All;
import com.ardiarahma.sik_bumdesa.networks.models.Results;
import com.ardiarahma.sik_bumdesa.networks.models.User;
import com.ardiarahma.sik_bumdesa.networks.models.responses.GetAllAkunResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.JurnalCreateResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.JurnalResponse;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import cn.pedant.SweetAlert.SweetAlertDialog;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JurnalActivity extends AppCompatActivity {

    ImageButton toolbar_back;

    private int pYear, pMonth, pDay;
    TextView textDate, datePost;

    com.getbase.floatingactionbutton.FloatingActionButton fab1;
    HorizontalCalendar horizontalCalendar;

    ArrayList<GetAllAkun> neracaAwalAllAkuns;
    ArrayAdapter<GetAllAkun> allAkunArrayAdapter;

    Dialog dialog, dialog1;
    SweetAlertDialog vDialog, pDialog;

    DatePickerDialog datePickerDialog;
    ImageButton datepicker, jurnal_date;
    TextView date;
    SimpleDateFormat dateFormat, monthFormat, yearFormat;

    EditText tv_keterangan, tv_jumlah, tv_kwitansi;
    TextView getKwitansi, kwitansi_id;
    Spinner debit_spinner, kredit_spinner, status_spinner_1, status_spinner_2;
    TextView akun_id_1, status_id_1, akun_id_2, status_id_2;

    TextView tv_months, tv_years;
    Spinner sp_months, sp_years;

    private ArrayList<Akun_DataAkun> dataAkuns;
    ArrayAdapter<Akun_DataAkun> dataAkunArrayAdapter;

    public static final String[] posisi = new String[]{
            "Debit", "Kredit"
    };

    RecyclerView rv_jurnal;
    ArrayList<Jurnal_All> jurnalAlls;
    Jurnal_Adapter jurnal_adapter;
    TextView tv_total_kredit, tv_total_debit;
    SwipeRefreshLayout swipeRefresh;
    LinearLayout layoutNoData, layoutData;
    ShimmerFrameLayout shimmerFrameLayout;

    static String dayFormatted, monthFormatted, yearFormatted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jurnal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        Calendar calendarNow = Calendar.getInstance(TimeZone.getDefault());
        int dayNow = calendarNow.get(Calendar.DAY_OF_MONTH);
        int monthNow = calendarNow.get(Calendar.MONTH) + 1;
        int yearNow = calendarNow.get(Calendar.YEAR);
        tv_months.setText(String.valueOf(monthNow));
        tv_years.setText(String.valueOf(yearNow));

        dayFormatted = String.valueOf(dayNow);
        monthFormatted = String.valueOf(monthNow);
        yearFormatted = String.valueOf(yearNow);

        Calendar startDate = Calendar.getInstance();
        startDate.set(Calendar.DAY_OF_MONTH, 1);

        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        endDate.set(Calendar.DAY_OF_MONTH, 1);
        endDate.add(Calendar.DATE, -1);

        rv_jurnal = findViewById(R.id.rv_jurnal);
        tv_total_kredit = findViewById(R.id.tv_total_kredit);
        tv_total_debit = findViewById(R.id.tv_total_debit);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        layoutNoData = findViewById(R.id.layoutNoData);
        layoutData = findViewById(R.id.layoutData);
        shimmerFrameLayout = findViewById(R.id.shimmerContainer);

        horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
                SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
                SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

                dayFormatted = dayFormat.format(date.getTime());
                monthFormatted = monthFormat.format(date.getTime());
                yearFormatted = yearFormat.format(date.getTime());

                loadAllJurnal(dayFormatted, monthFormatted, yearFormatted);
            }
        });

        jurnal_date = findViewById(R.id.jurnal_date);
        jurnal_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showJurnalDate();
            }
        });

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

//                Button add_jurnal = dialog.findViewById(R.id.add_jkredit);
//                add_jurnal.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        addAnotherJurnal();
//                        dialog.hide();
//                    }
//                });


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
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                status_spinner_1.setAdapter(adapter);
                status_spinner_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#8c8c8c"));
                        ((TextView) parent.getChildAt(0)).setTextSize(16);
                        ((TextView) parent.getChildAt(0)).setGravity(Gravity.END);
                        String selectedItem = String.valueOf(parent.getItemIdAtPosition(position)).toString();
                        if (selectedItem.equals("Debit")) {
                            status_id_1.setText("d");
                        } else if (selectedItem.equals("Kredit")) {
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
//                        validationJurnal();
                        createJurnal();
//                        dialog.hide();
                    }
                });
                dialog.show();
            }
        });

        swipeRefresh.setEnabled(true);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (jurnal_adapter != null) {
                    jurnal_adapter.refreshEvents(jurnalAlls);
                }
                loadAllJurnal(dayFormatted, monthFormatted, yearFormatted);
            }
        });

        loadAllJurnal(dayFormatted, monthFormatted, yearFormatted);
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

    public void showJurnalDate() {
        MonthYearPickerDialog pd = new MonthYearPickerDialog();
        pd.setListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                String monthS = "" + selectedMonth;
                String dayS = "" + selectedDay;
                if (selectedMonth < 10) {
                    monthS = "0" + selectedMonth;
                }
                if (selectedDay < 10) {
                    dayS = "0" + selectedDay;
                }
                String dummyDay = "10";
                String dateSelected = selectedYear + monthS + dummyDay;
                tv_months.setText(monthS);
                tv_years.setText(String.valueOf(selectedYear));
                SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
                Date dateSelectFormat = null;
                try {
                    dateSelectFormat = originalFormat.parse(dateSelected);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Calendar selectedCal = Calendar.getInstance();
                selectedCal.setTime(dateSelectFormat);

                Calendar startDate = Calendar.getInstance();
                startDate.setTime(dateSelectFormat);
                startDate.set(Calendar.DAY_OF_MONTH, 1);

                Calendar endDate = Calendar.getInstance();
                endDate.setTime(dateSelectFormat);
                endDate.add(Calendar.MONTH, 1);
                endDate.set(Calendar.DAY_OF_MONTH, 1);
                endDate.add(Calendar.DATE, -1);

                horizontalCalendar.setRange(startDate, endDate);
                horizontalCalendar.selectDate(startDate, true);
                //loadAllJurnal(String.valueOf(selectedDay), String.valueOf(selectedMonth), String.valueOf(selectedYear));
            }
        });
        pd.show(getFragmentManager(), "MonthYearPickerDialog");
    }

    public void validationJurnal() {
        final SweetAlertDialog vDialog = new SweetAlertDialog(JurnalActivity.this, SweetAlertDialog.WARNING_TYPE);
        vDialog.setTitleText("Apakah data sudah benar?");
        vDialog.setConfirmText("Ya, benar");
        vDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                createJurnal();
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

    public void validationAnotherJurnal() {
        final SweetAlertDialog vDialog = new SweetAlertDialog(JurnalActivity.this, SweetAlertDialog.WARNING_TYPE);
        vDialog.setTitleText("Apakah data sudah benar?");
        vDialog.setConfirmText("Ya, benar");
        vDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                createAnotherJurnal();
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

    public void addAnotherJurnal() {
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
        kwitansi_id = dialog1.findViewById(R.id.id_kwitansi);

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
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status_spinner_2.setAdapter(adapter);
        status_spinner_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#8c8c8c"));
                ((TextView) parent.getChildAt(0)).setTextSize(16);
                ((TextView) parent.getChildAt(0)).setGravity(Gravity.END);
                String selectedItem = String.valueOf(parent.getItemIdAtPosition(position)).toString();
                if (selectedItem.equals("Debit")) {
                    status_id_2.setText("d");
                } else if (selectedItem.equals("Kredit")) {
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

    public void createJurnal() {
        User user = SharedPref.getInstance(this).getBaseUser();
        String token = "Bearer " + user.getToken();

        String date = datePost.getText().toString();
        String kwitansi = tv_kwitansi.getText().toString();
        String ket = tv_keterangan.getText().toString();
        int id_akun = Integer.parseInt(akun_id_1.getText().toString());
        String status = status_id_1.getText().toString();
        String jumlah = tv_jumlah.getText().toString().trim();

        if (jumlah.isEmpty()) {
            tv_jumlah.setError("Jumlah harus diisi");
            tv_jumlah.requestFocus();
            return;
        }

        int jumlahstr = Integer.parseInt(tv_jumlah.getText().toString());

        if (kwitansi.isEmpty()) {
            tv_kwitansi.setError("Kwitansi harus diisi");
            tv_kwitansi.requestFocus();
            return;
        }

        if (ket.isEmpty()) {
            tv_keterangan.setError("Keterangan harus diisi");
            tv_keterangan.requestFocus();
            return;
        }

        Call<JurnalCreateResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .add_jurnal(token, date, id_akun, jumlahstr, status, kwitansi, ket);

        call.enqueue(new Callback<JurnalCreateResponse>() {
            @Override
            public void onResponse(Call<JurnalCreateResponse> call, Response<JurnalCreateResponse> response) {
//                pDialog.dismissWithAnimation();
                dialog.hide();
                JurnalCreateResponse jurnalCreateResponse = response.body();
                if (response.isSuccessful()) {
                    if (jurnalCreateResponse.equals("success")) {
                        int id_kwitansi = jurnalCreateResponse.getResult().getId();
                        kwitansi_id.setText(String.valueOf(id_kwitansi));
                        addAnotherJurnal();
//                        SweetAlertDialog sweet_dialog = new SweetAlertDialog(JurnalActivity.this, SweetAlertDialog.SUCCESS_TYPE);
//                        sweet_dialog.setTitleText("Jurnal debit berhasil ditambahkan");
//                        sweet_dialog.show();
//                        dialog.dismiss();
//                        vDialog.dismiss();
                    }

//                    if (parentAdapter != null) {
//                        parentAdapter.refreshEvents(neracaAwalParentArrayList);
//                    }
//                    loadParent();
                }
            }

            @Override
            public void onFailure(Call<JurnalCreateResponse> call, Throwable t) {
                pDialog.dismissWithAnimation();
                Toast.makeText(JurnalActivity.this, "Kesalahan terjadi, coba beberapa saat lagi.", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void createAnotherJurnal() {
        User user = SharedPref.getInstance(this).getBaseUser();
        String token = "Bearer " + user.getToken();

        String date = datePost.getText().toString();
        String kwitansi = kwitansi_id.getText().toString();
        String ket = tv_keterangan.getText().toString();
        int id_akun = Integer.parseInt(akun_id_2.getText().toString());
        String status = status_id_2.getText().toString();
        String jumlah = tv_jumlah.getText().toString().trim();

        if (jumlah.isEmpty()) {
            tv_jumlah.setError("Jumlah harus diisi");
            tv_jumlah.requestFocus();
            return;
        }

        int jumlahstr = Integer.parseInt(tv_jumlah.getText().toString());

        if (kwitansi.isEmpty()) {
            tv_kwitansi.setError("Kwitansi harus diisi");
            tv_kwitansi.requestFocus();
            return;
        }

        if (ket.isEmpty()) {
            tv_keterangan.setError("Keterangan harus diisi");
            tv_keterangan.requestFocus();
            return;
        }

        Call<JurnalCreateResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .add_another_jurnal(token, date, id_akun, jumlahstr, status, kwitansi);

        call.enqueue(new Callback<JurnalCreateResponse>() {
            @Override
            public void onResponse(Call<JurnalCreateResponse> call, Response<JurnalCreateResponse> response) {
                JurnalCreateResponse jurnalCreateResponse = response.body();
                if (response.isSuccessful()) {
                    if (jurnalCreateResponse.equals("success")) {
                        SweetAlertDialog sweet_dialog = new SweetAlertDialog(JurnalActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                        sweet_dialog.setTitleText("Jurnal berhasil ditambahkan");
                        sweet_dialog.show();
                        dialog.dismiss();
                        vDialog.dismiss();
                    }

//                    if (parentAdapter != null) {
//                        parentAdapter.refreshEvents(neracaAwalParentArrayList);
//                    }
//                    loadParent();
                }
            }

            @Override
            public void onFailure(Call<JurnalCreateResponse> call, Throwable t) {

            }
        });
    }

    public void loadAllJurnal(String day, String month, String year) {
        layoutData.setVisibility(View.GONE);
        layoutNoData.setVisibility(View.GONE);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmer();
        User user = SharedPref.getInstance(this).getBaseUser();
        String token = "Bearer " + user.getToken();

        Call<JurnalResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getJurnal(token, day, month, year);

        call.enqueue(new Callback<JurnalResponse>() {
            @Override
            public void onResponse(Call<JurnalResponse> call, Response<JurnalResponse> response) {
                JurnalResponse jurnalResponse = response.body();
                if (response.isSuccessful()) {
                    if (jurnalResponse.getStatus().equals("success")) {
                        jurnalAlls = jurnalResponse.getJurnalAlls();
                        jurnal_adapter = new Jurnal_Adapter(jurnalAlls);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        rv_jurnal.setLayoutManager(layoutManager);
                        rv_jurnal.setItemAnimator(new DefaultItemAnimator());
                        rv_jurnal.setAdapter(jurnal_adapter);
                        if (layoutManager.getItemCount() == 0) {
                            shimmerFrameLayout.setVisibility(View.GONE);
                            layoutData.setVisibility(View.GONE);
                            layoutNoData.setVisibility(View.VISIBLE);
                        } else {
                            layoutNoData.setVisibility(View.GONE);
                            shimmerFrameLayout.setVisibility(View.GONE);
                            layoutData.setVisibility(View.VISIBLE);
                            tv_total_debit.setText(jurnalResponse.getTotal_debit());
                            tv_total_kredit.setText(jurnalResponse.getTotal_kredit());
                        }
                    }
                }
                shimmerFrameLayout.stopShimmer();
                swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<JurnalResponse> call, Throwable t) {
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                layoutData.setVisibility(View.GONE);
                layoutNoData.setVisibility(View.VISIBLE);
                swipeRefresh.setRefreshing(false);
                Toast.makeText(JurnalActivity.this, "Kesalahan terjadi, coba beberapa saat lagi.", Toast.LENGTH_SHORT).show();
                Log.e("debug", "onFailure : ERROR > " + t.getMessage());

            }
        });
    }
}
