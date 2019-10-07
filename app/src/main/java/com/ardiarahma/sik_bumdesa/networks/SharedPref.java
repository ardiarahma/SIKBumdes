package com.ardiarahma.sik_bumdesa.networks;

import android.content.Context;
import android.content.SharedPreferences;

import com.ardiarahma.sik_bumdesa.networks.models.User;

/**
 * Created by Windows 10 on 8/11/2019.
 */

public class SharedPref {

    private SharedPreferences sharedPref;
    private static SharedPref instance;
    private Context context;

    public SharedPref(Context context) {
        this.context = context;
    }

    public static synchronized SharedPref getInstance(Context context){
        if (instance == null){
            instance = new SharedPref(context);
        }
        return instance;
    }

    public void saveBaseUser(User user){
        sharedPref = context.getSharedPreferences("Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("id", user.getId());
        editor.putString("nama", user.getNama());
        editor.putInt("no_telepon", user.getNo_telepon());
        editor.putString("alamat", user.getAlamat());
        editor.putString("email", user.getEmail());
        editor.putString("token", user.getToken());
        editor.apply();
    }

    public User getBaseUser(){
        sharedPref = context.getSharedPreferences("Login", Context.MODE_PRIVATE);
        return new User(
                sharedPref.getInt("id", -1),
                sharedPref.getString("nama", null),
                sharedPref.getInt("no_telepon", -1),
                sharedPref.getString("alamat",null),
                sharedPref.getString("email", null),
                sharedPref.getString("token", null)
        );
    }

    //untuk mengetahui user udah login atau belum
    public boolean isLoggedIn(){
        sharedPref = context.getSharedPreferences("Login", Context.MODE_PRIVATE);
        return sharedPref.getInt("id", -1) != -1;
    }

    public void clear(){
        sharedPref = context.getSharedPreferences("Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
    }
}
