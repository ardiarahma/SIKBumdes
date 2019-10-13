package com.ardiarahma.sik_bumdesa.activities.navigation_drawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.adapters.AkunAdapter;
import com.ardiarahma.sik_bumdesa.networks.adapters.KlasifikasiAkunAdapter;
import com.ardiarahma.sik_bumdesa.networks.models.AkunExp;
import com.ardiarahma.sik_bumdesa.networks.models.KlasifikasiAkun;

import java.util.ArrayList;
import java.util.List;

public class AccountClassificationActivity extends AppCompatActivity {

    RecyclerView list;
    KlasifikasiAkunAdapter adapter;
    private List<KlasifikasiAkun> klasifikasiAkuns;
    ImageButton toolbar_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_classification);

        toolbar_back = findViewById(R.id.toolbar_back);
        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        getKlasifikasi();
        adapter = new KlasifikasiAkunAdapter(klasifikasiAkuns);
        list.setLayoutManager(new LinearLayoutManager(AccountClassificationActivity.this));
        list.setAdapter(adapter);
//        ArrayList<KlasifikasiAkun> klasifikasiAkuns = new ArrayList<>();
//
//        ArrayList<AkunExp> akunExps = new ArrayList<>();
//        akunExps.add(new AkunExp("Kas"));
//        akunExps.add(new AkunExp("Kas di bank"));
//        akunExps.add(new AkunExp("Piutang Dagang"));
//
//        KlasifikasiAkun asetLancar = new KlasifikasiAkun("Aset Lancar", akunExps);
//        klasifikasiAkuns.add(asetLancar);
//
//        AkunAdapter adapter = new AkunAdapter(klasifikasiAkuns);
//        list.setAdapter(adapter);

    }

    public void getKlasifikasi() {
        klasifikasiAkuns = new ArrayList<>(6);
        for (int i = 0; i < 6; i++){
            List<AkunExp> akunExps = new ArrayList<>(3);
            akunExps.add(new AkunExp("Kas"));
            akunExps.add(new AkunExp("Kas di Bank"));
            akunExps.add(new AkunExp("Piutang Dagang"));
            klasifikasiAkuns.add(new KlasifikasiAkun("Aset Lancar" + 1, akunExps));
        }
    }
}
