package com.ardiarahma.sik_bumdesa.activities.dashboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.ardiarahma.sik_bumdesa.R;

public class BukuBesarActivity extends AppCompatActivity {

    ImageButton toolbar_back;

    Spinner sp_akun;
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
        setContentView(R.layout.activity_buku_besar);

        toolbar_back = findViewById(R.id.toolbar_back);
        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        sp_akun = findViewById(R.id.sp_account);
        ArrayAdapter<String> akun_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, akun);
        akun_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp_akun.setAdapter(akun_adapter);
    }
}
