package com.ardiarahma.sik_bumdesa.activities.dashboard;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.activities.MonthYearPickerDialog;
import com.ardiarahma.sik_bumdesa.networks.RetrofitClient;
import com.ardiarahma.sik_bumdesa.networks.SharedPref;
import com.ardiarahma.sik_bumdesa.networks.adapters.BukuBesarAdapter;
import com.ardiarahma.sik_bumdesa.networks.models.BukuBesar;
import com.ardiarahma.sik_bumdesa.networks.models.GetAllAkun;
import com.ardiarahma.sik_bumdesa.networks.models.User;
import com.ardiarahma.sik_bumdesa.networks.models.responses.BukuBesarResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.GetAllAkunResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BukuBesarActivity extends AppCompatActivity {

    ArrayList<GetAllAkun> allAkuns;
    ArrayAdapter<GetAllAkun> allAkunArrayAdapter;
    ArrayList<BukuBesar> allBukuBesar;
    BukuBesarAdapter bukuBesarAdapter;

    ImageButton toolbar_back;

    DatePickerDialog datePickerDialog;
    ImageButton bukubesar_date;
    TextView tv_months, tv_years;
    SimpleDateFormat dateFormat, monthFormat, yearFormat;
    static String dayFormatted, monthFormatted, yearFormatted;

    RecyclerView rv_bukubesar;
    Spinner sp_akun;
    TextView akun_id;
    int accountId;
    SwipeRefreshLayout swipeRefresh;
    LinearLayout layoutData, layoutNoData;
//    ShimmerFrameLayout shimmerFrameLayout;

    TextView tv_totaldebit, tv_totalkredit, tv_saldoawal, tv_saldoakhir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buku_besar);

        toolbar_back = findViewById(R.id.toolbar_back);
        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        sp_akun = findViewById(R.id.sp_account);
        akun_id = findViewById(R.id.account_id);
        akun_id.setText("0");

        rv_bukubesar = findViewById(R.id.rv_bukubesar);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        layoutNoData = findViewById(R.id.layoutNoData);
        layoutData = findViewById(R.id.layoutData);
        tv_months = findViewById(R.id.month);
        tv_years = findViewById(R.id.year);

        monthFormat = new SimpleDateFormat("MM", Locale.US);
        yearFormat = new SimpleDateFormat("yyyy", Locale.US);

        Calendar calendarNow = Calendar.getInstance(TimeZone.getDefault());
        int monthNow = calendarNow.get(Calendar.MONTH) + 1;
        int yearNow = calendarNow.get(Calendar.YEAR);
        tv_months.setText(String.valueOf(monthNow));
        tv_years.setText(String.valueOf(yearNow));

        sp_akun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#8c8c8c"));
                ((TextView) parent.getChildAt(0)).setTextSize(16);
                ((TextView) parent.getChildAt(0)).setGravity(Gravity.END);
                GetAllAkun AllAkun = (GetAllAkun) parent.getSelectedItem();
                akun_id.setText(String.valueOf(AllAkun.getAkunId()));
                loadList();
//                accountId = AllAkun.getAkunId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        swipeRefresh.setEnabled(true);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (bukuBesarAdapter != null) {
                    bukuBesarAdapter.refreshEvents(allBukuBesar);
                }
                loadList();
            }
        });

        bukubesar_date = findViewById(R.id.buku_date);
        bukubesar_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBukuDate();
            }
        });

        tv_saldoawal = findViewById(R.id.tv_saldoAwal);
        tv_saldoakhir = findViewById(R.id.tv_saldoAkhir);
        tv_totaldebit = findViewById(R.id.tv_totaldebit);
        tv_totalkredit = findViewById(R.id.tv_totalkredit);

        loadAkun();
        loadList();
//        loadList(monthFormatted, yearFormatted);
    }

    public void showBukuDate() {
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
                loadList();
            }
        });
        pd.show(getFragmentManager(), "MonthYearPickerDialog");
    }

    public void loadAkun() {
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
                        allAkuns = getAllAkunResponse.getNeracaAwalAllAkuns();
                        allAkunArrayAdapter = new ArrayAdapter<>(BukuBesarActivity.this, android.R.layout.simple_spinner_item, allAkuns);
                        allAkunArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp_akun.setAdapter(allAkunArrayAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllAkunResponse> call, Throwable t) {
//                pDialog.dismissWithAnimation();
                Toast.makeText(BukuBesarActivity.this, "Kesalahan terjadi, coba beberapa saat lagi.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadList() {
        layoutData.setVisibility(View.GONE);
        layoutNoData.setVisibility(View.GONE);
//        shimmerFrameLayout.setVisibility(View.VISIBLE);
//        shimmerFrameLayout.startShimmer();

        User user = SharedPref.getInstance(this).getBaseUser();
        String token = "Bearer " + user.getToken();

        int month = Integer.parseInt(tv_months.getText().toString());
        int year = Integer.parseInt(tv_years.getText().toString());
        int account_id = Integer.parseInt(akun_id.getText().toString());
//        String month = tv_months.getText().toString();
//        String year = tv_years.getText().toString();

        Call<BukuBesarResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getBukuBesar(token, month, year, account_id);

        call.enqueue(new Callback<BukuBesarResponse>() {
            @Override
            public void onResponse(Call<BukuBesarResponse> call, Response<BukuBesarResponse> response) {
                BukuBesarResponse bukuBesarResponse = response.body();
                if (response.isSuccessful()) {
                    if (bukuBesarResponse.getStatus().equals("success")) {
                        allBukuBesar = bukuBesarResponse.getBuku_besar();
                        if (bukuBesarResponse.getBuku_besar().get(0).getSaldo_awal() != null) {
                            bukuBesarAdapter = new BukuBesarAdapter(allBukuBesar);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            rv_bukubesar.setLayoutManager(layoutManager);
                            rv_bukubesar.setItemAnimator(new DefaultItemAnimator());
                            rv_bukubesar.setAdapter(bukuBesarAdapter);

//                            ArrayList<String> strArray = new ArrayList<>();
//
//                            String tess = response.body().toString();
//                            JSONObject jsonObject = null;
//                            try {
//                                jsonObject = new JSONObject(tess.trim());
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                            try {
//                                JSONArray jsonEmailArray = jsonObject.getJSONArray("buku_besar");
//                                for(int i=0; i<=jsonEmailArray.length(); i++){
//                                    strArray.add(jsonEmailArray.getString(i));
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                            StringBuilder builder = new StringBuilder();
//                            for(String s : strArray) {
//                                builder.append(s);
//                            }
//                            String str = builder.toString();
//
//                            Log.e("debug", str);

                            if (layoutManager.getItemCount() == 0) {
//                            shimmerFrameLayout.setVisibility(View.GONE);
                                layoutData.setVisibility(View.GONE);
                                layoutNoData.setVisibility(View.VISIBLE);
                            } else {
                                layoutNoData.setVisibility(View.GONE);
//                            shimmerFrameLayout.setVisibility(View.GONE);
                                layoutData.setVisibility(View.VISIBLE);
//                            tv_saldoawal.setText(bukuBesarResponse.getBuku_besar().get(allBukuBesar.size()-1).getSaldo_awal());
                                if (bukuBesarResponse.getSaldo_awal() != null) {
                                    tv_saldoawal.setText(bukuBesarResponse.getSaldo_awal().getJumlah());
                                } else {
                                    tv_saldoawal.setText("0");
                                }
                                tv_saldoakhir.setText(String.valueOf(bukuBesarResponse.getBuku_besar().get(allBukuBesar.size() - 1).getSaldo()));
                                tv_totaldebit.setText(String.valueOf(bukuBesarResponse.getTotal_debit()));
                                tv_totalkredit.setText(String.valueOf(bukuBesarResponse.getTotal_kredit()));
                            }
                        } else {
                            layoutData.setVisibility(View.GONE);
                            layoutNoData.setVisibility(View.VISIBLE);
                        }
                    }
                }
//                shimmerFrameLayout.stopShimmer();
                swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<BukuBesarResponse> call, Throwable t) {
                swipeRefresh.setRefreshing(false);
                Toast.makeText(BukuBesarActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
