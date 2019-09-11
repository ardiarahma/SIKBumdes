package com.ardiarahma.sik_bumdesa.activities.navigation_drawer;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.database.adapters.Neraca_AsetLancarAdapter;
import com.ardiarahma.sik_bumdesa.database.adapters.Neraca_AsetTetapAdapter;
import com.ardiarahma.sik_bumdesa.database.models.Neraca_AsetLancar;
import com.ardiarahma.sik_bumdesa.database.models.Neraca_AsetTetap;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class BalanceFragment extends Fragment{

    TextView date;
    ImageButton datepicker;
    EditText jumlah_balance;
    Spinner sp_months, sp_years, sp_account;
    public static final String[] months = new String[]{
            "Januari", "Februari", "Maret", "April", "Mei", "Juni",
            "Juli", "Agustus", "September", "Oktober", "November", "Desember"
    };

    private int mYear, mMonth, mDay;

    FloatingActionButton fab1;

    RecyclerView rv_aset_tetap, rv_aset_lancar, rv_utang_lancar, rv_utang_jp, rv_ekuitas,
            rv_pendapatan, rv_pendapatan_2, rv_biaya, rv_biaya_2;
    Neraca_AsetTetapAdapter adapter_asetTetap;
    Neraca_AsetLancarAdapter adapter_asetLancar;

    ArrayList<Neraca_AsetTetap> neracaAsetTetaps;
    ArrayList<Neraca_AsetLancar> neracaAsetLancars;

    Dialog dialog;
    SweetAlertDialog vDialog;

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

        sp_account = v.findViewById(R.id.nama_akun);
        date = v.findViewById(R.id.date);
        datepicker = v.findViewById(R.id.date_btn);
        jumlah_balance = v.findViewById(R.id.jumlah_balance);

        fab1 = v.findViewById(R.id.fab_1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.add_balance);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                datepicker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                        datePickerDialog.show();
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
                        dialog.hide();
                        validationAccount();
                    }
                });
                dialog.show();
            }
        });

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
        adapter_asetLancar = new Neraca_AsetLancarAdapter(getContext(), neracaAsetLancars);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                false);
        rv_aset_lancar.setAdapter(adapter_asetLancar);
        rv_aset_lancar.setHasFixedSize(true);
        rv_aset_lancar.setLayoutManager(linearLayoutManager);
        adapter_asetLancar.notifyDataSetChanged();

        rv_aset_tetap = v.findViewById(R.id.rv_aset_tetap);
        adapter_asetTetap= new Neraca_AsetTetapAdapter(getContext(), neracaAsetTetaps);
        LinearLayoutManager linearLayoutManager_1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                false);
        rv_aset_tetap.setAdapter(adapter_asetTetap);
        rv_aset_tetap.setHasFixedSize(true);
        rv_aset_tetap.setLayoutManager(linearLayoutManager_1);
        adapter_asetTetap.notifyDataSetChanged();


        rv_utang_lancar = v.findViewById(R.id.rv_utang_lancar);
        rv_utang_jp = v.findViewById(R.id.rv_utang_jp);
        rv_ekuitas = v.findViewById(R.id.rv_ekuitas);
        rv_biaya = v.findViewById(R.id.rv_biaya);
        rv_biaya_2 = v.findViewById(R.id.rv_biaya_2);
        rv_pendapatan = v.findViewById(R.id.rv_pendapatan);
        rv_pendapatan_2 = v.findViewById(R.id.rv_pendapatan_2);







        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        neracaAsetLancars = new ArrayList<>();
        neracaAsetLancars.add(new Neraca_AsetLancar("Kas", 5000000));
        neracaAsetLancars.add(new Neraca_AsetLancar("Kas di Bank", 3000000));
        neracaAsetTetaps = new ArrayList<>();
        neracaAsetTetaps.add(new Neraca_AsetTetap("Tanah", 5000000));
        neracaAsetTetaps.add(new Neraca_AsetTetap("Gedung", 3000000));
//
//        ningrum = new ArrayList<>();
//        ningrum.add(new Ningrum("", 4566))


    }

    public void validationAccount(){
        final SweetAlertDialog vDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE);
        vDialog.setTitleText("Apakah data sudah benar?");
        vDialog.setConfirmText("Ya, benar");
        vDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                SweetAlertDialog sweet_dialog = new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
                sweet_dialog.setTitleText("Klasifikasi akun berhasil ditambahkan");
                sweet_dialog.show();
                dialog.dismiss();
            }
        }).show();
    }
}
