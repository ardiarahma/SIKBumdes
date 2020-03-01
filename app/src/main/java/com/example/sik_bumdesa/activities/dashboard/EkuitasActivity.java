package com.example.sik_bumdesa.activities.dashboard;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sik_bumdesa.R;
import com.example.sik_bumdesa.activities.MonthYearPickerDialog;
import com.example.sik_bumdesa.networks.RetrofitClient;
import com.example.sik_bumdesa.networks.SharedPref;
import com.example.sik_bumdesa.networks.models.User;
import com.example.sik_bumdesa.networks.models.responses.EkuitasResponse;

import java.text.SimpleDateFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EkuitasActivity extends AppCompatActivity {

    TextView nilai_modal, nilai_saldoDitahan, nilai_saldoBerjalan, total_sum;

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

        nilai_modal = findViewById(R.id.jumlah_modalAwal);
        nilai_saldoDitahan = findViewById(R.id.jumlah_saldoDitahan);
        nilai_saldoBerjalan = findViewById(R.id.jumlah_saldoBerjalan);
        total_sum = findViewById(R.id.total_sum);

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
        MonthYearPickerDialog pd = new MonthYearPickerDialog();
        pd.setListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

                tv_months.setText(String.valueOf(selectedMonth));
                tv_years.setText(String.valueOf(selectedYear));
                loadEkuitas();
            }
        });
        pd.show(getFragmentManager(), "MonthYearPickerDialog");
    }

    public void loadEkuitas(){
        User user = SharedPref.getInstance(this).getBaseUser();
        String token = "Bearer " + user.getToken();
        int month = Integer.parseInt(tv_months.getText().toString());
        int year = Integer.parseInt(tv_years.getText().toString());

        Call<EkuitasResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .ekuitas(token, month, year);

        call.enqueue(new Callback<EkuitasResponse>() {
            @Override
            public void onResponse(Call<EkuitasResponse> call, Response<EkuitasResponse> response) {
                EkuitasResponse ekuitasResponse = response.body();
                Log.d("TAG", "Response " + response.body());
                if (response.isSuccessful()){
                    if (ekuitasResponse.getStatus().equals("success")){
                        nilai_modal.setText(String.valueOf(ekuitasResponse.getEkuitas_modals().getNilai_akun()));
//                        nilai_saldoDitahan.setText(String.valueOf(ekuitasResponse.getEkuitas_saldos().getSaldo_ditahan().getNilai_akun()));
                        nilai_saldoBerjalan.setText(String.valueOf(ekuitasResponse.getEkuitas_saldos().getSaldo_berjalan()));
                        total_sum.setText(String.valueOf(ekuitasResponse.getTotalEkuitas()));
                    }
                }
            }

            @Override
            public void onFailure(Call<EkuitasResponse> call, Throwable t) {

            }
        });
    }
}
