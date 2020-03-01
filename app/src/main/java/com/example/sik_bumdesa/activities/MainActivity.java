package com.example.sik_bumdesa.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sik_bumdesa.R;
import com.example.sik_bumdesa.activities.dashboard.BukuBesarActivity;
import com.example.sik_bumdesa.activities.dashboard.EkuitasActivity;
import com.example.sik_bumdesa.activities.dashboard.JurnalActivity;
import com.example.sik_bumdesa.activities.dashboard.LabaRugiActivity;
import com.example.sik_bumdesa.activities.dashboard.NeracaActivity;
import com.example.sik_bumdesa.activities.navigation_drawer.AccountActivity;
import com.example.sik_bumdesa.activities.navigation_drawer.AnggaranActivity;
import com.example.sik_bumdesa.activities.navigation_drawer.NeracaAwalActivity;
import com.example.sik_bumdesa.activities.navigation_drawer.UserActivity;
import com.example.sik_bumdesa.networks.SharedPref;
import com.example.sik_bumdesa.networks.models.User;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar = null;
    Context context = this;

    RelativeLayout v1, v2, v3, v4, v5;
    TextView welcome, nav_company_name, nav_company_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.setTitle("SIK Bumdes");

        User user = SharedPref.getInstance(this).getBaseUser();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        displaySelectedScreen(R.id.nav_home);

        welcome = findViewById(R.id.company_name);
        welcome.setText(user.getNama());

        View headerView = navigationView.getHeaderView(0);
        nav_company_name = headerView.findViewById(R.id.nav_company_name);
        nav_company_email = headerView.findViewById(R.id.nav_company_email);
        nav_company_name.setText(user.getNama());
        nav_company_email.setText(user.getEmail());

        v1 = findViewById(R.id.menu1);
        v2 = findViewById(R.id.menu2);
        v3 = findViewById(R.id.menu3);
        v4 = findViewById(R.id.menu4);
        v5 = findViewById(R.id.menu5);

        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JurnalActivity.class);
                startActivity(intent);
            }
        });

        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BukuBesarActivity.class);
                startActivity(intent);
            }
        });

        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LabaRugiActivity.class);
                startActivity(intent);
            }
        });

        v4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EkuitasActivity.class);
                startActivity(intent);
            }
        });

        v5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NeracaActivity.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


//    public void displaySelectedScreen(int id){
//        Fragment fragment = null;
//        switch (id){
//            case R.id.nav_home:
//                fragment = new HomeFragment();
//                break;
//            case R.id.nav_akun:
//                fragment = new AccountFragment();
//                break;
//            case R.id.nav_neraca:
//                fragment = new BalanceFragment();
//                break;
//            case R.id.nav_rab:
//                fragment = new RABFragment();
//                break;
//            case R.id.nav_settings:
//                fragment = new UserFragment();
//                break;
//            case R.id.logout:
//                logoutConfirmation();
//                break;
//        }
//        if (fragment != null){
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.screen_area, fragment);
//            ft.commit();
//        }
//        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_home:
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_akun:
                Intent intent1 = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_neraca:
                Intent intent2 = new Intent(MainActivity.this, NeracaAwalActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_rab:
                Intent intent3 = new Intent(MainActivity.this, AnggaranActivity.class);
                startActivity(intent3);
                break;
            case R.id.nav_settings:
                Intent intent4 = new Intent(MainActivity.this, UserActivity.class);
                break;
            case R.id.logout:
                logoutConfirmation();
                break;
        }
//        displaySelectedScreen(id);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logoutConfirmation(){
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setTitleText("Konfirmasi");
        sweetAlertDialog.setConfirmText("Keluar");
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                SharedPref.getInstance(context).clear();
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(context, "Anda berhasil keluar", Toast.LENGTH_SHORT).show();

            }
        });
        sweetAlertDialog.setCancelButton("Batal", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
            }
        }).show();
    }
}
