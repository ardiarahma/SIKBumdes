package com.ardiarahma.sik_bumdesa.activities.navigation_drawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.database.adapters.Neraca_AsetTetap;
import com.ardiarahma.sik_bumdesa.database.models.Aset;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class BalanceFragment extends Fragment {

    Spinner sp_months, sp_years;
    public static final String[] months = new String[]{
            "Januari", "Februari", "Maret", "April", "Mei", "Juni",
            "Juli", "Agustus", "September", "Oktober", "November", "Desember"
    };

    RecyclerView rv_aset_tetap, rv_aset_lancar, rv_utang_lancar, rv_utang_jp, rv_ekuitas, rv_pendapatan, rv_pendapatan_2, rv_biaya, rv_biaya_2;
    Neraca_AsetTetap adapter;
    ArrayList<Aset> asets;

    public BalanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_balance, container, false);

        sp_months = v.findViewById(R.id.sp_month);
        sp_years = v.findViewById(R.id.sp_year);

        //set year
        ArrayList<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1900; i <= thisYear; i++){
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, years);
        sp_years.setAdapter(adapter_year);

        //set months
        ArrayAdapter<String> adapter_month = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, months);
        sp_months.setAdapter(adapter_month);


        rv_aset_lancar = v.findViewById(R.id.rv_aset_lancar);
        rv_aset_tetap = v.findViewById(R.id.rv_aset_tetap);
        rv_utang_lancar = v.findViewById(R.id.rv_utang_lancar);
        rv_utang_jp = v.findViewById(R.id.rv_utang_jp);
        rv_ekuitas = v.findViewById(R.id.rv_ekuitas);
        rv_biaya = v.findViewById(R.id.rv_biaya);
        rv_biaya_2 = v.findViewById(R.id.rv_biaya_2);
        rv_pendapatan = v.findViewById(R.id.rv_pendapatan);
        rv_pendapatan_2 = v.findViewById(R.id.rv_pendapatan_2);

        adapter = new Neraca_AsetTetap(getActivity(), asets);

        rv_aset_tetap.setAdapter(adapter);
        rv_aset_lancar.setAdapter(adapter);
        rv_utang_lancar.setAdapter(adapter);
        rv_utang_jp.setAdapter(adapter);
        rv_ekuitas.setAdapter(adapter);
        rv_pendapatan.setAdapter(adapter);
        rv_biaya.setAdapter(adapter);
        rv_pendapatan_2.setAdapter(adapter);
        rv_biaya_2.setAdapter(adapter);

        rv_aset_lancar.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv_aset_tetap.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv_utang_lancar.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv_utang_jp.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv_ekuitas.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv_pendapatan.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv_biaya.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv_pendapatan_2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv_biaya_2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter.notifyDataSetChanged();





        return v;
    }

}
