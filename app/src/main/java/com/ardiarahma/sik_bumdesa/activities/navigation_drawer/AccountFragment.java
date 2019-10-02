package com.ardiarahma.sik_bumdesa.activities.navigation_drawer;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.DatabaseHelper;
import com.ardiarahma.sik_bumdesa.networks.adapters.ClassAccountAdapter;
import com.ardiarahma.sik_bumdesa.networks.models.ClassAccount;
import com.diegodobelo.expandingview.ExpandingItem;
import com.diegodobelo.expandingview.ExpandingList;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    DatabaseHelper db;
    RecyclerView rv;
    ClassAccountAdapter adapter;
    ArrayList<ClassAccount> classAccounts;

    ExpandingList expandingList = null;
    ExpandingItem item;

    FloatingActionButton fab1, fab2;
    EditText kode_klasifikasi, nama_klasifikasi;
    Spinner spParent, spPosisi;

    Dialog dialog;
    SweetAlertDialog vDialog;


    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account_data, container, false);

        rv = v.findViewById(R.id.list);
        adapter = new ClassAccountAdapter(getActivity());
        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(adapter);
        adapter.setData(getList());


        fab1 = v.findViewById(R.id.fab_1);
        fab2 = v.findViewById(R.id.fab_2);

        kode_klasifikasi = v.findViewById(R.id.kode_klasifikasi);
        nama_klasifikasi = v.findViewById(R.id.nama_klasifikasi);
        spParent = v.findViewById(R.id.parentAcc_spinner);
        spPosisi = v.findViewById(R.id.posisi_spinner);



        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.add_parent_account);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

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

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.add_child_account);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

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
                        validationCAccount();
                    }
                });
                dialog.show();
            }
        });




//        =====================================================================
//        db = new DatabaseHelper(getContext());
//
//        rv = v.findViewById(R.id.rv_classAccount);

        //create class
//        ClassAccount classAccount1 = new ClassAccount(1, "Neraca_AsetLancarAdapter");
//        ClassAccount classAccount2 = new ClassAccount(1, "Liabilitas/Utang");
//        ClassAccount classAccount3 = new ClassAccount(1, "Ekuitas");
//        ClassAccount classAccount4 = new ClassAccount(1, "Pendapatan");
//        ClassAccount classAccount5 = new ClassAccount(1, "Biaya Lainnya");
//        ClassAccount classAccount6 = new ClassAccount(1, "Pendapatan Lainnya");
//
//        db.addClassAccount(classAccount1);
//        db.addClassAccount(classAccount2);
//        db.addClassAccount(classAccount3);
//        db.addClassAccount(classAccount4);
//        db.addClassAccount(classAccount5);
//        db.addClassAccount(classAccount6);


//        classAccounts = db.getAllClassAccount();
//        adapter = new ClassAccountAdapter(getContext(), classAccounts);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        rv.setLayoutManager(layoutManager);
//        rv.setItemAnimator(new DefaultItemAnimator());
//        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
//        rv.addItemDecoration(decoration);
//        rv.setAdapter(adapter);
//======================================================================



//        expandingList = v.findViewById(R.id.expanding_list_main);
//        expandingList.createNewItem(R.layout.expanding_layout);
//
//        ((TextView) item.findViewById(R.id.title)).setText("It Works!!");
//
//        //This will create 5 items
//        item.createSubItems(5);
//
//        //get a sub item View
//        View subItemZero = item.getSubItemView(0);
//        ((TextView) subItemZero.findViewById(R.id.sub_title)).setText("Cool");
//
//        View subItemOne = item.getSubItemView(1);
//        ((TextView) subItemOne.findViewById(R.id.sub_title)).setText("Awesome");
//
//        item.setIndicatorColorRes(R.color.three);
//        item.setIndicatorIconRes(R.drawable.ic_account);

//        db = new DatabaseHelper(getActivity());
//
//        db.addAccountClass("Neraca_AsetLancarAdapter");
//        db.addAccountClass("Liabilitas/Utang");
//        db.addAccountClass("Ekuitas");
//        db.addAccountClass("Pendapatan");
//        db.addAccountClass("Beban");
//        db.addAccountClass("Pendapatan Lainnya");
//        db.addAccountClass("Biaya Lainnya");
//
//        Toast.makeText(getActivity(), "Tabel berhasil disimpan", Toast.LENGTH_LONG).show();

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
                vDialog.dismiss();
            }
        });
        vDialog.setCancelButton("Belum", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                vDialog.dismiss();
                dialog.show();
            }
        }).show();
    }

    public void validationCAccount(){
        final SweetAlertDialog vDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE);
        vDialog.setTitleText("Apakah data sudah benar?");
        vDialog.setConfirmText("Ya, benar");
        vDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                SweetAlertDialog sweet_dialog = new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
                sweet_dialog.setTitleText("Akun berhasil ditambahkan");
                sweet_dialog.show();
                dialog.dismiss();
                vDialog.dismiss();
            }
        });

    }

    public ArrayList<ClassAccount> getList(){
        ArrayList<ClassAccount> classAccounts = new ArrayList<>();

        ClassAccount account_1 = new ClassAccount(1, "Aset", 1, null);
        ClassAccount account_1_1 = new ClassAccount(11, "Aset Lancar", 2, null);
        ClassAccount account_1_1_1 = new ClassAccount(1110, "Kas", 3, "DEBIT");
        ClassAccount account_1_1_2 = new ClassAccount(1111, "Kas di Bank", 3, "DEBIT");
        ClassAccount account_1_1_3 = new ClassAccount(1120, "Piutang Dagang", 3, "DEBIT");
        ClassAccount account_1_1_4 = new ClassAccount(1130, "Sewa Dibayar Dimuka", 3, "DEBIT");
        ClassAccount account_1_2 = new ClassAccount(12, "Aset Tetap", 2, null);
        ClassAccount account_1_2_1 = new ClassAccount(1210, "Tanah", 3, "DEBIT");
        ClassAccount account_1_2_2 = new ClassAccount(1220, "Gedung", 3, "DEBIT");
        ClassAccount account_1_2_3 = new ClassAccount(1221, "Akumulasi Penyusutan Gedung", 3, "KREDIT");
        ClassAccount account_1_2_4 = new ClassAccount(1230, "Kendaraan", 3, "DEBIT");
        ClassAccount account_1_2_5 = new ClassAccount(1231, "Akumulasi Penyusutan Kendaraan", 3, "KREDIT");
        ClassAccount account_1_2_6 = new ClassAccount(1240, "Peralatan Kantor", 3, "DEBIT");
        ClassAccount account_1_2_7 = new ClassAccount(1240, "Akumulasi Penyusutan Peralatan Kantor", 3, "KREDIT");
        ClassAccount account_1_3 = new ClassAccount(13, "Aset Lainnya", 2, null);
        account_1.classAccounts.add(account_1_1);
        account_1_1.classAccounts.add(account_1_1_1);
        account_1_1.classAccounts.add(account_1_1_2);
        account_1_1.classAccounts.add(account_1_1_3);
        account_1_1.classAccounts.add(account_1_1_4);
        account_1.classAccounts.add(account_1_2);
        account_1_2.classAccounts.add(account_1_2_1);
        account_1_2.classAccounts.add(account_1_2_2);
        account_1_2.classAccounts.add(account_1_2_3);
        account_1_2.classAccounts.add(account_1_2_4);
        account_1_2.classAccounts.add(account_1_2_5);
        account_1_2.classAccounts.add(account_1_2_6);
        account_1_2.classAccounts.add(account_1_2_7);
        account_1.classAccounts.add(account_1_3);

        ClassAccount account_2 = new ClassAccount(2, "Liabilitas/Utang", 1, null);
        ClassAccount account_2_1 = new ClassAccount(21, "Utang Lancar", 2, null);
        ClassAccount account_2_1_1 = new ClassAccount(2110, "Utang Dagang", 3, "KREDIT");
        ClassAccount account_2_1_2 = new ClassAccount(2120, "Utang Gaji", 3, "KREDIT");
        ClassAccount account_2_1_3 = new ClassAccount(2130, "Utang Bank", 3, "KREDIT");
        ClassAccount account_2_2 = new ClassAccount(22, "Utang Jangka Panjang", 2, null);
        ClassAccount account_2_2_1 = new ClassAccount(2210, "Obligasi", 3, "KREDIT");
        account_2.classAccounts.add(account_2_1);
        account_2_1.classAccounts.add(account_2_1_1);
        account_2_1.classAccounts.add(account_2_1_2);
        account_2_1.classAccounts.add(account_2_1_3);
        account_2.classAccounts.add(account_2_2);
        account_2_2.classAccounts.add(account_2_2_1);

        ClassAccount account_3 = new ClassAccount(3, "Ekuitas", 1, null);
        ClassAccount account_3_1 = new ClassAccount(3100, "Modal Disetor", 2, "KREDIT");
        ClassAccount account_3_2 = new ClassAccount(3110, "Saldo Laba Ditahan", 2, "KREDIT");
        ClassAccount account_3_3 = new ClassAccount(3120, "Saldo Laba Tahun Berjalan", 2, "KREDIT");
        account_3.classAccounts.add(account_3_1);
        account_3.classAccounts.add(account_3_2);
        account_3.classAccounts.add(account_3_3);

        ClassAccount account_4 = new ClassAccount(4, "Pendapatan", 1, null);
        ClassAccount account_4_1 = new ClassAccount(4100, "Pendapatan Wisata", 2, "KREDIT");
        ClassAccount account_4_2 = new ClassAccount(4110, "Pendapatan Homestay", 2, "KREDIT");
        ClassAccount account_4_3 = new ClassAccount(4130, "Pendapatan Resto", 2, "KREDIT");
        ClassAccount account_4_4 = new ClassAccount(4140, "Pendapatan Event", 2, "KREDIT");
        account_4.classAccounts.add(account_4_1);
        account_4.classAccounts.add(account_4_2);
        account_4.classAccounts.add(account_4_3);
        account_4.classAccounts.add(account_4_4);


        ClassAccount account_5 = new ClassAccount(5, "Beban", 1, null);
        ClassAccount account_5_1 = new ClassAccount(5110, "Biaya Gaji", 2, "DEBIT");
        ClassAccount account_5_2 = new ClassAccount(5120, "Biaya Listrik, Air, dan Telepon", 2, "DEBIT");
        ClassAccount account_5_3 = new ClassAccount(5130, "Biaya Administrasi dan Umum", 2, "DEBIT");
        ClassAccount account_5_4 = new ClassAccount(5140, "Biaya Pemasaran", 2, "DEBIT");
        ClassAccount account_5_5 = new ClassAccount(5150, "Biaya Perlengkapan Kantor", 2, "DEBIT");
        ClassAccount account_5_6 = new ClassAccount(5160, "Biaya Sewa", 2, "DEBIT");
        ClassAccount account_5_7 = new ClassAccount(5170, "Biaya Asuransi", 2, "DEBIT");
        ClassAccount account_5_8 = new ClassAccount(5180, "Biaya Penyusutan Gedung", 2, "DEBIT");
        ClassAccount account_5_9 = new ClassAccount(5190, "Biaya Penyusutan Kendaraan", 2, "DEBIT");
        ClassAccount account_5_10 = new ClassAccount(5200, "Biaya Penyusutan Peralatan Kantor", 2, "DEBIT");
        account_5.classAccounts.add(account_5_1);
        account_5.classAccounts.add(account_5_2);
        account_5.classAccounts.add(account_5_3);
        account_5.classAccounts.add(account_5_4);
        account_5.classAccounts.add(account_5_5);
        account_5.classAccounts.add(account_5_6);
        account_5.classAccounts.add(account_5_7);
        account_5.classAccounts.add(account_5_8);
        account_5.classAccounts.add(account_5_9);
        account_5.classAccounts.add(account_5_10);

        ClassAccount account_6 = new ClassAccount(6, "Pendapatan Lainnya", 1, null);
        ClassAccount account_6_1 = new ClassAccount(6110, "Pendapatan Lain-lain", 2, "KREDIT");
        account_6.classAccounts.add(account_6_1);

        ClassAccount account_7 = new ClassAccount(7, "Biaya Lainnya", 1, null);
        ClassAccount account_7_1 = new ClassAccount(7110, "Biaya Lain-lain", 2, "DEBIT");
        account_7.classAccounts.add(account_7_1);

        classAccounts.add(account_1);
        classAccounts.add(account_2);
        classAccounts.add(account_3);
        classAccounts.add(account_4);
        classAccounts.add(account_5);
        classAccounts.add(account_6);
        classAccounts.add(account_7);

        return classAccounts;

    }

    @Override
    public void onViewCreated(View view, @android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Data Akun");
    }

}
