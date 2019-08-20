package com.ardiarahma.sik_bumdesa.activities.navigation_drawer;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    TextView tvName, tvAddress, tvPhone, tvEmail;
    Button change_pass, change_profile;

    EditText etName, etAddress, etPhone, etEmail, etOldPass, etNewPass, etConfPass;

    Dialog dialog;
    SweetAlertDialog vDialog;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Pengaturan Perusahaan");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user, container, false);

        tvName = v.findViewById(R.id.company_name);
        tvAddress = v.findViewById(R.id.company_addr);
        tvPhone = v.findViewById(R.id.company_phone);
        tvEmail = v.findViewById(R.id.company_email);

        etName = v.findViewById(R.id.etName);
        etAddress = v.findViewById(R.id.etAddress);
        etPhone = v.findViewById(R.id.etPhone);
        etEmail = v.findViewById(R.id.etEmail);

        etOldPass = v.findViewById(R.id.old_password);
        etNewPass = v.findViewById(R.id.new_pass);
        etConfPass = v.findViewById(R.id.confirm_pass);

        change_pass = v.findViewById(R.id.change_pass);
        change_profile = v.findViewById(R.id.change_profile);

        change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.user_change_pass);
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
                        validationPassword();
                    }
                });
                dialog.show();
            }
        });

        change_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.user_change_profile);
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
                        validationProfile();
                    }
                });
                dialog.show();
            }
        });

        return v;
    }

    public void validationProfile() {
        final SweetAlertDialog vDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE);
        vDialog.setTitleText("Apakah data sudah benar?");
        vDialog.setConfirmText("Ya, benar");
        vDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                SweetAlertDialog sweet_dialog = new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
                sweet_dialog.setTitleText("Detail Perusahaan berhasil diubah");
                dialog.dismiss();
                sweet_dialog.show();
//                if (!validateName() || !validationAdrress() || !validatePhone() || !validateEmail()){
//                    //ini nanti ada manggil helper buat nyimpen datanya
//                    SweetAlertDialog sweet_dialog = new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
//                    sweet_dialog.setTitleText("Detail Perusahaan berhasil diubah");
//                    dialog.dismiss();
//                    sweet_dialog.show();
//
//                }else {
//                    dialog.show();
//                }
            }
        }).show();
    }

    public void validationPassword() {
        final SweetAlertDialog vDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE);
        vDialog.setTitleText("Yakin ingin mengubah password?");
        vDialog.setConfirmText("Ya");
        vDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                SweetAlertDialog sweet_dialog = new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
                sweet_dialog.setTitleText("Password berhasil diubah");
                sweet_dialog.show();
                dialog.dismiss();
//                if (!validateOldPassword() || !validateNewPassword()){
//                    //ini nanti ada manggil helper buat nyimpen datanya
//                    SweetAlertDialog sweet_dialog = new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
//                    sweet_dialog.setTitleText("Password berhasil diubah");
//                    sweet_dialog.show();
//                    dialog.dismiss();
//                }else {
//                    dialog.show();
//                }
            }
        }).show();
    }

    private boolean validateName() {
        String name = etName.getText().toString().trim();

        if (name.isEmpty()) {
            etName.setError("Nama perusahaan harus diisi");
            return false;
        } else {
            etName.setError(null);
            return true;
        }
    }

    private boolean validationAdrress() {
        String address = etAddress.getText().toString().trim();

        if (address.isEmpty()) {
            etAddress.setError("Alamat perusahaan harus diisi");
            return false;
        } else {
            etAddress.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        String phone = etPhone.getText().toString().trim();

        if (phone.isEmpty()) {
            etPhone.setError("Nomor telepon harus diisi");
            return false;
        } else {
            etPhone.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String email = etEmail.getText().toString().trim();

        if (email.isEmpty()) {
            etEmail.setError("Email harus diisi");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Masukkan email yang benar");
            etEmail.requestFocus();
            return false;
        } else {
            etEmail.setError(null);
            return true;
        }
    }

    private boolean validateOldPassword() {
        String old_password = etOldPass.getText().toString().trim();

        if (old_password.isEmpty()) {
            etOldPass.setError("Password lama harus diisi");
            return false;
        } else if (old_password.length() < 8) {
            etOldPass.setError("Password lama harus berisi minimal 8 karakter");
            etOldPass.requestFocus();
            return false;
        } else {
            etOldPass.setError(null);
            return true;
        }
    }

    private boolean validateNewPassword() {
        String new_password = etNewPass.getText().toString().trim();
        String confirm_password = etConfPass.getText().toString().trim();

        if (new_password.isEmpty()) {
            etNewPass.setError("Password baru harus diisi");
            return false;
        } else if (new_password.length() < 8) {
            etNewPass.setError("Password baru harus berisi minimal 8 karakter");
            etNewPass.requestFocus();
            return false;
        }


        if (confirm_password.isEmpty()) {
            etConfPass.setError("Konfirmasi password baru harus diisi");
            return false;
        } else if (!confirm_password.equals(new_password)) {
            etConfPass.setError("Password tidak sama. Mohon ini dengan password yang sesuai");
            etConfPass.requestFocus();
            return false;
        } else {
            etNewPass.setError(null);
            etConfPass.setError(null);
            return true;
        }
    }



}
