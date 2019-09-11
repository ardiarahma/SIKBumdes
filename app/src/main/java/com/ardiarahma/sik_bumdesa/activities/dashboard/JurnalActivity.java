package com.ardiarahma.sik_bumdesa.activities.dashboard;

import android.animation.ArgbEvaluator;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.database.adapters.JurnalViewPagerAdapter;
import com.ardiarahma.sik_bumdesa.database.models.Jurnal;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class JurnalActivity extends AppCompatActivity {

    Spinner sp_months, sp_years;
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

    ImageButton toolbar_back;

    private ViewPager viewPager = null;
    private JurnalViewPagerAdapter pagerAdapter = null;

    FloatingActionButton fab1;

    Dialog dialog;
    SweetAlertDialog vDialog;

    DatePickerDialog datePickerDialog;
    ImageButton date_btn;
    TextView date;
    SimpleDateFormat dateFormat;

    TextView tv_keterangan, tv_jumlah, tv_kwitansi;
    Spinner debit_spinner, kredit_spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jurnal);

        toolbar_back = findViewById(R.id.toolbar_back);
        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        sp_months = findViewById(R.id.sp_month);
        sp_years = findViewById(R.id.sp_year);

        //set year
        ArrayList<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1900; i <= thisYear; i++){
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, years);
        sp_years.setAdapter(adapter_year);

        //set months
        final ArrayAdapter<String> adapter_month = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, months);
        sp_months.setAdapter(adapter_month);

        pagerAdapter = new JurnalViewPagerAdapter();
        viewPager = findViewById (R.id.viewpager);
        viewPager.setAdapter (pagerAdapter);

        LayoutInflater inflater = this.getLayoutInflater();
        FrameLayout v0 = (FrameLayout) inflater.inflate (R.layout.fragment_one_of, null);
        FrameLayout v1 = (FrameLayout) inflater.inflate (R.layout.fragment_two_of, null);

        pagerAdapter.addView (v0, 0);
        pagerAdapter.addView (v1, 1);
        pagerAdapter.notifyDataSetChanged();

        fab1 = findViewById(R.id.fab_1);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(JurnalActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.add_jurnal_debit);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                tv_keterangan = dialog.findViewById(R.id.tv_keterangan);
                tv_jumlah = dialog.findViewById(R.id.tv_jumlah);
                tv_kwitansi = dialog.findViewById(R.id.tv_kwitansi);
                debit_spinner = dialog.findViewById(R.id.debit_spinner);
                kredit_spinner = dialog.findViewById(R.id.kredit_spinner);
                date_btn = dialog.findViewById(R.id.date_btn);
                date = dialog.findViewById(R.id.date);

                dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

                date_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDatePicker();
                    }
                });

                ArrayAdapter<String> debit_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, akun);
                debit_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                debit_spinner.setAdapter(debit_adapter);

                ArrayAdapter<String> kredit_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, akun);
                kredit_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                kredit_spinner.setAdapter(kredit_adapter);


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

    }

    public void showDatePicker(){
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
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
        final SweetAlertDialog vDialog = new SweetAlertDialog(JurnalActivity.this, SweetAlertDialog.WARNING_TYPE);
        vDialog.setTitleText("Apakah data sudah benar?");
        vDialog.setConfirmText("Ya, benar");
        vDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                SweetAlertDialog sweet_dialog = new SweetAlertDialog(JurnalActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                sweet_dialog.setTitleText("Jurnal debit berhasil ditambahkan");
                sweet_dialog.show();
                dialog.dismiss();
                vDialog.dismiss();
            }
        }).show();
    }

    public void addView (View newPage)
    {
        int pageIndex = pagerAdapter.addView (newPage);
        // You might want to make "newPage" the currently displayed page:
        viewPager.setCurrentItem (pageIndex, true);
    }

    public void removeView (View defunctPage)
    {
        int pageIndex = pagerAdapter.removeView (viewPager, defunctPage);
        // You might want to choose what page to display, if the current page was "defunctPage".
        if (pageIndex == pagerAdapter.getCount())
            pageIndex--;
        viewPager.setCurrentItem (pageIndex);
    }

    // Here's what the app should do to get the currently displayed page.
    public View getCurrentPage ()
    {
        return pagerAdapter.getView (viewPager.getCurrentItem());
    }

    public void setCurrentPage (View pageToShow)
    {
        viewPager.setCurrentItem (pagerAdapter.getItemPosition (pageToShow), true);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
