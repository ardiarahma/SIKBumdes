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
        sharedPref = context.getSharedPreferences("Register", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("name", user.getCompany_name());
        editor.putString("address", user.getCompany_address());
        editor.putInt("telephone", user.getCompany_telp());
        editor.putString("email", user.getCompany_email());
        editor.putString("password", user.getCompany_pass());
        editor.apply();
    }

    public User getBaseUser(){
        sharedPref = context.getSharedPreferences("Register", Context.MODE_PRIVATE);
        return new User(
                sharedPref.getString("name", null),
                sharedPref.getString("address",null),
                sharedPref.getInt("telephone", -1),
                sharedPref.getString("email", null),
                sharedPref.getString("password", null)
        );
    }
}
