package com.ardiarahma.sik_bumdesa.activities.dashboard;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.activities.MonthYearPickerDialog;
import com.ardiarahma.sik_bumdesa.networks.RetrofitClient;
import com.ardiarahma.sik_bumdesa.networks.SharedPref;
import com.ardiarahma.sik_bumdesa.networks.adapters.NeracaUmum_AsetLancarAdapter;
import com.ardiarahma.sik_bumdesa.networks.adapters.NeracaUmum_AsetTetapAdapter;
import com.ardiarahma.sik_bumdesa.networks.adapters.NeracaUmum_LiabilitasLancarAdapter;
import com.ardiarahma.sik_bumdesa.networks.models.NeracaUmum_AsetLancar;
import com.ardiarahma.sik_bumdesa.networks.models.NeracaUmum_AsetTetap;
import com.ardiarahma.sik_bumdesa.networks.models.NeracaUmum_LiabilitasJangkaPanjang;
import com.ardiarahma.sik_bumdesa.networks.models.NeracaUmum_LiabilitasLancar;
import com.ardiarahma.sik_bumdesa.networks.models.User;
import com.ardiarahma.sik_bumdesa.networks.models.responses.NeracaResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

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
    TextView tv_jumlahModal, tv_jumlahSaldoDitahan, tv_jumlahSaldoBerjalan, tv_nilaiLiabilitasJP;
    TextView tv_totalAset, tv_totalLiabilitas, tv_totalEkuitas, tv_totalLiaEku;
    RecyclerView rv_asetLancar, rv_asetTetap, rv_liabilitasLancar;

    ArrayList<NeracaUmum_AsetLancar> neracaUmum_asetLancars;
    ArrayList<NeracaUmum_AsetTetap> neracaUmum_asetTetaps;
    ArrayList<NeracaUmum_LiabilitasLancar> neracaUmum_liabilitasLancars;
    ArrayList<NeracaUmum_LiabilitasJangkaPanjang> neracaUmum_liabilitasJangkaPanjangs;

    NeracaUmum_AsetLancarAdapter asetLancarAdapter;
    NeracaUmum_AsetTetapAdapter asetTetapAdapter;
    NeracaUmum_LiabilitasLancarAdapter liabilitasLancarAdapter;



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

        tv_totalAsetLancar = findViewById(R.id.total_aset_lancar);
        tv_totalAsetTetap = findViewById(R.id.total_aset_tetap);
        tv_totalLiabilitasLancar = findViewById(R.id.total_liabilitas_lancar);
        tv_totalLiabilitasJP = findViewById(R.id.total_liabilitas_jangkapanjang);

        rv_asetLancar = findViewById(R.id.rv_aset_lancar);
        rv_asetTetap = findViewById(R.id.rv_aset_tetap);
        rv_liabilitasLancar = findViewById(R.id.rv_labilitaslancar);

        tv_nilaiLiabilitasJP = findViewById(R.id.nilai_liabilitas);
        tv_jumlahModal = findViewById(R.id.jumlah_modalAwal);
        tv_jumlahSaldoBerjalan = findViewById(R.id.jumlah_saldoBerjalan);
        tv_jumlahSaldoDitahan = findViewById(R.id.jumlah_saldoDitahan);

        tv_totalEkuitas = findViewById(R.id.total_ekuitas);
        tv_totalAset = findViewById(R.id.total_aset);
        tv_totalLiabilitas = findViewById(R.id.total_liabilitas);
        tv_totalLiaEku = findViewById(R.id.total_liabilitasekuitas);

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
                loadNeracaUmum();
            }
        });
        pd.show(getFragmentManager(), "MonthYearPickerDialog");
    }

     public void loadNeracaUmum(){
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

                         tv_nilaiLiabilitasJP.setText(String.valueOf(neracaResponse.getNeracaUmumLiabilitasJangkaPanjang().getNilai_akun()));
                         tv_jumlahModal.setText(String.valueOf(neracaResponse.getNeracaUmumNeracaEkuitas().getEkuitas_modal()));
                         tv_jumlahSaldoDitahan.setText(String.valueOf(neracaResponse.getNeracaUmumNeracaEkuitas().getEkuitas_saldoDitahan()));
                         tv_jumlahSaldoBerjalan.setText(String.valueOf(neracaResponse.getNeracaUmumNeracaEkuitas().getEkuitas_saldojalan()));

                     }
                 }
             }

             @Override
             public void onFailure(Call<NeracaResponse> call, Throwable t) {
                 Toast.makeText(NeracaActivity.this, "Kesalahan terjadi, coba beberapa saat lagi.", Toast.LENGTH_SHORT).show();

             }
         });
     }
}
