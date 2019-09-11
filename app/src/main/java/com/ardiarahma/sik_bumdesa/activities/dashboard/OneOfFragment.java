package com.ardiarahma.sik_bumdesa.activities.dashboard;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.database.adapters.JurnalAdapter;
import com.ardiarahma.sik_bumdesa.database.models.Jurnal;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneOfFragment extends Fragment {

    List<Jurnal> jurnals;


    public OneOfFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_one_of, container, true);
        final RecyclerView rv = v.findViewById(R.id.rv_jurnal);
        JurnalAdapter adapter = new JurnalAdapter(getContext(), jurnals);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                false);
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(linearLayoutManager);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        jurnals = new ArrayList<>();
        jurnals.add(new Jurnal(2,"Pembayaran Gaji Karyawan", 5110, 1233, "D", "4 September"));
        jurnals.add(new Jurnal(2,"Pembayaran Gaji Karyawan", 5110, 1233, "K", "4 September"));
        jurnals.add(new Jurnal(2,"Sewa Pegawai", 4120, 2345, "D", "4 September"));
        jurnals.add(new Jurnal(2,"Sewa Pegawai", 4120, 2345, "K", "4 September"));
        jurnals.add(new Jurnal(2,"Bayar Listrik", 3120, 3214, "D", "4 September"));
        jurnals.add(new Jurnal(2,"Bayar Listrik", 3120, 3214, "K", "4 September"));

    }
}
