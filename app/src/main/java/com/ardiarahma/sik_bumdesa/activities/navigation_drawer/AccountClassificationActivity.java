package com.ardiarahma.sik_bumdesa.activities.navigation_drawer;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.networks.adapters.AkunAdapter;
import com.ardiarahma.sik_bumdesa.networks.adapters.KlasifikasiAkunAdapter;
import com.ardiarahma.sik_bumdesa.networks.models.AkunExp;
import com.ardiarahma.sik_bumdesa.networks.models.KlasifikasiAkun;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AccountClassificationActivity extends AppCompatActivity {

    RecyclerView list;
    KlasifikasiAkunAdapter adapter;
    private List<KlasifikasiAkun> klasifikasiAkuns;

    ImageButton toolbar_back;

    FloatingActionButton fab1, fab2;
    Dialog dialog;
    SweetAlertDialog vDialog;

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

        fab1 = findViewById(R.id.fab_1);
        fab2 = findViewById(R.id.fab_2);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(AccountClassificationActivity.this);
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
                dialog = new Dialog(AccountClassificationActivity.this);
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

    }

    public void validationCAccount(){
        final SweetAlertDialog vDialog = new SweetAlertDialog(AccountClassificationActivity.this, SweetAlertDialog.WARNING_TYPE);
        vDialog.setTitleText("Apakah data sudah benar?");
        vDialog.setConfirmText("Ya, benar");
        vDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                //post data
                SweetAlertDialog sweet_dialog = new SweetAlertDialog(AccountClassificationActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                sweet_dialog.setTitleText("Akun berhasil ditambahkan");
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

    public void validationAccount(){
        final SweetAlertDialog vDialog = new SweetAlertDialog(AccountClassificationActivity.this, SweetAlertDialog.WARNING_TYPE);
        vDialog.setTitleText("Apakah data sudah benar?");
        vDialog.setConfirmText("Ya, benar");
        vDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                //post data
                SweetAlertDialog sweet_dialog = new SweetAlertDialog(AccountClassificationActivity.this, SweetAlertDialog.SUCCESS_TYPE);
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

    public void getKlasifikasi() {
        klasifikasiAkuns = new ArrayList<>(6);
        for (int i = 0; i < 6; i++){
            List<AkunExp> akunExps = new ArrayList<>(3);
            akunExps.add(new AkunExp(1110, 11,"Kas", "Debit"));
            akunExps.add(new AkunExp(1111, 11, "Kas di Bank", "Debit"));
            akunExps.add(new AkunExp(1120, 11, "Piutang Dagang", "Debit"));
            klasifikasiAkuns.add(new KlasifikasiAkun("Aset Lancar", akunExps));
        }
    }
}
