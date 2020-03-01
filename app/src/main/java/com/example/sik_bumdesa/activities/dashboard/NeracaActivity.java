package com.example.sik_bumdesa.activities.dashboard;

import android.app.DatePickerDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sik_bumdesa.R;
import com.example.sik_bumdesa.activities.MonthYearPickerDialog;
import com.example.sik_bumdesa.networks.RetrofitClient;
import com.example.sik_bumdesa.networks.SharedPref;
import com.example.sik_bumdesa.networks.adapters.NeracaUmum_AsetLancarAdapter;
import com.example.sik_bumdesa.networks.adapters.NeracaUmum_AsetTetapAdapter;
import com.example.sik_bumdesa.networks.adapters.NeracaUmum_LiabilitasJangkaPanjangAdapter;
import com.example.sik_bumdesa.networks.adapters.NeracaUmum_LiabilitasLancarAdapter;
import com.example.sik_bumdesa.networks.models.NeracaUmum_AsetLancar;
import com.example.sik_bumdesa.networks.models.NeracaUmum_AsetTetap;
import com.example.sik_bumdesa.networks.models.NeracaUmum_LiabilitasJangkaPanjang;
import com.example.sik_bumdesa.networks.models.NeracaUmum_LiabilitasLancar;
import com.example.sik_bumdesa.networks.models.User;
import com.example.sik_bumdesa.networks.models.responses.NeracaResponse;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NeracaActivity extends AppCompatActivity {

    ImageButton toolbar_back;

    DatePickerDialog datePickerDialog;
    ImageButton date_btn;
    SimpleDateFormat monthFormat, yearFormat;
    TextView tv_months, tv_years;

    TextView tv_totalAsetLancar, tv_totalAsetTetap, tv_totalLiabilitasLancar, tv_totalLiabilitasJP;
    TextView tv_jumlahModal, tv_jumlahSaldoDitahan, tv_jumlahSaldoBerjalan;
    TextView tv_totalAset, tv_totalLiabilitas, tv_totalEkuitas, tv_totalLiaEku;
    RecyclerView rv_asetLancar, rv_asetTetap, rv_liabilitasLancar, rv_liabilitasJP;

    ArrayList<NeracaUmum_AsetLancar> neracaUmum_asetLancars;
    ArrayList<NeracaUmum_AsetTetap> neracaUmum_asetTetaps;
    ArrayList<NeracaUmum_LiabilitasLancar> neracaUmum_liabilitasLancars;
    ArrayList<NeracaUmum_LiabilitasJangkaPanjang> neracaUmum_liabilitasJangkaPanjangs;

    NeracaUmum_AsetLancarAdapter asetLancarAdapter;
    NeracaUmum_AsetTetapAdapter asetTetapAdapter;
    NeracaUmum_LiabilitasLancarAdapter liabilitasLancarAdapter;
    NeracaUmum_LiabilitasJangkaPanjangAdapter liabilitasJangkaPanjangAdapter;

    SwipeRefreshLayout swipeRefresh;
    LinearLayout layoutData;
    ShimmerFrameLayout shimmerFrameLayout;

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

        Calendar calendarNow = Calendar.getInstance(TimeZone.getDefault());
        int monthNow = calendarNow.get(Calendar.MONTH) + 1;
        int yearNow = calendarNow.get(Calendar.YEAR);
        tv_months.setText(String.valueOf(monthNow));
        tv_years.setText(String.valueOf(yearNow));

        date_btn = findViewById(R.id.date_btn);
        date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });

        tv_totalAsetLancar = findViewById(R.id.total_aset_lancar);
        tv_totalAsetTetap = findViewById(R.id.total_aset_tetap);
        tv_totalLiabilitasLancar = findViewById(R.id.total_liabilitas_lancar);
        tv_totalLiabilitasJP = findViewById(R.id.total_liabilitas_jangkapanjang);

        rv_asetLancar = findViewById(R.id.rv_aset_lancar);
        rv_asetTetap = findViewById(R.id.rv_aset_tetap);
        rv_liabilitasLancar = findViewById(R.id.rv_labilitaslancar);
        rv_liabilitasJP= findViewById(R.id.rv_liabilitasJP);

        tv_jumlahModal = findViewById(R.id.jumlah_modalAwal);
        tv_jumlahSaldoBerjalan = findViewById(R.id.jumlah_saldoBerjalan);
        tv_jumlahSaldoDitahan = findViewById(R.id.jumlah_saldoDitahan);

        tv_totalEkuitas = findViewById(R.id.total_ekuitas);
        tv_totalAset = findViewById(R.id.total_aset);
        tv_totalLiabilitas = findViewById(R.id.total_liabilitas);
        tv_totalLiaEku = findViewById(R.id.total_liabilitasekuitas);

        swipeRefresh = findViewById(R.id.swipeRefresh);
        layoutData = findViewById(R.id.layoutData);
        shimmerFrameLayout = findViewById(R.id.shimmerContainer);

        swipeRefresh.setEnabled(true);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNeracaUmum();
            }
        });

        loadNeracaUmum();
    }

    public void showDate(){
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
                tv_months.setText(monthS);
                tv_years.setText(String.valueOf(selectedYear));
                loadNeracaUmum();
            }
        });
        pd.show(getFragmentManager(), "MonthYearPickerDialog");
    }

     public void loadNeracaUmum(){
         layoutData.setVisibility(View.GONE);
         shimmerFrameLayout.setVisibility(View.VISIBLE);
         shimmerFrameLayout.startShimmer();
         User user = SharedPref.getInstance(this).getBaseUser();
         String token = "Bearer " + user.getToken();
         int month = Integer.parseInt(tv_months.getText().toString());
         int year = Integer.parseInt(tv_years.getText().toString());

         Call<NeracaResponse> call = RetrofitClient
                 .getInstance()
                 .getApi()
                 .neraca(token, month, year);

         call.enqueue(new Callback<NeracaResponse>() {
             @Override
             public void onResponse(Call<NeracaResponse> call, Response<NeracaResponse> response) {
                 NeracaResponse neracaResponse = response.body();
                 shimmerFrameLayout.setVisibility(View.GONE);
                 layoutData.setVisibility(View.VISIBLE);
                 if (response.isSuccessful()){
                     if (neracaResponse.getStatus().equals("success")){
                         tv_totalAsetLancar.setText(String.valueOf(neracaResponse.getTotalAsetLancar()));
                         tv_totalAsetTetap.setText(String.valueOf(neracaResponse.getTotalAsetTetap()));
                         tv_totalLiabilitasLancar.setText(String.valueOf(neracaResponse.getTotalLiabilitas_lancar()));
                         tv_totalLiabilitasJP.setText(String.valueOf(neracaResponse.getTotalLiabilitas_JP()));
                         tv_totalEkuitas.setText(String.valueOf(neracaResponse.getTotalEkuitas()));
                         tv_totalAset.setText(String.valueOf(neracaResponse.getTotalAset()));
                         tv_totalLiabilitas.setText(String.valueOf(neracaResponse.getTotalLiabilitas()));
                         tv_totalLiaEku.setText(String.valueOf(neracaResponse.getTotalLiaEku()));

                         neracaUmum_asetLancars = neracaResponse.getNeracaUmum_asetLancars();
                         asetLancarAdapter = new NeracaUmum_AsetLancarAdapter(NeracaActivity.this, neracaUmum_asetLancars);
                         RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext());
                         rv_asetLancar.setLayoutManager(layoutManager1);
                         rv_asetLancar.setItemAnimator(new DefaultItemAnimator());
                         rv_asetLancar.setAdapter(asetLancarAdapter);

                         neracaUmum_asetTetaps = neracaResponse.getNeracaUmum_asetTetaps();
                         asetTetapAdapter = new NeracaUmum_AsetTetapAdapter(NeracaActivity.this, neracaUmum_asetTetaps);
                         RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getApplicationContext());
                         rv_asetTetap.setLayoutManager(layoutManager2);
                         rv_asetTetap.setItemAnimator(new DefaultItemAnimator());
                         rv_asetTetap.setAdapter(asetTetapAdapter);

                         neracaUmum_liabilitasLancars = neracaResponse.getNeracaUmum_liabilitasLancars();
                         liabilitasLancarAdapter = new NeracaUmum_LiabilitasLancarAdapter(NeracaActivity.this, neracaUmum_liabilitasLancars);
                         RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(getApplicationContext());
                         rv_liabilitasLancar.setLayoutManager(layoutManager3);
                         rv_liabilitasLancar.setItemAnimator(new DefaultItemAnimator());
                         rv_liabilitasLancar.setAdapter(liabilitasLancarAdapter);

                         neracaUmum_liabilitasJangkaPanjangs = neracaResponse.getNeracaUmum_liabilitasJangkaPanjangs();
                         liabilitasJangkaPanjangAdapter = new NeracaUmum_LiabilitasJangkaPanjangAdapter(NeracaActivity.this, neracaUmum_liabilitasJangkaPanjangs);
                         RecyclerView.LayoutManager layoutManager4 = new LinearLayoutManager(getApplicationContext());
                         rv_liabilitasJP.setLayoutManager(layoutManager4);
                         rv_liabilitasJP.setItemAnimator(new DefaultItemAnimator());
                         rv_liabilitasJP.setAdapter(liabilitasLancarAdapter);

                         tv_jumlahModal.setText(String.valueOf(neracaResponse.getNeracaUmumNeracaEkuitas().getEkuitas_modal().getNilai_akun()));
                         tv_jumlahSaldoDitahan.setText(String.valueOf(neracaResponse.getNeracaUmumNeracaEkuitas().getEkuitas_saldoDitahan().getNilai_akun()));
                         tv_jumlahSaldoBerjalan.setText(String.valueOf(neracaResponse.getNeracaUmumNeracaEkuitas().getEkuitas_saldojalan()));

                     }
                 }
                 shimmerFrameLayout.stopShimmer();
                 swipeRefresh.setRefreshing(false);
             }

             @Override
             public void onFailure(Call<NeracaResponse> call, Throwable t) {
                 shimmerFrameLayout.stopShimmer();
                 shimmerFrameLayout.setVisibility(View.GONE);
                 layoutData.setVisibility(View.GONE);
                 swipeRefresh.setRefreshing(false);
                 Toast.makeText(NeracaActivity.this, "Kesalahan terjadi, coba beberapa saat lagi.", Toast.LENGTH_SHORT).show();
                 Log.e("debug", "onFailure : ERROR > " + t.getMessage());

             }
         });
     }
}
