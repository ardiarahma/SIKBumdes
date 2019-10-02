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
import com.ardiarahma.sik_bumdesa.networks.adapters.Neraca_AsetLancarAdapter;
import com.ardiarahma.sik_bumdesa.networks.adapters.Neraca_AsetTetapAdapter;
import com.ardiarahma.sik_bumdesa.networks.models.Neraca_AsetLancar;
import com.ardiarahma.sik_bumdesa.networks.models.Neraca_AsetTetap;
import com.ardiarahma.sik_bumdesa.networks.models.Neraca_UtangLancar;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnggaranFragment extends Fragment{

    TextView date;
    ImageButton datepicker;
    EditText jumlah_balance;
    Spinner sp_account;
    public static final String[] months = new String[]{
            "Januari", "Februari", "Maret", "April", "Mei", "Juni",
            "Juli", "Agustus", "September", "Oktober", "November", "Desember"
    };

    public static final String[] akun = new String[]{
            "Kas", "Kas di Bank", "Piutang Dagang", " Sewa Dibayar Dimuka", "Aset Lainnya", "Utang Dagang",
            "Utang Gaji", "Utang Bank", "Obligasi", "Modal Disetor", "Saldo Laba Ditahan", "Saldo Laba Tahun Berjalan",
            "Pendapatan Wisata", "Pendapatan Homestay", "Pendapatan Resto", "Pendapatan Event", "Biaya Gaji", "Biaya Listrik, Air, dan Telepon",
            "Biaya Administrasi dan Umum", "Biaya Pemasaran", "Biaya Perlengkapan Kantor", "Biaya Sewa", "Biaya Asuransi", "Biaya Penyusutan Gedung",
            "Biaya Penyusutan Kendaraan", "Biaya Penyusutan Peralatan Kantor", "Pendapatan Lain-lain",
            "Biaya Lain-lain"
    };

    private int mYear, mMonth, mDay;

    FloatingActionButton fab1;

    RecyclerView rv_aset_tetap, rv_aset_lancar, rv_utang_lancar, rv_utang_jp, rv_ekuitas,
            rv_pendapatan, rv_pendapatan_2, rv_biaya, rv_biaya_2;
    Neraca_AsetTetapAdapter adapter_asetTetap;
    Neraca_AsetLancarAdapter adapter_asetLancar;

    ArrayList<Neraca_AsetTetap> neracaAsetTetaps;
    ArrayList<Neraca_AsetLancar> neracaAsetLancars;
    ArrayList<Neraca_UtangLancar> utangLancars;

    Dialog dialog;
    SweetAlertDialog vDialog;

    DatePickerDialog datePickerDialog;
    ImageButton balance_date;
    TextView tv_months, tv_years;
    SimpleDateFormat dateFormat, monthFormat, yearFormat;

    public AnggaranFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_balance, container, false);

//        sp_months = v.findViewById(R.id.sp_month);
//        sp_years = v.findViewById(R.id.sp_year);

        balance_date = v.findViewById(R.id.balance_date);
        balance_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBalanceDate();
            }
        });

        tv_months = v.findViewById(R.id.month);
        tv_years = v.findViewById(R.id.year);


        fab1 = v.findViewById(R.id.fab_1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.add_balance);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                sp_account = dialog.findViewById(R.id.nama_akun);
                date = dialog.findViewById(R.id.date);
                datepicker = dialog.findViewById(R.id.date_btn);
                jumlah_balance = dialog.findViewById(R.id.jumlah_balance);

                ArrayAdapter<String> account_adapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, akun);
                account_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                sp_account.setAdapter(account_adapter);

                dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

                datepicker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDatePicker();
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
//        ArrayList<String> years = new ArrayList<String>();
//        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
//        for (int i = 1900; i <= thisYear; i++){
//            years.add(Integer.toString(i));
//        }
//        ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, years);
//        sp_years.setAdapter(adapter_year);
//
//        //set months
//        ArrayAdapter<String> adapter_month = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, months);
//        sp_months.setAdapter(adapter_month);

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
        neracaAsetLancars.add(new Neraca_AsetLancar("Kas", 1000000));
        neracaAsetLancars.add(new Neraca_AsetLancar("Kas di Bank", 2000000));
        neracaAsetLancars.add(new Neraca_AsetLancar("Piutang Dagang", 1000000));
        neracaAsetTetaps = new ArrayList<>();
        neracaAsetTetaps.add(new Neraca_AsetTetap("Gedung", 3000000));

    }

    public void showBalanceDate(){
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                tv_months.setText(monthFormat.format(newDate.getTime()));
                tv_years.setText(yearFormat.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void showDatePicker(){
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);

                date.setText(dateFormat.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
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
                vDialog.dismissWithAnimation();
            }
        });
        vDialog.setCancelButton("Belum", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                vDialog.dismissWithAnimation();
                dialog.show();
            }
        }).show();
    }
}
