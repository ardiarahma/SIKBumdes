package com.ardiarahma.sik_bumdesa.activities.navigation_drawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.models.AkunExp;
import com.ardiarahma.sik_bumdesa.networks.models.KlasifikasiAkun;

import java.util.ArrayList;

public class AccountClassificationActivity extends AppCompatActivity {

    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_classification);

        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<KlasifikasiAkun> klasifikasiAkuns = new ArrayList<>();

        ArrayList<AkunExp> akunExps = new ArrayList<>();
        akunExps.add(new AkunExp("Kas"));
        akunExps.add(new AkunExp("Kas di bank"));
        akunExps.add(new AkunExp("Piutang Dagang"));

        KlasifikasiAkun asetLancar = new KlasifikasiAkun("Aset Lancar", akunExps);
    }
}
